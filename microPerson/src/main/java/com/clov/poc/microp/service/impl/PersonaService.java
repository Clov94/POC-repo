package com.clov.poc.microp.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clov.poc.microp.exception.ModelNotFoundException;
import com.clov.poc.microp.model.Person;
import com.clov.poc.microp.repo.IPersonRepo;
import com.clov.poc.microp.service.IPersonaService;

@Service
public class PersonaService implements IPersonaService {

	@Autowired
	private IPersonRepo personaRepo;

	@Autowired
	private AddressService addressService;

	@Override
	public Person createPerson(Person person) {
		return personaRepo.save(person);
	}

	@Override	
	public Person updatePerson(Person person) {
		return personaRepo.save(person);
	}

	@Override
	public List<Person> findAllPerson() {

		List<Person> persons = personaRepo.findAll();

		if (persons.isEmpty()) {

			throw new ModelNotFoundException("There is no person entity found!");
		}

		return persons.stream().map(person -> {
			person.setAddresses(addressService.getAddressesByPersonId(person.getId()));
			return person;
		}).collect(Collectors.toList());

	}

	@Override
	public Optional<Person> findPersonById(int personId) {
		Optional<Person> person = personaRepo.findById(personId);

		if (!person.isPresent()) {

			throw new ModelNotFoundException("person" + personId + " id, not found");
		}

		return person;
	}

	@Override
	public void deleteById(int personId) {

		Optional<Person> person = personaRepo.findById(personId);

		if (!person.isPresent()) {

			throw new ModelNotFoundException("there is no " + personId + " id person found to delete");
		}

		personaRepo.deleteById(personId);
	}

}
