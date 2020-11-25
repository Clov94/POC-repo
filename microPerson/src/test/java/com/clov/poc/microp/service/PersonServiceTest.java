package com.clov.poc.microp.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.clov.poc.microp.exception.ModelNotFoundException;
import com.clov.poc.microp.model.Person;
import com.clov.poc.microp.repo.IPersonRepo;
import com.clov.poc.microp.service.impl.AddressService;
import com.clov.poc.microp.service.impl.PersonaService;

import entity.Address;

@ExtendWith(MockitoExtension.class)
class PersonServiceTest {

	@InjectMocks
	private PersonaService personService;

	@InjectMocks
	private AddressService addressService;

	@Mock
	private IPersonRepo personRepo;

	@Test
	void testCreatePerson() {
		Person person = new Person();

		person.setId(1);
		person.setFirstName("1");
		person.setLastName("1");
		person.setAge("1");
		person.setBloodType("1");

		Address address = new Address();
		address.setCity("gdl");
		address.setCode(1);
		address.setPersonId(1);
		address.setState("jalisco");
		address.setStreet("madero");
		address.setStreetNumber("1");
		person.setAddresses(Arrays.asList(address));

		when(personRepo.save(person)).thenReturn(person);

		assertEquals(person, personService.createPerson(person));

	}

	@Test
	void testUpdatePerson() {

		Person person = new Person();

		person.setId(1);
		person.setFirstName("1");
		person.setLastName("1");
		person.setAge("1");
		person.setBloodType("1");

		Address address = new Address();
		address.setCity("gdl");
		address.setCode(1);
		address.setPersonId(1);
		address.setState("jalisco");
		address.setStreet("madero");
		address.setStreetNumber("1");
		person.setAddresses(Arrays.asList(address));

		when(personRepo.save(person)).thenReturn(person);

		assertEquals(person, personService.updatePerson(person));

	}

	@Test
	void testFinAll() {

		Person person = new Person();

		person.setId(1);
		person.setFirstName("1");
		person.setLastName("1");
		person.setAge("1");
		person.setBloodType("1");

		Address address = new Address();
		address.setCity("gdl");
		address.setCode(1);
		address.setPersonId(1);
		address.setState("jalisco");
		address.setStreet("madero");
		address.setStreetNumber("1");
//		person.setAddresses(Arrays.asList(address));

		when(personRepo.findAll()).thenReturn(Arrays.asList(person));
		assertEquals(person, personService.findAllPerson());

	}
	
	@Test
	void testFinAllWhenIsEmpty() {
		
		when(personRepo.findAll()).thenReturn(Collections.emptyList());
		assertThrows(ModelNotFoundException.class, () -> personService.findAllPerson());
	}
	
	@Test
	void testFindPersonById() {
		Person person = new Person();

		person.setId(1);
		person.setFirstName("1");
		person.setLastName("1");
		person.setAge("1");
		person.setBloodType("1");
		
		when(personRepo.findById(person.getId())).thenReturn(Optional.of(person));
		assertEquals(Optional.of(person), personService.findPersonById(person.getId()));
		
	}
	
	@Test
	void testFindPersonByIdWheIsNotPresent() {
		when(personRepo.findById(anyInt())).thenReturn(Optional.empty());
		assertThrows(ModelNotFoundException.class, () -> personService.findPersonById(1));
	}
	
	@Test
	void testDeleteById() {
		Person person = new Person();

		person.setId(1);
		person.setFirstName("1");
		person.setLastName("1");
		person.setAge("1");
		person.setBloodType("1");
		
		when(personRepo.findById(person.getId())).thenReturn(Optional.of(person));
		personService.deleteById(person.getId());
		
		verify(personRepo, times(1)).findById(anyInt());
//		verify(personRepo, times(1)).delete(any(Person.class));
		
	}
	
	@Test
	void testDeleteByIdCodeException() {
		
		when(personRepo.findById(anyInt())).thenReturn(Optional.empty());
		assertThrows(ModelNotFoundException.class, () -> personService.deleteById(1));
	}
}
