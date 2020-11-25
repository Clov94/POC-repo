package com.clov.poc.microp.controller;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.clov.poc.microp.model.Person;
import com.clov.poc.microp.service.impl.PersonaService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

class PersonControllerTest {

	private MockMvc mockMvc;

	@Mock
	private PersonaService personService;

	@InjectMocks
	private PersonController personController;

	@BeforeEach
	void init() {
		MockitoAnnotations.initMocks(this);

		mockMvc = MockMvcBuilders.standaloneSetup(personController).build();

	}

	ObjectMapper oMapper = new ObjectMapper();

	@Test
	void testAddPerson() throws JsonProcessingException, Exception {

		Person person = new Person();

		person.setId(1);
		person.setFirstName("1");
		person.setLastName("1");
		person.setAge("1");
		person.setBloodType("1");

		when(personService.createPerson(person)).thenReturn(person);

		this.mockMvc
				.perform(post("/service/persons").contentType(MediaType.APPLICATION_JSON)
						.content(oMapper.writeValueAsString(person)))
				.andExpect(status().isCreated()).andDo(print())
				.andExpect(content().json(oMapper.writeValueAsString(person)));
	}

	@Test
	void testUpdatePerson() throws JsonProcessingException, Exception {

		Person person = new Person();

		person.setId(1);
		person.setFirstName("1");
		person.setLastName("1");
		person.setAge("1");
		person.setBloodType("1");

		when(personService.updatePerson(person)).thenReturn(person);

		this.mockMvc
				.perform(put("/service/persons").contentType(MediaType.APPLICATION_JSON)
						.content(oMapper.writeValueAsString(person)))
				.andExpect(status().isOk()).andDo(print())
				.andExpect(content().json(oMapper.writeValueAsString(person)));
	}

	@Test
	void testGetPersons() throws JsonProcessingException, Exception {
		Person person = new Person();

		person.setId(1);
		person.setFirstName("1");
		person.setLastName("1");
		person.setAge("1");
		person.setBloodType("1");

		when(personService.findAllPerson()).thenReturn(Arrays.asList(person));

		this.mockMvc.perform(get("/service/persons").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andDo(print()).andExpect(content().json(oMapper.writeValueAsString(Arrays.asList(person))));
	}

	@Test
	void testFindPersonById() throws JsonProcessingException, Exception {
		Person person = new Person();

		person.setId(1);
		person.setFirstName("1");
		person.setLastName("1");
		person.setAge("1");
		person.setBloodType("1");

		when(personService.findPersonById(person.getId())).thenReturn(Optional.of(person));

		this.mockMvc.perform(get("/service/persons/{personId}", person.getId()).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andDo(print())
				.andExpect(content().json(oMapper.writeValueAsString(Optional.of(person))));

	}
	
	@Test
	void testDeleteById() throws JsonProcessingException, Exception {
		Person person = new Person();

		person.setId(1);
		person.setFirstName("1");
		person.setLastName("1");
		person.setAge("1");
		person.setBloodType("1");
		
		doNothing().when(personService).deleteById(person.getId());
		
		this.mockMvc
				.perform(delete("/service/persons/{personId}", person.getId()).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andDo(print())
				.andExpect(content().json(oMapper.writeValueAsString(person)));
	}

}
