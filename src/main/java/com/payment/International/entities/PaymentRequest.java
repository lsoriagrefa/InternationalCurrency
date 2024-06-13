package com.payment.International.entities;

import java.math.BigDecimal;
import com.payment.International.services.CurrencyService;

import lombok.Data;
import lombok.NonNull;

@Data
public class PaymentRequest {
	
	@NonNull
    public Integer id;

	@NonNull
    public BigDecimal amount;

	@NonNull
    public String accountNumber;

	@NonNull
    public String currency;
    
	@NonNull
    public BigDecimal amountInDollars;
    
	
	public PaymentRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PaymentRequest(Integer id, BigDecimal amount, String accountNumber, String currency,
			BigDecimal amountInDollars, CurrencyService currencyService) {
		super();
		this.id = id;
		this.amount = amount;
		this.accountNumber = accountNumber;
		this.currency = currency;
		this.amountInDollars = amountInDollars;
	}

	@Override
	public String toString() {
		return "PaymentRequest [id=" + id + ", amount=" + amount + ", accountNumber=" + accountNumber + ", currency="
				+ currency + ", amountInDollars=" + amountInDollars + "]";
	}
}
