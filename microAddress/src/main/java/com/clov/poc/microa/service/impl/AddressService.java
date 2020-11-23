package com.clov.poc.microa.service.impl;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.clov.poc.microa.exception.ModelNotFoundException;
import com.clov.poc.microa.model.Address;
import com.clov.poc.microa.repo.IAddressRepo;
import com.clov.poc.microa.service.IAddressService;

@Service
public class AddressService implements IAddressService {

	@Autowired
	public IAddressRepo addressRepo;

	@Override
	public Address createAddress(Address address) {
		return addressRepo.save(address);
	}

	@Override
	public Address updateAddress(Address address) {
		return addressRepo.save(address);
	}

	@Override
	public List<Address> findAll() {

		if (addressRepo.findAll().isEmpty()) {
			
			throw new ModelNotFoundException("There is no address entity found!");			
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
	public List<Address> findByPersonId(int personId){
		return addressRepo.findByPersonId(personId);
	}
	

	@Override
	public void deleteByCode(String code) {
		
		Optional<Address> entity = addressRepo.findByCode(code);

		if (!entity.isPresent()) {
			
			throw new ModelNotFoundException("impossible to delete the code: " + code + ", it does not found!");
		}
		
		addressRepo.delete(entity.get());

	}

}
