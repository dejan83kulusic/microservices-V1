package com.example;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class CurrencyConversionControler {
	
	@Autowired
	private CurrencyExchangeServiceProxy proxy;
	
	
	@GetMapping("/currency-conversion/from/{from}/to/{to}/quantity/{quantity}")
	private CurrencyConversionBean convertCurrency(@PathVariable String from, @PathVariable String to,
													@PathVariable BigDecimal quantity ) {
		
		
		Map<String,String> uriVarialbles=new HashMap<String,String>();
		uriVarialbles.put("from", from);
		uriVarialbles.put("to", to);
		ResponseEntity<CurrencyConversionBean>  responseEntity =
				new RestTemplate().getForEntity("http://localhost:8000/currency-exchange/from/{from}/to/{to}",
				CurrencyConversionBean.class, uriVarialbles);
		CurrencyConversionBean response= responseEntity.getBody();
		
		return new CurrencyConversionBean(1L, from, to, response.getConversionMultiple(), quantity, 
				quantity.multiply(response.getConversionMultiple()), 0);
		
	}

	@GetMapping("/currency-conversion-feign/from/{from}/to/{to}/quantity/{quantity}")
	private CurrencyConversionBean convertCurrencyFeign(@PathVariable String from, @PathVariable String to,
													@PathVariable BigDecimal quantity ) {
		
		
		CurrencyConversionBean response= proxy.retirveExchangeVlaue(from, to);
		
		return new CurrencyConversionBean(1L, from, to, response.getConversionMultiple(), quantity, 
				quantity.multiply(response.getConversionMultiple()), 0);
		
	}
}
