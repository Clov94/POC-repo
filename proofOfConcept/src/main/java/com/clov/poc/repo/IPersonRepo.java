package com.clov.poc.repo;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.springframework.stereotype.Repository;

import com.clov.poc.model.Person;

//@Repository
@EnableJpaRepositories
public interface IPersonRepo extends JpaRepository<Person, Integer> {
	
	Optional<Person> findByFirstName(String firstName);

}
