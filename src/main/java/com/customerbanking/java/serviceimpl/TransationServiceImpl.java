package com.customerbanking.java.serviceimpl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.customerbanking.java.dto.FundTransferRequestDto;
import com.customerbanking.java.dto.FundTransferResponseDto;
import com.customerbanking.java.dto.MobileTransferRequestDto;
import com.customerbanking.java.dto.MobileTransferResponseDto;
import com.customerbanking.java.entity.Account;
import com.customerbanking.java.entity.Customer;
import com.customerbanking.java.entity.Transaction;
import com.customerbanking.java.exception.AccountNotFoundException;
import com.customerbanking.java.repository.AccountRepository;
import com.customerbanking.java.repository.CustomerRepository;
import com.customerbanking.java.repository.TransactionRepository;
import com.customerbanking.java.service.TransactionService;

@Service
public class TransationServiceImpl implements TransactionService{

	@Autowired
	AccountRepository accountRepository;
	
	@Autowired
	TransactionRepository transactionRepository;
	
	@Autowired
	CustomerRepository customerRepository;
	
	
    @Override
    @Transactional
    
	public FundTransferResponseDto performFundTransfer(FundTransferRequestDto fundTransferRequestDto) {
		
		Optional<Account> fromAccount=accountRepository.findByAccountNumber(fundTransferRequestDto.getFromAccountNumber());
		Optional<Account> toAccount=accountRepository.findByAccountNumber(fundTransferRequestDto.getToAccountNumber());
		
		if(!fromAccount.isPresent()) {
			throw new AccountNotFoundException("Given FromAccount number"+fundTransferRequestDto.getFromAccountNumber()+" not exist");
		}
		else if(!toAccount.isPresent()) 
			throw new AccountNotFoundException("Given toAccount number"+fundTransferRequestDto.getToAccountNumber()+" not exist");
		else	
			balanceCalculation(fromAccount.get(),toAccount.get(),fundTransferRequestDto.getTransactionType(),fundTransferRequestDto.getAmount());
			Transaction transaction=new Transaction();
			transaction.setFromAccount(fundTransferRequestDto.getFromAccountNumber());
			transaction.setToAccount(fundTransferRequestDto.getToAccountNumber());
			transaction.setTransactionType(fundTransferRequestDto.getTransactionType());
			transaction.setTransactionDate(LocalDateTime.now());
			transaction.setAmount(fundTransferRequestDto.getAmount());
			transactionRepository.save(transaction);
			 FundTransferResponseDto fundTransferResponseDto=new FundTransferResponseDto();
			 BeanUtils.copyProperties(transaction,fundTransferResponseDto);
			return fundTransferResponseDto;		
			
			}
				
	 private void balanceCalculation(Account debitAccount,Account creditAccount,String transactionType, BigDecimal amount) {
		 debitAccount.setBalance(debitAccount.getBalance().subtract(amount));
		 creditAccount.setBalance(creditAccount.getBalance().add(amount));
		 accountRepository.save(debitAccount);
		 accountRepository.save(creditAccount);
	}

	@Override
	public String performGooglepayFundTransfer(
			MobileTransferRequestDto mobileTransferRequestDto) {
		// TODO Auto-generated method stub
		Optional <Customer> fromPhoneno=customerRepository.findByPhoneNo(mobileTransferRequestDto.getFromPhoneNumber().toString());
		Optional <Customer> toPhoneno=customerRepository.findByPhoneNo(mobileTransferRequestDto.getToPhoneNumber().toString());
		Optional<Account> fromAccount=accountRepository.findByCustomerId(fromPhoneno.get().getCustomerId());
		Optional<Account> toAccount=accountRepository.findByCustomerId(toPhoneno.get().getCustomerId());
		FundTransferRequestDto fundTransferRequestDto=new FundTransferRequestDto();
		fundTransferRequestDto.setFromAccountNumber(fromAccount.get().getAccountNumber());
		fundTransferRequestDto.setToAccountNumber(toAccount.get().getAccountNumber());
		fundTransferRequestDto.setAmount(mobileTransferRequestDto.getAmount());
		fundTransferRequestDto.setTransactionType(mobileTransferRequestDto.getTransactionType());
        performFundTransfer(fundTransferRequestDto);
        return "Success";
		
	}

	
	

}
