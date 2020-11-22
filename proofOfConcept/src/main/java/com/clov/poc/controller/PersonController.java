package com.clov.poc.controller;

import static org.springframework.hateoas.server.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.ControllerLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
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
	private PersonaService _peronsaService;

	@PostMapping(produces = "application/json", consumes = "application/json")
	public ResponseEntity<Person> addPerson(@RequestBody @Validated Person person) {
		Person persona = _peronsaService.createPerson(person);
		
		URI localUri = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{personId}")
				.buildAndExpand(persona.getid())
				.toUri();
		return ResponseEntity.created(localUri).build();
	}
	
	@PutMapping
	public ResponseEntity<Person> updatePerson(@RequestBody @Validated Person person) {
		_peronsaService.updatePerson(person);
		return new ResponseEntity<Person>(HttpStatus.OK);
	}
	
	@GetMapping("/gettingAll")

	public ResponseEntity<List<Person>> getPersons(){
		
		List<Person> personasList = null;
		try {
			
			if (_peronsaService.total() != 0) {
				personasList = _peronsaService.findAllPerson();
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.getMessage();
			e.printStackTrace();
		}
		
		return new ResponseEntity<List<Person>>(HttpStatus.OK);
	}
	
	@GetMapping("/personId")
	public EntityModel<Person> findPersonById(@PathVariable(name = "personId") int personId) {		
		Person person = _peronsaService.findPersonById(personId);
		
		if(person.equals(null)) {
			throw new ModelNotFoundException("person" + personId + " id, not found");
		}
		
		EntityModel<Person> resource = new EntityModel<Person>(person);
		ControllerLinkBuilder linkBuilder = linkTo(methodOn(this.getClass()).findPersonById(personId));
		resource.add(linkBuilder.withRel("person-resource"));
		
		return resource;
	}
	
	public ResponseEntity<Object> deleteById(@PathVariable("personId") int personId){
		Person person = _peronsaService.findPersonById(personId);
		
		if (person.equals(null)) {
			throw new ModelNotFoundException("there is no " + personId + " id person found to delete");
		}
		
		return new ResponseEntity<Object>(HttpStatus.OK);
	}

}