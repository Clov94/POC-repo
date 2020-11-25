package com.clov.poc.microp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import entity.Address;
import lombok.Data;

@Data
@Service
public class AddressService {

	private final String URLService = "http://localhost:8090/service/address/";

	@Autowired
	private RestTemplate restTemplate;

	public Address getAddressByCode(int code) {

		HttpHeaders requestHeaders = new HttpHeaders();

		requestHeaders.add("accept", MediaType.APPLICATION_JSON_VALUE);
		requestHeaders.add("Content-Type", MediaType.APPLICATION_JSON_VALUE);

		// request entity is created with request headers
		HttpEntity<Void> requestEntity = new HttpEntity<>(requestHeaders);

		return restTemplate.exchange(URLService + code, HttpMethod.GET, requestEntity, Address.class).getBody();
	}
	
	/*
	 * 
	 * estudiar sistemas de versionamiento
	 * csv/svn
	 * git/mercurial
	 * también estudiar funciones de encriptación
	 * programacion reactiva
	 * 
	 * */

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
				.exchange(URLService + "by-person/" + personId, HttpMethod.GET, requestEntity, type).getBody();

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

}
