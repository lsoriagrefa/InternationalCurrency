package com.payment.International.controller;

import com.payment.International.entities.PaymentRequest;
import com.payment.International.services.PaymentService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment")
public class PaymentController {
	
	private static final Logger logger= LoggerFactory.getLogger(PaymentController.class);
	
	private final PaymentService paymentService;
	
	@Autowired
	public PaymentController(PaymentService paymentService) {
		this.paymentService=paymentService;
	}
	
    @GetMapping("/isalive")
    public String isalive(){
        return "OK";
    }

    @PostMapping("/pay")
    public ResponseEntity<String> pay(@RequestBody PaymentRequest paymentRequest) throws Exception{
    	logger.info("Received payment request: {}", paymentRequest);
    	try {
        	String result =paymentService.registerPayment(paymentRequest);
        	logger.info("Payment response: {}", result);
            return ResponseEntity.ok(result);
    	}catch (Exception e) {
    		return ResponseEntity.status(405).body(e.getMessage());
		}

    }

//    @GetMapping("/pay")
//    public String payhget(@RequestBody PaymentRequest paymentRequest){
//        return paymentRequest.toString();
//    }
    
    /*@GetMapping("/test")
    public String test(){
    	CurrencyConection currencyConection= new CurrencyConection();
        return currencyConection.fetchDataFromApi();
    }*/

}
