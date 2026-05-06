package com.fullstack.service;

import com.fullstack.entity.Customer;
import com.fullstack.exception.RecordNotFoundException;
import com.fullstack.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerService implements ICustomerService {

    private final CustomerRepository customerRepository;

    @Override
    public Customer signUp(Customer customer) {
        //
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
}
