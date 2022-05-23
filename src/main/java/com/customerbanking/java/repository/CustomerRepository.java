package com.customerbanking.java.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.customerbanking.java.entity.Account;
import com.customerbanking.java.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer>{
	
	Customer findByCustomerId(Integer customerId);
	Customer findByFirstName (String firstNamr);
	
	Optional<Customer> findByPhoneNo(String phoneNo);
	

}
