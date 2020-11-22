package com.clov.poc.service.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.clov.poc.service.model.Address;

@Repository
public interface IAddressRepo extends JpaRepository<Address, String> {
	
	Optional<Address> findByCode(String code);

	Optional<Address> findByCity(String city);
	
	Optional<Address> findByState(String state);
	
	Optional<Address> findByStreet(String street);
	
	Optional<Address> findByStreetNumber(String streetNumber);

}
