package com.fullstack.service;

import com.fullstack.entity.Customer;
import com.fullstack.exception.InsufficientFundException;
import com.fullstack.exception.InvalidAmountException;
import com.fullstack.exception.InvalidOTPException;
import com.fullstack.exception.RecordNotFoundException;
import com.fullstack.repository.CustomerRepository;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerService implements ICustomerService {

    private final PasswordEncoder passwordEncoder;


    private final CustomerRepository customerRepository;

    private final JavaMailSender javaMailSender;

    private static final SecureRandom secureRandom = new SecureRandom();

    private Map<String, String> otpStorage = new LinkedHashMap<>();

    @Value("${spring.mail.username}")
    private String fromEmail;

    @Override
    public Customer signUp(Customer customer) {

        // Password Encryption

        customer.setCustPassword(passwordEncoder.encode(customer.getCustPassword()));

        return customerRepository.save(customer);
    }

    @Override
    public boolean signIn(String custEmailId, String custPassword) {
        return customerRepository.findByCustEmailIdAndCustPassword(custEmailId, custPassword) != null;
    }

    @Override
    public Optional<Customer> findById(long custId) {
        return Optional.of(customerRepository.findById(custId).orElseThrow(() -> new RecordNotFoundException("Customer ID Does Npot Exist")));
    }

    @Override
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public Customer update(long custId, Customer customer) {

        Customer customer1 = findById(custId).get();
        customer1.setCustName(customer.getCustName());
        customer1.setCustAddress(customer.getCustAddress());
        customer1.setCustAccountBalance(customer.getCustAccountBalance());
        customer1.setCustomerStatus(customer.getCustomerStatus());
        customer1.setCustContactNumber(customer.getCustContactNumber());
        customer1.setCustEmailId(customer.getCustEmailId());
        customer1.setCustUID(customer.getCustUID());
        customer1.setCustPanCard(customer.getCustPanCard());
        customer1.setCustDOB(customer.getCustDOB());
        customer1.setCustPassword(customer.getCustPassword());
        return customerRepository.save(customer1);
    }

    @Override
    public Customer changeContactNumber(long custAccountNumber, long custContactNumber) {

        Customer customer = customerRepository.findByCustAccountNumber(custAccountNumber).orElseThrow(() -> new RecordNotFoundException("Customer Account Number Does Not Exist"));

        customer.setCustContactNumber(custContactNumber);
        return customerRepository.save(customer);
    }

    @Override
    public Customer changeEmailId(long custAccountNumber, String custEmailId) {
        Customer customer = customerRepository.findByCustAccountNumber(custAccountNumber).orElseThrow(() -> new RecordNotFoundException("Customer Account Number Does Not Exist"));

        customer.setCustEmailId(custEmailId);
        return customerRepository.save(customer);
    }

    @Override
    public Customer depositAmount(long custAccountNumber, double amount) {

        Customer customer = customerRepository.findByCustAccountNumber(custAccountNumber).orElseThrow(() -> new RecordNotFoundException("Customer Account Number Not Exist"));

        if (amount <= 0) {
            throw new InvalidAmountException("Amount should not be 0 or -ve");
        }
        double existingAccountBalance = customer.getCustAccountBalance();

        existingAccountBalance += amount;

        customer.setCustAccountBalance(existingAccountBalance);

        return customerRepository.save(customer);
    }

    @Override
    public Customer withdrawAmount(long custAccountNumber, double amount) {


        Customer customer = customerRepository.findByCustAccountNumber(custAccountNumber).orElseThrow(() -> new RecordNotFoundException("Customer Account Number Not Exist"));

        if (amount <= 0) {
            throw new InvalidAmountException("Amount should not be 0 or -ve");
        }
        double existingAccountBalance = customer.getCustAccountBalance();

        if (existingAccountBalance >= amount) {
            existingAccountBalance -= amount;
        } else {
            throw new InsufficientFundException("Insufficient Fund");
        }

        customer.setCustAccountBalance(existingAccountBalance);

        return customerRepository.save(customer);
    }

    @Override
    public void verifyOTP(String custEmailId) {


        Customer customer = customerRepository.findByCustEmailId(custEmailId).orElseThrow(() -> new RecordNotFoundException("Customer Email ID Does Not Exist"));

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

            mimeMessageHelper.setFrom(fromEmail);


            mimeMessageHelper.setTo(customer.getCustEmailId());
            mimeMessageHelper.setSubject("OTP for Transfer Fund");
            mimeMessageHelper.setText("Hey Please use this OTP: " + generateOtp());

            otpStorage.put("OTP", generateOtp());
            javaMailSender.send(mimeMessage);

            log.info("Mail Sent Successfully");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    public Customer transferFund(long fromCustAccountNumber, long toCustAccountNumber, String otp, double amount) {


        Customer fromCustomer = customerRepository.findByCustAccountNumber(fromCustAccountNumber).orElseThrow(() -> new RecordNotFoundException("Customer Account Number Not Exist"));

        Customer toCustomer = customerRepository.findByCustAccountNumber(toCustAccountNumber).orElseThrow(() -> new RecordNotFoundException("Customer Account Number Not Exist"));


        String mainOTP = otpStorage.get("OTP");

        log.info("@@@@@@@ Main OTP: " + mainOTP);


        if (amount <= 0) {
            throw new InvalidAmountException("Amount should not be 0 or -ve");
        }
        double existingAccountBalance = fromCustomer.getCustAccountBalance();

        if (otp.equals(mainOTP)) {
            if (existingAccountBalance >= amount) {


                existingAccountBalance -= amount;

                fromCustomer.setCustAccountBalance(existingAccountBalance);
                customerRepository.save(fromCustomer);

                double existingAccountBalanceCustomer2 = toCustomer.getCustAccountBalance();


                existingAccountBalanceCustomer2 += amount;

                toCustomer.setCustAccountBalance(existingAccountBalanceCustomer2);

                customerRepository.save(toCustomer);


            } else {
                throw new InsufficientFundException("Insufficient Fund");
            }
        } else {

            throw new InvalidOTPException("Incorrect OTP");
        }


        return null;
    }

    @Override
    public Double checkAccBalance(long custAccountNumber) {

        Customer customer = customerRepository.findByCustAccountNumber(custAccountNumber).get();


        return customer.getCustAccountBalance();
    }

    public String generateOtp() {

        int otp = 1000 + secureRandom.nextInt(9000);

        return String.valueOf(otp);
    }
}
