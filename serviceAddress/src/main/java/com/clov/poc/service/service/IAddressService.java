package com.clov.poc.service.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;

import com.clov.poc.service.model.Address;

public interface IAddressService {
	
	Address createAddress(String code, String street, String city, String state, String streetNumber);
	
	Address updateAddress(String code, String street, String city, String state, String streetNumber);

	List<Address> findAll();
	
	Optional<Address> findAddressByCode(String code);

	Object deleteByCode(String code);
}
