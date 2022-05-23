package com.customerbanking.java.serviceimpl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.customerbanking.java.dto.CustomerRequestDto;
import com.customerbanking.java.dto.CustomerResponseDto;
import com.customerbanking.java.entity.Account;
import com.customerbanking.java.entity.Customer;
import com.customerbanking.java.repository.CustomerRepository;
import com.customerbanking.java.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService{

	@Autowired
	CustomerRepository customerRepository;
	@Override
	public CustomerResponseDto saveCustomer(CustomerRequestDto customerRequestDto) {
		// TODO Auto-generated method stub
		Customer customer=new Customer();
		BeanUtils.copyProperties(customerRequestDto, customer);
		Account account=new Account();
		account.setAccountType("saving");
		account.setBalance(BigDecimal.valueOf(20000));
		account.setCustomer(customer);
		customer.setAccount(new ArrayList<Account>(Arrays.asList(account)));
		Customer mainuser=customerRepository.save(customer);
		CustomerResponseDto customerResponseDto=new CustomerResponseDto();
		BeanUtils.copyProperties(mainuser, customerResponseDto);
		return customerResponseDto; 
	}

}
