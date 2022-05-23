package com.customerbanking.java.dto;

import java.math.BigDecimal;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class MobileTransferRequestDto {
	
	@NotNull(message = "Balance to be sent cannot Empty")
	private BigDecimal amount;
	@NotNull(message = "Please provide the From  Phone Number")
	private Long fromPhoneNumber;
	@NotNull(message = "Please provide ToPhone Number")
	private Long toPhoneNumber;
	@NotNull(message = "Please provide the Transaction Type")
	private String transactionType;
	
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public Long getFromPhoneNumber() {
		return fromPhoneNumber;
	}
	public void setFromPhoneNumber(Long fromPhoneNumber) {
		this.fromPhoneNumber = fromPhoneNumber;
	}
	public Long getToPhoneNumber() {
		return toPhoneNumber;
	}
	public void setToPhoneNumber(Long toPhoneNumber) {
		this.toPhoneNumber = toPhoneNumber;
	}
	public String getTransactionType() {
		return transactionType;
	}
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}
	
	

}
