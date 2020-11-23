package com.clov.poc.microa.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;

import com.clov.poc.microa.model.Address;

public interface IAddressService {
	
	Address createAddress(Address address);
	
	Address updateAddress(Address address);

	List<Address> findAll();
	
	Optional<Address> findAddressByCode(String code);
	
	List<Address> findByPersonId(int personId);

	void deleteByCode(String code);
}
