package com.payment.International.services;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.payment.International.externaldomain.CurrencyConection;

@Service
public class CurrencyService {
	
    public BigDecimal convertAmountToDollars(BigDecimal amount, String currency) throws Exception {
    	CurrencyConection currencyConection = new CurrencyConection();
    	if(currency==null ||currency.isEmpty() && amount == null || amount.compareTo(BigDecimal.ZERO) == 0) {
    		throw new Exception("No se permiten campos nulos o vacíos"); //403
    	}
        String jsonResponse = currencyConection.fetchDataFromApi(currency);
        Map<String, BigDecimal> currencyData = parseJsonResponse(jsonResponse);
        BigDecimal currencyAmount = currencyData.get(currency);

        if (currencyAmount == null) {
            throw new Exception("No se encontró la tasa de conversión para la moneda: " + currency);
        }
        BigDecimal result = amount.divide(currencyAmount,2,RoundingMode.HALF_UP);
        return result;
    }

    private Map<String, BigDecimal> parseJsonResponse(String jsonResponse) {
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, BigDecimal> currencyData = new HashMap<>();
        try {
            JsonNode jsonNode = objectMapper.readTree(jsonResponse);
            JsonNode dataNode = jsonNode.get("data");

            Iterator<Map.Entry<String, JsonNode>> fields = dataNode.fields();
            while (fields.hasNext()) {
                Map.Entry<String, JsonNode> entry = fields.next();
                BigDecimal value = new BigDecimal(entry.getValue().asText());
                currencyData.put(entry.getKey(), value);
            }
            return currencyData;
        } catch (IOException e) {
            throw new RuntimeException("Error parsing JSON response", e);
        }
    }
    
}
