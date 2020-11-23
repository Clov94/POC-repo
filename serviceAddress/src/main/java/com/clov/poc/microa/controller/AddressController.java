package com.clov.poc.microa.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clov.poc.microa.exception.ModelNotFoundException;
import com.clov.poc.microa.model.Address;
import com.clov.poc.microa.service.impl.AddressService;

@RestController
@Validated
@RequestMapping("/service/address")
public class AddressController {

	@Autowired
	private AddressService addressService;

	@PostMapping(produces = "application/json", consumes = "application/json")
	public ResponseEntity<Address> addAddress(@RequestBody @Valid Address address) {

		Address addressAdded = addressService.createAddress(address);

		return new ResponseEntity<Address>(addressAdded, HttpStatus.CREATED);
	}

	@PutMapping
	public ResponseEntity<Address> updateAddress(@RequestBody @Valid Address address) {

		Address uAddress = addressService.updateAddress(address);
		
		return new ResponseEntity<Address>(uAddress, HttpStatus.ACCEPTED);
	}

	@GetMapping
	public ResponseEntity<List<Address>> getAddress() {
		
		List<Address> addressList = addressService.findAll();

		return new ResponseEntity<List<Address>>(addressList, HttpStatus.OK);
	}

	@GetMapping("/{code}")
	public ResponseEntity<Optional<Address>> findAddressByCode(@PathVariable(name = "code") String code) {

		Optional<Address> addressByCode = addressService.findAddressByCode(code);
		
		if (!addressByCode.isPresent()) {
			
			throw new ModelNotFoundException("Is not found the address with code: " + code);
		}		
		
		return new ResponseEntity<Optional<Address>>(addressByCode, HttpStatus.FOUND);
	}
	
	@GetMapping("/by-person/{personId}")
	public ResponseEntity<List<Address>> findAddressesByPersonId(@PathVariable(name = "personId") int personId) {

		List<Address> addressList = addressService.findByPersonId(personId);

		return new ResponseEntity<List<Address>>(addressList, HttpStatus.FOUND);
	}

	@DeleteMapping("/{code}")
	public ResponseEntity<Object> deleteByCode(@PathVariable("code") String code) {
		
		addressService.deleteByCode(code);
		
		return new ResponseEntity<Object>(HttpStatus.OK);
	}

}
