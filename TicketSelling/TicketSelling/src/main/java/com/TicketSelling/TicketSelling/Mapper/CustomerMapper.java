package com.TicketSelling.TicketSelling.Mapper;

import com.TicketSelling.TicketSelling.DTO.Request.Customer.CustomerCreationRequest;
import com.TicketSelling.TicketSelling.DTO.Request.Customer.CustomerUpdateRequest;
import com.TicketSelling.TicketSelling.DTO.Response.Booking.BookingResponse;
import com.TicketSelling.TicketSelling.DTO.Response.Concert.ConcertResponse;
import com.TicketSelling.TicketSelling.DTO.Response.Customer.CustomerBookingsResponse;
import com.TicketSelling.TicketSelling.DTO.Response.Customer.CustomerConcertHistoryResponse;
import com.TicketSelling.TicketSelling.DTO.Response.Customer.CustomerResponse;
import com.TicketSelling.TicketSelling.Entity.Customer;
import com.TicketSelling.TicketSelling.Utils.CastingUtil;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring",nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface CustomerMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "bookings",ignore = true)
    Customer toCustomer(CustomerCreationRequest request);


    CustomerResponse toCustomerResponse(Customer customer);


    @Mapping(target = "bookings", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    void updateCustomer(@MappingTarget Customer customer, CustomerUpdateRequest request);


    default CustomerBookingsResponse toCustomerBookingResponse(Object[] data) {
        if (data == null || data.length < 3) {
            return null;
        }

        return new CustomerBookingsResponse((String) data[0], (String) data[1] , CastingUtil.safeCastList (data[2], BookingResponse.class) );
    }
    default CustomerConcertHistoryResponse toCustomerConcertHistoryResponse(Object[] data) {
        if (data == null || data.length < 3) {
            return null;
        }
        return new CustomerConcertHistoryResponse( (String) data[0], (String) data[1], CastingUtil.safeCastList(data[2], ConcertResponse.class));
    }


}
