package com.TicketSelling.TicketSelling.Service;

import com.TicketSelling.TicketSelling.DTO.Request.Booking.BookingCreationRequest;
import com.TicketSelling.TicketSelling.DTO.Request.BookingLog.BookingLogRequest;
import com.TicketSelling.TicketSelling.DTO.Request.Payment.PaymentRequest;
import com.TicketSelling.TicketSelling.DTO.Request.Payment.ShowInfoPaymentRequest;
import com.TicketSelling.TicketSelling.DTO.Response.Payment.PaymentInformation;
import com.TicketSelling.TicketSelling.DTO.Response.Payment.PaymentResponse;
import com.TicketSelling.TicketSelling.Entity.*;
import com.TicketSelling.TicketSelling.Enum.*;
import com.TicketSelling.TicketSelling.Exception.ApplicationException;
import com.TicketSelling.TicketSelling.Exception.ErrorCode;
import com.TicketSelling.TicketSelling.Mapper.BookingMapper;
import com.TicketSelling.TicketSelling.Mapper.SeatMapper;
import com.TicketSelling.TicketSelling.Repository.*;
import com.TicketSelling.TicketSelling.Repository.Jpa.PaymentJpaRepository;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PaymentService {
    IConcertRepository concertRepository;
    ICustomerRepository customerRepository;
    ISeatRepository seatRepository;
    ITicketRepository ticketRepository;
    IBookingRepository bookingRepository;
    PaymentJpaRepository paymentJpaRepository;
    BookingLogService bookingLogService;
    BookingMapper bookingMapper;
    SeatMapper seatMapper;


    public PaymentInformation showInfo(ShowInfoPaymentRequest request) {
        var ticketInfoList = request.getSeatIds().stream().map(seatRepository::findSeatById).map(seatMapper::toSeatResponse).toList();
        int totalTickets = request.getSeatIds().size();
        double totalAmount = calculateTotalPrice(request).doubleValue();
        return new PaymentInformation(totalTickets,totalAmount, ticketInfoList);
    }

    @Transactional
    public synchronized  PaymentResponse processReserveTicketPayment(String bookingId, PaymentRequest request) {
        Booking booking = bookingRepository.getBookingById(bookingId);
        checkConditionToPay( booking, request.getAmount());
        Payment payment = processSavingPayment(booking, request);
        String message = "user with id " + request.getCustomerId() + " successfully completed the ticket payment in the amount of " + request.getAmount() + ". Payment Method is " + request.getPaymentMethod();
        updateConfirmedBooking(booking);
        ticketToStatus(booking.getTickets(),TicketStatus.ACTIVE,SeatStatus.OCCUPIED);

        BookingLogRequest bookingLogRequest =BookingLogRequest.builder()
                .bookingStatus(BookingStatus.CONFIRMED)
                .bookingId(booking.getId())
                .customerId(booking.getId())
                .action(BookingLogAction.BOOKING)
                .ExpiredTimePayment(booking.getExpiredPaymentTime())
                .detail(message)
                .build();

        bookingLogService.createNewBookingLog(bookingLogRequest );
        return new PaymentResponse( message);
    }

    @Transactional
    public synchronized PaymentResponse processTicketPayment(PaymentRequest paymentRequest) {
        BookingCreationRequest bookingCreationRequest = paymentRequest.getBookingCreationRequest();
        Concert concert = concertRepository.findConcertById(bookingCreationRequest.getConcertId());
        Customer customer = customerRepository.getCustomerById(paymentRequest.getCustomerId());
        Booking booking = bookingMapper.toBooking(bookingCreationRequest);
        booking.setCustomer(customer);
        execSavingBookingWithTickets(bookingCreationRequest, booking, concert);
        processSavingPayment(booking, paymentRequest);
        checkConditionToPay(booking, paymentRequest.getAmount());
        updateConfirmedBooking(booking);
        ticketToStatus(booking.getTickets(),TicketStatus.ACTIVE,SeatStatus.OCCUPIED);
        String message = "user with id " + bookingCreationRequest.getCustomerId() + " successfully completed tickets payment in the amount of " + paymentRequest.getAmount() + ". Payment Method is " + paymentRequest.getPaymentMethod();

        BookingLogRequest bookingLogRequest =BookingLogRequest.builder()
                .bookingStatus(BookingStatus.CONFIRMED)
                .bookingId(booking.getId())
                .customerId(booking.getId())
                .action(BookingLogAction.BOOKING)
                .ExpiredTimePayment(booking.getExpiredPaymentTime())
                .detail(message)
                .build();

        bookingLogService.createNewBookingLog(bookingLogRequest );
        return new PaymentResponse(message);
    }

    @Transactional
    public PaymentResponse processListTicketPayment (
            List<PaymentRequest> requests
    ) {
       String id = "";
       int totalTickets = 0;
        for (var req : requests) {
            totalTickets += req.getBookingCreationRequest().getTickets().size();
            processTicketPayment(req);
        }
        String message = "user with id " + requests.getFirst().getCustomerId() + " successfully completed "+  totalTickets +  " tickets payment in the amount of " + requests.getFirst().getAmount() + ". Payment Method is " + requests.getFirst().getPaymentMethod();
        return new PaymentResponse(message);
    }


    private void checkConditionToPay(Booking booking, BigDecimal paymentAmount) {
        // calculate requiredAmount
        BigDecimal requiredAmount = booking.getTickets().stream()
                .map(Ticket::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        System.out.println(booking.getBookingStatus().name());
        System.out.println(booking.getExpiredPaymentTime().toString());
        if (booking.getBookingStatus().name().equals( BookingStatus.CONFIRMED.name())) {
            throw new ApplicationException(ErrorCode.TICKET_ALREADY_BOUGHT);
        }
        else if (LocalDateTime.now().isAfter(booking.getExpiredPaymentTime()) ) {
            System.out.println("timeout payment");
            booking.setBookingStatus(BookingStatus.CANCELLED);
            ticketToStatus(booking.getTickets(), TicketStatus.CANCELLED, SeatStatus.AVAILABLE);
            bookingRepository.save(booking);
            throw new ApplicationException(ErrorCode.PAYMENT_TIMEOUT_ERROR);
        } else if (paymentAmount.compareTo(requiredAmount) != 0) {
            System.out.println("don't have enough money");
            throw new ApplicationException(ErrorCode.PAYMENT_REQUIRED_MONEY_ERROR);
        }
    }

    @Transactional
    private void ticketToStatus(List<Ticket> tickets, TicketStatus ticketStatus, SeatStatus seatStatus) {
        for (var ticket : tickets) {
            ticket.setTicketStatus(ticketStatus);
            Seat seat = ticket.getSeat();
            seat.setSeatStatus(seatStatus);
            seatRepository.save(seat);
            ticketRepository.save(ticket);
        }
    }
    private Payment processSavingPayment(Booking booking, PaymentRequest request) {
        Payment payment = Payment.builder()
                .status(PaymentStatus.SUCCEEDED)
                .amount(request.getAmount())
                .paymentMethod(request.getPaymentMethod())
                .booking(booking)
                .build();
        return paymentJpaRepository.save(payment);
    }


    private void execSavingBookingWithTickets(BookingCreationRequest request, Booking booking, Concert concert) {
        if (request.getTickets() != null) {
            List<Ticket> tickets = new ArrayList<>();
            for ( var ticketCreationRequest : request.getTickets()) {
                Seat seat = seatRepository.findSeatById(ticketCreationRequest.getSeatId());
                seat.setSeatStatus(SeatStatus.RESERVED);
                TicketPK ticketPK = new TicketPK(seat.getId(), concert.getId());
                Ticket ticket = Ticket.builder()
                        .id(ticketPK)
                        .booking(booking)
                        .concert(concert)
                        .price(seat.getSeatCategory().getPrice())
                        .seat(seat)
                        .build();
                seatRepository.save(seat);
                tickets.add(ticket);
            }
            booking.setTickets(tickets);
            bookingRepository.save(booking);
            ticketRepository.saveAll(tickets);
        }
    }
    private BigDecimal calculateTotalPrice(ShowInfoPaymentRequest request) {
        return request.getSeatIds().stream()
                .map(seatId -> {
                    Seat seat = seatRepository.findSeatById(seatId);
                    return seat.getSeatCategory().getPrice();
                })
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
    private void updateConfirmedBooking(Booking booking) {
        booking.setBookingStatus(BookingStatus.CONFIRMED);
        bookingRepository.save(booking);
    }

}

