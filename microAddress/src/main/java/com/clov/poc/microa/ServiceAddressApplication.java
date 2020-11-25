package com.clov.poc.microa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.clov.poc.microa.repo.IAddressRepo;
import com.clov.poc.microa.service.impl.AddressService;

@SpringBootApplication
public class ServiceAddressApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceAddressApplication.class, args);

	}

}
