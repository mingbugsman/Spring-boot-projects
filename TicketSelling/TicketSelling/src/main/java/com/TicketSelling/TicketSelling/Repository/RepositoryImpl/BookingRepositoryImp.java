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
    BookingJpaRepository bookingJpaRepository;



    @Override
    public Booking save(Booking booking) {
        return bookingJpaRepository.save(booking);
    }

    @Override
    public Booking getBookingById(String bookingId) {
        return bookingJpaRepository.findById(bookingId)
                .orElseThrow(() -> new ApplicationException(ErrorCode.NOT_FOUND_ID));
    }


    @Override
    public List<Booking> getAllBookings(TypedSort typedSort) {
        List<Booking> bookingResponses = bookingJpaRepository.findAll()
                .stream()
                .toList(); // Materialize stream first

        return switch (typedSort) {
            case ASCENDING -> bookingResponses.stream()
                    .sorted(Comparator.comparing(Booking::getCreatedAt))
                    .toList();
            case DESCENDING -> bookingResponses.stream()
                    .sorted(Comparator.comparing(Booking::getCreatedAt).reversed())
                    .toList();
            case DEFAULT -> bookingResponses;
        };
    }


    @Override
    public boolean existsById(String bookingId) {
        return bookingJpaRepository.existsById(bookingId);
    }


    @Override
    public void deleteBookingById(String bookingId) {
        bookingJpaRepository.deleteById(bookingId);
    }
}
