package com.blautech.ecommerce.authentication.infrastructure.adapters.in.rest.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Past;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignUpRequest {
    @NotBlank(message = "Firstname is required")
    @Size(min = 1, max = 64, message = "Firstname must be at most 64 characters")
    private String firstname;
    @NotBlank(message = "Lastname is required")
    @Size(min = 1, max = 64, message = "Lastname must be at most 64 characters")
    private String lastname;
    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;
    @NotBlank(message = "Password is required")
    @Size(min = 8, max = 64, message = "Lastname must be between 8 and 64 characters")
    private String password;
    @NotBlank(message = "Address is required")
    @Size(min = 1, max = 256)
    private String address;
    @NotNull(message = "Birthday is required")
    @Past(message = "Birthday must be at the past")
    private LocalDate birthday;
}
