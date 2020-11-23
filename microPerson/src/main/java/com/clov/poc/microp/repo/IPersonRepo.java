package com.clov.poc.microp.repo;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.clov.poc.microp.model.Person;

@Repository
public interface IPersonRepo extends JpaRepository<Person, Integer> {
	
	Optional<Person> findByFirstName(String firstName);

}
