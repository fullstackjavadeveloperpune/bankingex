package com.fullstack.entity;

import com.fullstack.constant.CustomerStatus;
import com.fullstack.constant.UserRole;
import jakarta.persistence.*;
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
@Entity
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long custId;

    @Column(unique = true)
    private long custAccountNumber;

   // @Size(min = 2, message = "Customer Name should be at least 2 characters")
    private String custName;

    private String custAddress;

    //@Range(min = 1000000000, max = 9999999999L, message = "Contact Number must be 10 Digit")
    @Column(unique = true)
    private long custContactNumber;

    private double custAccountBalance;

    private LocalDate custDOB;

    private CustomerStatus customerStatus;

    @Column(unique = true)
    private long custUID;

    @Column(unique = true)
    private String custPanCard;

   // @Email(message = "Email ID Must be valid")
    @Column(unique = true)
    private String custEmailId;


  /*  @NotBlank(message = "Password is required")
    @Pattern(
            regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!]).{8,16}$",
            message = "Password must be 8-16 characters long, include at least one uppercase, one lowercase, one number, and one special character"
    )*/
    private String custPassword;

    private UserRole role;

}
