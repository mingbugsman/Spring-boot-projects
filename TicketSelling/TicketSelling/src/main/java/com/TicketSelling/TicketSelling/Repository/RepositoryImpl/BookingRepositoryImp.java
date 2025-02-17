package com.TicketSelling.TicketSelling.Repository.RepositoryImpl;

import com.TicketSelling.TicketSelling.Entity.Booking;
import com.TicketSelling.TicketSelling.Exception.ApplicationException;
import com.TicketSelling.TicketSelling.Exception.ErrorCode;
import com.TicketSelling.TicketSelling.Repository.IBookingRepository;
import com.TicketSelling.TicketSelling.Repository.Jpa.BookingJpaRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
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
        return bookingJpaRepository.findBookingById(bookingId)
                .orElseThrow(() -> new ApplicationException(ErrorCode.NOT_FOUND_ID));
    }

    @Override
    public List<Booking> getBookingsByCustomerId(String customerId, LocalDateTime lastCreatedAt, String sortDirection, Pageable pageable) {
        return bookingJpaRepository.getBookingsByCustomerId(
                customerId,
                lastCreatedAt,
                sortDirection,
                pageable
        );
    }


    @Override
    public List<Booking> getAllBookings() {
        return bookingJpaRepository.getAllBookings();
    }


    @Override
    public boolean existsById(String bookingId) {
        return bookingJpaRepository.existsById(bookingId);
    }


    @Override
    public void deleteBooking(Booking booking) {
        booking.setDeletedAt(LocalDateTime.now());
        bookingJpaRepository.save(booking);
    }
}
