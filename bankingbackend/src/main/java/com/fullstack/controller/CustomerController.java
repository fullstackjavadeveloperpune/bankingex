package com.fullstack.controller;

import com.fullstack.dto.LogInRequest;
import com.fullstack.entity.Customer;
import com.fullstack.service.ICustomerService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
@Slf4j
@SecurityRequirement(name = "Bearer Auth")
public class CustomerController {

    private final ICustomerService customerService;


    @GetMapping("/findbyid/{custId}")
    public ResponseEntity<Optional<Customer>> findById(@PathVariable long custId) {
        return new ResponseEntity<>(customerService.findById(custId), HttpStatus.OK);
    }

    @GetMapping("/findall")
    public ResponseEntity<List<Customer>> findAll() {
        return new ResponseEntity<>(customerService.findAll(), HttpStatus.OK);
    }

    @PutMapping("/update/{custId}")
    public ResponseEntity<Customer> update(@PathVariable long custId, @RequestBody @Valid Customer customer) {

        return new ResponseEntity<>(customerService.update(custId, customer), HttpStatus.CREATED);
    }

    @PatchMapping("/changecontactnumber/{custAccountNumber}/{custContactNumber}")
    public ResponseEntity<Customer> changeContactNumber(@PathVariable long custAccountNumber, @PathVariable long custContactNumber) {

        return new ResponseEntity<>(customerService.changeContactNumber(custAccountNumber, custContactNumber), HttpStatus.OK);
    }

    @PatchMapping("/changeemail/{custAccountNumber}/{custEmailId}")
    public ResponseEntity<Customer> changeEmail(@PathVariable long custAccountNumber, @PathVariable String custEmailId) {

        return new ResponseEntity<>(customerService.changeEmailId(custAccountNumber, custEmailId), HttpStatus.OK);
    }

    @PatchMapping("/deposit/{custAccountNumber}/{amount}")
    public ResponseEntity<Customer> deposit(@PathVariable long custAccountNumber, @PathVariable double amount) {

        return new ResponseEntity<>(customerService.depositAmount(custAccountNumber, amount), HttpStatus.CREATED);
    }

    @PatchMapping("/withdraw/{custAccountNumber}/{amount}")
    public ResponseEntity<Customer> withdraw(@PathVariable long custAccountNumber, @PathVariable double amount) {

        return new ResponseEntity<>(customerService.withdrawAmount(custAccountNumber, amount), HttpStatus.CREATED);
    }

    @GetMapping("/verifyotp/{custEmailId}")
    public ResponseEntity<String> verifyOTP(@PathVariable String custEmailId) {
        customerService.verifyOTP(custEmailId);
        return new ResponseEntity<>("Please check your email for OTP: ", HttpStatus.OK);
    }

    @PatchMapping("/transferfund/{fromCustAccountNumber}/{toCustAccountNumber}/{otp}/{amount}")
    public ResponseEntity<String> transferFund(@PathVariable long fromCustAccountNumber, @PathVariable long toCustAccountNumber, @PathVariable String otp, @PathVariable double amount) {

        customerService.transferFund(fromCustAccountNumber, toCustAccountNumber, otp, amount);

        return new ResponseEntity<>("Fund Transfer Successfully", HttpStatus.OK);
    }

    @GetMapping("/checkaccbalance/{custAccountNumber}")
    public ResponseEntity<Double> checkAccountBalance(@PathVariable long custAccountNumber){

        return new ResponseEntity<>(customerService.checkAccBalance(custAccountNumber), HttpStatus.OK);
    }

}
