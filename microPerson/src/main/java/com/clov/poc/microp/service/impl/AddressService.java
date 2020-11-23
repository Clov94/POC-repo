package com.clov.poc.microp.service.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import entity.Address;

@Service
public class AddressService /* implements IAddressService */ {

	private final String URLService = "http://localhost:8090/service/address";

	private final RestTemplate restTemplate = new RestTemplate();

	public Address getAddressByCode(String code) {

		HttpHeaders requestHeaders = new HttpHeaders();

		requestHeaders.add("accept", MediaType.APPLICATION_JSON_VALUE);
		requestHeaders.add("Content-Type", MediaType.APPLICATION_JSON_VALUE);

		// request entity is created with request headers
		HttpEntity<Void> requestEntity = new HttpEntity<>(requestHeaders);

		return restTemplate.exchange(URLService.concat("/" + code), HttpMethod.GET, requestEntity, Address.class)
				.getBody();
	}

	public List<Address> getAddresses() {

		HttpHeaders requestHeaders = new HttpHeaders();

		requestHeaders.add("accept", MediaType.APPLICATION_JSON_VALUE);
		requestHeaders.add("Content-Type", MediaType.APPLICATION_JSON_VALUE);

		// request entity is created with request headers
		HttpEntity<Void> requestEntity = new HttpEntity<>(requestHeaders);

		final ParameterizedTypeReference<List<Address>> type = new ParameterizedTypeReference<List<Address>>() {
		};

		final List<Address> addressList = restTemplate.exchange(URLService, HttpMethod.GET, requestEntity, type)
				.getBody();

		return addressList;

	}

	public List<Address> getAddressesByPersonId(int personId) {

		HttpHeaders requestHeaders = new HttpHeaders();

		requestHeaders.add("accept", MediaType.APPLICATION_JSON_VALUE);
		requestHeaders.add("Content-Type", MediaType.APPLICATION_JSON_VALUE);

		// request entity is created with request headers
		HttpEntity<Void> requestEntity = new HttpEntity<>(requestHeaders);

		final ParameterizedTypeReference<List<Address>> type = new ParameterizedTypeReference<List<Address>>() {
		};

		final List<Address> addressList = restTemplate
				.exchange(URLService + "/by-person/" + personId, HttpMethod.GET, requestEntity, type).getBody();

		return addressList;

	}

	public Address uAddress(Address address) {

		HttpHeaders requestHeaders = new HttpHeaders();

		requestHeaders.add("accept", MediaType.APPLICATION_JSON_VALUE);
		requestHeaders.add("Content-Type", MediaType.APPLICATION_JSON_VALUE);

		HttpEntity<Address> requestEntity = new HttpEntity<Address>(requestHeaders);

		return restTemplate.exchange(URLService, HttpMethod.PUT, requestEntity, Address.class).getBody();

	}

	public Address addAddress(Address address) {

		HttpHeaders requestHeaders = new HttpHeaders();

		requestHeaders.add("accept", MediaType.APPLICATION_JSON_VALUE);
		requestHeaders.add("Content-Type", MediaType.APPLICATION_JSON_VALUE);

		HttpEntity<Address> requestEntity = new HttpEntity<Address>(requestHeaders);

		return restTemplate.exchange(URLService, HttpMethod.PUT, requestEntity, Address.class).getBody();
	}

	/*
	 * 
	 * swagger separar servicios junit test programacion reactiva
	 * 
	 */

}
