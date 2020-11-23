package com.clov.poc.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class clientService {
	
	private final String URL_SERVICE = "http://localhost:8091/service/persons";
	
	private final RestTemplate restTemplate = new RestTemplate();
	
	

}
