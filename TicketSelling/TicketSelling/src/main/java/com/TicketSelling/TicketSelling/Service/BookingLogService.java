package com.TicketSelling.TicketSelling.Service;

import com.TicketSelling.TicketSelling.DTO.Request.Booking.BookingCreationRequest;
import com.TicketSelling.TicketSelling.DTO.Request.BookingLog.BookingLogRequest;
import com.TicketSelling.TicketSelling.Entity.BookingLog;
import com.TicketSelling.TicketSelling.Enum.BookingLogAction;
import com.TicketSelling.TicketSelling.Repository.IBookingLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
@RequiredArgsConstructor
public class BookingLogService {

    private  final IBookingLogRepository bookingLogRepository;

    public void createNewBookingLog(BookingLogRequest bookingLogRequest) {
        System.out.println("thuc hien tao booking log");
        BookingLog bookingLog = BookingLog.builder()
                .customerId(bookingLogRequest.getCustomerId())
                .details(bookingLogRequest.getDetail())
                .bookingId(bookingLogRequest.getBookingId())
                .action(BookingLogAction.BOOKING)
                .build();
        System.out.println("thuc hien save");
        bookingLogRepository.save(bookingLog);
    }

    public List<BookingLog> getAllBookingLogByCustomerId(String customerId) {
        return bookingLogRepository.getAllBookingLogByCustomerId(customerId);
    }
    public List<BookingLog> findBookingLogByDateRange(LocalDateTime startDate, LocalDateTime endDate) {
        return bookingLogRepository.findBookingLogByDateRange(startDate,endDate);
    }

}

