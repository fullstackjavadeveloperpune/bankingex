package com.fullstack.repository;

import com.fullstack.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    // Custom Methods

    Customer findByCustEmailIdAndCustPassword(String custEmailId, String custPassword);

    Optional<Customer> findByCustAccountNumber(long custAccountNumber);

    Optional<Customer> findByCustEmailId(String custEmailId);




}
