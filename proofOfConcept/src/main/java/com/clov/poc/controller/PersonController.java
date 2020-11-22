package com.clov.poc.controller;

import static org.springframework.hateoas.server.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.ControllerLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.clov.poc.exception.ModelNotFoundException;
import com.clov.poc.model.Person;
import com.clov.poc.service.impl.PersonaService;

@RestController
@RequestMapping("/persons")
public class PersonController {
	
	@Autowired
	private PersonaService peronsaService;

	@PostMapping(produces = "application/json", consumes = "application/json")
	public ResponseEntity<Person> addPerson(@RequestBody @Validated Person person) {
		
		Person persona = peronsaService.createPerson(person);
		
		return new ResponseEntity<Person>(HttpStatus.CREATED);
	}
	
	@PutMapping("/uPersons")
	public ResponseEntity<Person> updatePerson(@RequestBody @Validated Person person) {
		
		Person uPerson = peronsaService.updatePerson(person);
		
		return new ResponseEntity<Person>(HttpStatus.OK);
	}
	
	@GetMapping("/gettingAll")
	public ResponseEntity<List<Person>> getPersons(){
		
		List<Person> persons = peronsaService.findAllPerson();
		
		return new ResponseEntity<List<Person>>(HttpStatus.OK);
	}
	
	@GetMapping("/getPersonById/{personId}")
	public EntityModel<Optional<Person>> findPersonById(@PathVariable(name = "personId") int personId) {
		
		Optional<Person> person = peronsaService.findPersonById(personId);
		
		return new EntityModel<Person>(HttpStatus.OK);
	}
	
	@DeleteMapping("/deleteById/{personId}")
	public ResponseEntity<Object> deleteById(@PathVariable("personId") int personId){
		
		peronsaService.deleteById(personId);
		
		return new ResponseEntity<Object>(HttpStatus.OK);
	}

}