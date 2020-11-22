package com.clov.poc.service.impl;

import static org.springframework.hateoas.server.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.ControllerLinkBuilder.methodOn;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.ControllerLinkBuilder;
import org.springframework.stereotype.Service;

import com.clov.poc.exception.ModelNotFoundException;
import com.clov.poc.model.Person;
import com.clov.poc.repo.IPersonRepo;
import com.clov.poc.service.IPersonaService;

@Service
public class PersonaService implements IPersonaService{
	
	@Autowired
	private IPersonRepo personaRepo;
	
	@Autowired
	private AddressService addressService;

	@Override
	public Person createPerson(Person person) {
		
		Person entity = new Person();
		entity.setFirstName(person.getFirstName());
		entity.setLastName(person.getLastName());
		entity.setAge(person.getAge());
		entity.setBloodType(person.getBloodType());
		entity.setAddress(addressService.getAddresses());
		
		return personaRepo.save(entity);
	}

	@Override
	public Person  updatePerson(Person person) {
		Person entity = new Person();
		entity.setFirstName(person.getFirstName());
		entity.setLastName(person.getLastName());
		entity.setAge(person.getAge());
		entity.setBloodType(person.getBloodType());
		entity.setAddress(addressService.getAddresses());
		
		return personaRepo.save(entity);
	}


	@Override
	public List<Person> findAllPerson(){
		
		if (personaRepo.findAll().isEmpty()) {
			
			throw new ModelNotFoundException("There is no person entity found!");		
		}
		
		return personaRepo.findAll();
	}

	@Override
	public Optional<Person> findPersonById(int personId) {
		Optional<Person> person = personaRepo.findById(personId);
		
		if(!person.isPresent()) {
			
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
