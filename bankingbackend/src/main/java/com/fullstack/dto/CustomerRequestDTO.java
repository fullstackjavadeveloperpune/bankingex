package com.fullstack.dto;

import com.fullstack.constant.CustomerStatus;
import com.fullstack.constant.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerRequestDTO {

    private Long custId;

    private long custAccountNumber;

    @Size(min = 2, message = "Customer Name should be at least 2 characters")
    private String custName;

    @NotBlank(message = "Customer Address is required")
    private String custAddress;

    @Range(min = 1000000000L, max = 9999999999L,
            message = "Contact Number must be 10 Digit")
    private long custContactNumber;

    private double custAccountBalance;

    private LocalDate custDOB;

    private CustomerStatus customerStatus;

    @Range(min = 100000000000L, max = 999999999999L,
            message = "UID Number must be 12 Digit")
    private long custUID;

    @Pattern(
            regexp = "^[A-Z]{5}[0-9]{4}[A-Z]{1}$",
            message = "Invalid PAN Card Number"
    )
    private String custPanCard;

    @Email(message = "Email ID Must be valid")
    @NotBlank(message = "Email ID is required")
    private String custEmailId;

    @NotBlank(message = "Password is required")
    @Pattern(
            regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,16}$",
            message = "Password must be 8-16 characters long, include at least one uppercase, one lowercase, one number, and one special character"
    )
    private String custPassword;

    private UserRole role;

}