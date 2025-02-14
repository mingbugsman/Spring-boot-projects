package com.TicketSelling.TicketSelling.Repository.RepositoryImpl;

import com.TicketSelling.TicketSelling.DTO.Request.Booking.BookingCreationRequest;
import com.TicketSelling.TicketSelling.DTO.Request.Booking.BookingUpdateRequest;
import com.TicketSelling.TicketSelling.DTO.Response.Booking.BookingResponse;
import com.TicketSelling.TicketSelling.Entity.Booking;
import com.TicketSelling.TicketSelling.Entity.Customer;
import com.TicketSelling.TicketSelling.Entity.Ticket;
import com.TicketSelling.TicketSelling.Entity.TicketPK;
import com.TicketSelling.TicketSelling.Enum.TypedSort;
import com.TicketSelling.TicketSelling.Exception.ApplicationException;
import com.TicketSelling.TicketSelling.Exception.ErrorCode;
import com.TicketSelling.TicketSelling.Mapper.BookingMapper;
import com.TicketSelling.TicketSelling.Repository.IBookingRepository;
import com.TicketSelling.TicketSelling.Repository.ICustomerRepository;
import com.TicketSelling.TicketSelling.Repository.ITicketRepository;
import com.TicketSelling.TicketSelling.Repository.Jpa.BookingJpaRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Repository;

import java.util.Comparator;
import java.util.List;

@Repository
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BookingRepositoryImp implements IBookingRepository {
    ICustomerRepository customerRepository;
    ITicketRepository ticketRepository;
    BookingJpaRepository bookingJpaRepository;
    BookingMapper bookingMapper;


    @Override
    public Booking getBookingById(String bookingId) {
        return bookingJpaRepository.findById(bookingId)
                .orElseThrow(() -> new ApplicationException(ErrorCode.NOT_FOUND_ID));
    }

    @Override
    public BookingResponse getBooking(String bookingId) {
        Booking foundBooking = getBookingById(bookingId);
        return bookingMapper.toBookingResponse(foundBooking);
    }

    @Override
    public List<BookingResponse> getAllBookings(TypedSort typedSort) {
        List<BookingResponse> bookingResponses = bookingJpaRepository.findAll()
                .stream()
                .map(bookingMapper::toBookingResponse)
                .toList(); // Materialize stream first

        return switch (typedSort) {
            case ASCENDING -> bookingResponses.stream()
                    .sorted(Comparator.comparing(BookingResponse::createdAt))
                    .toList();
            case DESCENDING -> bookingResponses.stream()
                    .sorted(Comparator.comparing(BookingResponse::createdAt).reversed())
                    .toList();
            case DEFAULT -> bookingResponses;
        };
    }

    @Override
    public BookingResponse createNewBooking(BookingCreationRequest request) {
        Customer customer = customerRepository.getCustomerById(request.getCustomerId());
        Booking booking = bookingMapper.toBooking(request);
        booking.setCustomer(customer);
        return bookingMapper.toBookingResponse(bookingJpaRepository.save(booking));
    }

    @Override
    public BookingResponse updateBooking(String bookingId, BookingUpdateRequest request) {
        Booking booking = getBookingById(bookingId);
        bookingMapper.updateBooking(booking, request);
        List<TicketPK> ticketIds = request.getTicketIds();
        for (var ticketId :ticketIds) {
            Ticket ticket = ticketRepository.findTicketById(ticketId);
            booking.getTickets().add(ticket);
        }
        return bookingMapper.toBookingResponse(booking);

    }

    @Override
    public void deleteBooking(String bookingId) {
        if (!bookingJpaRepository.existsById(bookingId)) {
            throw new ApplicationException(ErrorCode.NOT_FOUND_ID);
        }
        bookingJpaRepository.deleteById(bookingId);
    }
}
