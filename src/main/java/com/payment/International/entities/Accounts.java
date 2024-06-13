package com.payment.International.entities;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class Accounts {
	
	public Integer id;
	
	public Integer idPartner;
	
	public BigDecimal amount;

}
