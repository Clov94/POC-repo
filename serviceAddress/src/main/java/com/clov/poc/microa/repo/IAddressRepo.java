package com.clov.poc.microa.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.clov.poc.microa.model.Address;

@Repository
public interface IAddressRepo extends JpaRepository<Address, String> {
	
	Optional<Address> findByCode(String code);

	List<Address> findByPersonId(int personId);

}
