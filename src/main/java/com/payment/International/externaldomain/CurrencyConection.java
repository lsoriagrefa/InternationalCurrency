package com.payment.International.externaldomain;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class CurrencyConection {
	
    private static final String apiUrl = "https://api.freecurrencyapi.com/v1/latest";
    private static final String apiKey = "fca_live_CJ8BozC2jjmlrmHifkmpU6Qy2GQwfJdk59iCpXOK";

    public String fetchDataFromApi(String currency) {
    	RestTemplate restTemplate = new RestTemplate();
        String url = UriComponentsBuilder.fromUriString(apiUrl)
                .queryParam("apikey", apiKey)
                .queryParam("currencies", currency)
                .toUriString();

        return restTemplate.getForObject(url, String.class);
    }
    
}
