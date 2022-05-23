package com.customerbanking.java.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.customerbanking.java.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Long>{

	Optional<Account> findByAccountNumber(Long fromAccountNumber);
	
	@Query(value = "SELECT * FROM ACCOUNT where customer_id=?1",nativeQuery = true)
	Optional<Account> findByCustomerId(Integer customerid);

	


}
