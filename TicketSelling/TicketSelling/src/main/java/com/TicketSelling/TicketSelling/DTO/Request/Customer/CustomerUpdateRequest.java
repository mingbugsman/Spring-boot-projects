package com.TicketSelling.TicketSelling.DTO.Request.Customer;



import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CustomerUpdateRequest {

    @NotBlank(message = "Name cannot be blank")
    private String customerName;

    @Pattern(regexp = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$", message = "Invalid email format")
    private String email;

    @Pattern(regexp = "^\\+?[0-9]{7,20}$", message = "Invalid phone number")
    private String phoneNumber;

    private String address;

    @Past(message = "Birth date must be in the past")
    private LocalDate birthDate;
}

