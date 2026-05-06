package com.fullstack.controller;

import com.fullstack.dto.LogInRequest;
import com.fullstack.entity.Customer;
import com.fullstack.service.ICustomerService;
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
public class CustomerController {

    private final ICustomerService customerService;

    @PostMapping("/signup")
    public ResponseEntity<Customer> signUp(@RequestBody @Valid Customer customer) {

        log.info("@@@@@@@@Trying to save data for Customer: " + customer.getCustName());
        return new ResponseEntity<>(customerService.signUp(customer), HttpStatus.CREATED);
    }

    @PostMapping("/signin")
    public ResponseEntity<Boolean> signIn(@RequestBody LogInRequest logInRequest) {

        return new ResponseEntity<>(customerService.signIn(logInRequest.custEmailId(), logInRequest.custPassword()), HttpStatus.OK);
    }

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

}
