package com.clov.poc.microp.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.clov.poc.microp.model.Person;
import com.clov.poc.microp.service.impl.PersonaService;

@RestController
@RequestMapping("/service/persons")
public class PersonController {
	
	@Autowired
	private PersonaService peronsaService;

	@PostMapping(produces = "application/json", consumes = "application/json")
	public ResponseEntity<Person> addPerson(@RequestBody @Validated Person person) {
		
		Person persona = peronsaService.createPerson(person);
		
		return new ResponseEntity<Person>(persona, HttpStatus.CREATED);
	}
	
	@PutMapping
	public ResponseEntity<Person> updatePerson(@RequestBody @Validated Person person) {
		
		Person uPerson = peronsaService.updatePerson(person);
		
		return new ResponseEntity<Person>(uPerson, HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<List<Person>> getPersons(){
		
		List<Person> persons = peronsaService.findAllPerson();
		
		return new ResponseEntity<List<Person>>(persons, HttpStatus.OK);
	}
	
	@GetMapping("/{personId}")
	public ResponseEntity<Person> findPersonById(@PathVariable(name = "personId") int personId) {
		
		Optional<Person> person = peronsaService.findPersonById(personId);
		
		return new ResponseEntity<Person>(person.get(), HttpStatus.OK);
	}
	
	@DeleteMapping("/{personId}")
	public ResponseEntity<Void> deleteById(@PathVariable("personId") int personId){
		
		peronsaService.deleteById(personId);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}

}