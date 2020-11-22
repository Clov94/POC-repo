package com.clov.poc.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clov.poc.model.Person;
import com.clov.poc.repo.IPersonRepo;
import com.clov.poc.service.IPersonaService;

@Service
public class PersonaService implements IPersonaService{
	
	@Autowired
	private IPersonRepo _personaRepo;

	@Override
	public Person createPerson(Person person) {
		return _personaRepo.save(person);
	}

	@Override
	public Person  updatePerson(Person person) {
		return _personaRepo.save(person);
	}

	public long total() {
		return _personaRepo.count();
	}

	@Override
	public List<Person> findAllPerson(){
		return _personaRepo.findAll();
	}

	@Override
	public Person findPersonById(int personId) {
		 return _personaRepo.getOne(personId);
	}

	@Override
	public void deleteById(int personId) {
		_personaRepo.deleteById(personId);
	}

}
