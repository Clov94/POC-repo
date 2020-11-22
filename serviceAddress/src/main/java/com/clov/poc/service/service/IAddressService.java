package com.clov.poc.service.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;

import com.clov.poc.service.model.Address;

public interface IAddressService {
	
	Address createAddress(Address address);
	
	Address updateAddress(Address address);

	List<Address> findAll();
	
	Optional<Address> findAddressByCode(String code);

	Object deleteByCode(String code);
}
