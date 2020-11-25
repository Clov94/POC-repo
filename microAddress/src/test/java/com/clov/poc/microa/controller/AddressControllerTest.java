package com.clov.poc.microa.controller;

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

import com.clov.poc.microa.model.Address;
import com.clov.poc.microa.service.impl.AddressService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

class AddressControllerTest {

	private MockMvc mockMvc;

	@Mock
	private AddressService addressService;

	@InjectMocks
	private AddressController addressController;

	@BeforeEach
	void init() {
		MockitoAnnotations.initMocks(this);

		mockMvc = MockMvcBuilders.standaloneSetup(addressController).build();
	}

	ObjectMapper mapper = new ObjectMapper();

	@Test
	void testAddAddress() throws Exception {
		Address address = new Address();
		address.setCity("gdl");
		address.setCode(1);
		address.setPersonId(1000);
		address.setState("jalisco");
		address.setStreet("madero");
		address.setStreetNumber("1");

		when(addressService.createAddress(address)).thenReturn(address);

		this.mockMvc
				.perform(post("/service/address").contentType(MediaType.APPLICATION_JSON)
						.content(mapper.writeValueAsString(address))) // RequestBody
				.andExpect(status().isCreated()).andDo(print())
				.andExpect(content().json(mapper.writeValueAsString(address)));
//		status code most used and why, and joke status code.

	}

	@Test
	void testUpdateAddress() throws JsonProcessingException, Exception {

		Address address = new Address();
		address.setCity("gdl");
		address.setCode(1);
		address.setPersonId(1);
		address.setState("jalisco");
		address.setStreet("madero");
		address.setStreetNumber("1");

		when(addressService.updateAddress(address)).thenReturn(address);

		this.mockMvc
				.perform(put("/service/address").contentType(MediaType.APPLICATION_JSON)
						.content(mapper.writeValueAsString(address)))
				.andExpect(status().isOk()).andDo(print())
				.andExpect(content().json(mapper.writeValueAsString(address)));

	}

	@Test
	void testGetAddress() throws JsonProcessingException, Exception {
		Address address = new Address();
		address.setCity("gdl");
		address.setCode(1);
		address.setPersonId(1);
		address.setState("jalisco");
		address.setStreet("madero");
		address.setStreetNumber("1");

		when(addressService.findAll()).thenReturn(Arrays.asList(address));

		this.mockMvc.perform(get("/service/address").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andDo(print()).andExpect(content().json(mapper.writeValueAsString(Arrays.asList(address))));
	}

	@Test
	void testFindAddressByCode() throws JsonProcessingException, Exception {
		Address address = new Address();
		address.setCity("gdl");
		address.setCode(1);
		address.setPersonId(1);
		address.setState("jalisco");
		address.setStreet("madero");
		address.setStreetNumber("1");

		when(addressService.findAddressByCode(address.getCode())).thenReturn(Optional.of(address));

		this.mockMvc.perform(get("/service/address/" + address.getCode()).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andDo(print())
				.andExpect(content().json(mapper.writeValueAsString(address)));
	}

	@Test
	void testFindAddressesByPersonId() throws JsonProcessingException, Exception {
		Address address = new Address();
		address.setCity("gdl");
		address.setCode(1);
		address.setPersonId(1);
		address.setState("jalisco");
		address.setStreet("madero");
		address.setStreetNumber("1");

		when(addressService.findByPersonId(address.getPersonId())).thenReturn(Arrays.asList(address));

		this.mockMvc
				.perform(get("/service/address/by-person/" + address.getPersonId())
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andDo(print())
				.andExpect(content().json(mapper.writeValueAsString(Arrays.asList(address))));
	}

	@Test
	void testDeleteByCode() throws JsonProcessingException, Exception {
		Address address = new Address();
		address.setCity("gdl");
		address.setCode(1);
		address.setPersonId(1);
		address.setState("jalisco");
		address.setStreet("madero");
		address.setStreetNumber("1");

		doNothing().when(addressService).deleteByCode(address.getCode());

		this.mockMvc
				.perform(delete("/service/address/{code}", address.getCode()).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andDo(print())
				.andExpect(content().json(mapper.writeValueAsString(address)));

//		verify(addressService, times(1)).deleteByCode(address.getCode());

	}

}
