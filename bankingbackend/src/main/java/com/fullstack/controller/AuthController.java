package com.fullstack.controller;

import com.fullstack.dto.LogInRequest;
import com.fullstack.entity.Customer;
import com.fullstack.service.ICustomerService;
import com.fullstack.util.JWTUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final JWTUtil jwtUtil;

    private final AuthenticationManager authenticationManager;

    private final ICustomerService customerService;

    @PostMapping("/signup")
    public ResponseEntity<Customer> signUp(@RequestBody Customer customer) {

        return new ResponseEntity<>(customerService.signUp(customer), HttpStatus.CREATED);
    }

    @PostMapping("/signin")
    public ResponseEntity<String> generateToken(@RequestBody LogInRequest logInRequest) {

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(logInRequest.custEmailId(), logInRequest.custPassword()));

        return new ResponseEntity<>(jwtUtil.generateToken(logInRequest.custEmailId(), logInRequest.role().toString()), HttpStatus.OK);

    }
}
