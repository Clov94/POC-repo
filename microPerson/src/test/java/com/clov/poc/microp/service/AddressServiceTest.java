package com.clov.poc.microp.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.clov.poc.microp.service.impl.AddressService;

import entity.Address;

@ExtendWith(MockitoExtension.class)
public class AddressServiceTest {

	@Mock
	private RestTemplate restTemplate;

	@InjectMocks
	private AddressService addressService;

	@Test
	void testGetAddressByCode() {

		when(restTemplate.exchange(anyString(), any(HttpMethod.class), any(HttpEntity.class), eq(Address.class)))
				.thenReturn(new ResponseEntity<>(new Address(), HttpStatus.OK));

		assertNotNull(addressService.getAddressByCode(1));
		verify(restTemplate, times(1)).exchange(anyString(), any(HttpMethod.class), any(HttpEntity.class),
				eq(Address.class));
	}

	@Test
	@SuppressWarnings("unchecked")
	void testGetAddresses() {

		when(restTemplate.exchange(anyString(), any(HttpMethod.class), any(HttpEntity.class),
				any(ParameterizedTypeReference.class)))
						.thenReturn(new ResponseEntity<>(Arrays.asList(new Address()), HttpStatus.OK));

		assertNotNull(addressService.getAddresses());
		verify(restTemplate, times(1)).exchange(anyString(), any(HttpMethod.class), any(HttpEntity.class),
				any(ParameterizedTypeReference.class));
	}

	@Test
	@SuppressWarnings("unchecked")
	void testGetAddressesByPersonId() {
		when(restTemplate.exchange(anyString(), any(HttpMethod.class), any(HttpEntity.class),
				any(ParameterizedTypeReference.class)))
						.thenReturn(new ResponseEntity<>(Arrays.asList(new Address()), HttpStatus.OK));

		assertNotNull(addressService.getAddressesByPersonId(1));
		verify(restTemplate, times(1)).exchange(anyString(), any(HttpMethod.class), any(HttpEntity.class),
				any(ParameterizedTypeReference.class));
	}

	@Test
	void testAddAddress() {
		when(restTemplate.exchange(anyString(), any(HttpMethod.class), any(HttpEntity.class), eq(Address.class)))
				.thenReturn(new ResponseEntity<>(new Address(), HttpStatus.OK));

		assertNotNull(addressService.addAddress(new Address()));
		verify(restTemplate, times(1)).exchange(anyString(), any(HttpMethod.class), any(HttpEntity.class),
				eq(Address.class));
	}

	@Test
	void testUAddress() {

		when(restTemplate.exchange(anyString(), any(HttpMethod.class), any(HttpEntity.class), eq(Address.class)))
				.thenReturn(new ResponseEntity<>(new Address(), HttpStatus.OK));

		assertNotNull(addressService.uAddress(new Address()));
		verify(restTemplate, times(1)).exchange(anyString(), any(HttpMethod.class), any(HttpEntity.class),
				eq(Address.class));
	}

}
