package com.clov.poc.service.service.impl;

import static org.springframework.hateoas.server.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.ControllerLinkBuilder.methodOn;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.clov.poc.service.exception.ModelNotFoundException;
import com.clov.poc.service.model.Address;
import com.clov.poc.service.repo.IAddressRepo;
import com.clov.poc.service.service.IAddressService;

@Service
public class AddressService implements IAddressService {

	@Autowired
	public IAddressRepo addressRepo;

	@Override
	public Address createAddress(String code, String street, String city, String state, String streetNumber) {

		Address entity = new Address();
		entity.setCity(city);
		entity.setCode(code);
		entity.setStreet(street);
		entity.setState(state);
		entity.setStreetNumber(streetNumber);

		return addressRepo.save(entity);
	}

	@Override
	public Address updateAddress(String code, String street, String city, String state, String streetNumber) {

		Address entity = new Address();
		entity.setCity(city);
		entity.setCode(code);
		entity.setStreet(street);
		entity.setState(state);
		entity.setStreetNumber(streetNumber);

		return addressRepo.save(entity);

	}

	@Override
	public List<Address> findAll() {

		if (addressRepo.count() == 0) {
			
			throw new ModelNotFoundException("There is no entity found!");			
		} 
		
		return addressRepo.findAll();
	}

	@Override
	public Optional<Address> findAddressByCode(String code) {

		Optional<Address> address = addressRepo.findByCode(code);

		if (!address.isPresent()) {

			throw new ModelNotFoundException("the following code: " + code + " is not found!");
		}
		
		return address;
	}

	@Override
	public Object deleteByCode(String code) {
		
		Optional<Address> address = addressRepo.findByCode(code);

		if (!address.isPresent()) {
			
			throw new ModelNotFoundException("impossible to delete the code: " + code + ", it does not found!");
		}
		
		return addressRepo.delete(address);

	}

}
