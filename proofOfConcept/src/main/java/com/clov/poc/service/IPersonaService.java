package com.clov.poc.service;

import java.util.List;

import com.clov.poc.model.Person;

public interface IPersonaService {

	Person createPerson(Person person);
	
	Person updatePerson(Person person);
	
	List<Person> findAllPerson();
	
	Person findPersonById(int personId);
	
	void deleteById(int personId);

}
