package com.payment.International.services;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import com.payment.International.entities.PaymentRequest;

@Service
public class PaymentService {
	
	public String registerPayment( PaymentRequest paymentRequest) throws Exception {
//        paymentRequest.setId(1);
//        paymentRequest.setAmount(new BigDecimal("10000"));
//        paymentRequest.setAccountNumber("123456789");
//        paymentRequest.setCurrency("JPY");
        String currency = paymentRequest.getCurrency();
        BigDecimal amount = paymentRequest.getAmount();
        amountInDollars(paymentRequest,amount,currency);
        return paymentRequest.toString();

    }
	
	public static void amountInDollars(PaymentRequest paymentRequest, BigDecimal amount, String currency) throws Exception {
		CurrencyService currencyService=new CurrencyService();
		BigDecimal amountInDollar= currencyService.convertAmountToDollars(amount, currency);
		paymentRequest.setAmountInDollars(amountInDollar);	
	}

}
