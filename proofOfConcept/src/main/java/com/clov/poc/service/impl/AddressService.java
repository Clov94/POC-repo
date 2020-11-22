package com.clov.poc.service.impl;

import java.util.Arrays;
import java.util.List;

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
public class AddressService /*implements IAddressService*/{
	
	private final String URLService = "http://localhost:8090/service";
	
	private final RestTemplate restTemplate = new RestTemplate();
	


	public Address getAddressByCode(String code) {
		
		HttpHeaders requestHeaders = new HttpHeaders();

        requestHeaders.add("Accept", MediaType.APPLICATION_JSON_VALUE);

        //request entity is created with request headers
        HttpEntity<Void> requestEntity = new HttpEntity<>(requestHeaders);

		return restTemplate.exchange(URLService.concat("/getAddressesByCode/"+code), HttpMethod.GET, requestEntity, Address.class).getBody();
	}
	
	public List<Address> getAddresses(){
		
		HttpHeaders requestHeaders = new HttpHeaders();

        requestHeaders.add("Accept", MediaType.APPLICATION_JSON_VALUE);

        //request entity is created with request headers
        HttpEntity<Void> requestEntity = new HttpEntity<>(requestHeaders);
        
        Address addressList = restTemplate.exchange(URLService.concat("/getAddresses"), HttpMethod.GET, requestEntity, Address.class).getBody();
        
        return Arrays.asList(addressList);
		
	}
	
	/*
	 * 
	 * swagger
	 * separar servicios
	 * junit test
	 * 
	 * */
	
	
	/*@Autowired
	private IAddressRepo _addressRepo;
	
	@Override
	public Address createAddress(String code, String street, String city, String state, String streetNumber){

		return _addressRepo.findByCode(code)
				.orElse(_addressRepo.save(new Address(code, street, city, state, streetNumber)));
	}

	@Override
	public Address updateAddress(String code, String street, String city, String state, String streetNumber){

		return _addressRepo.findByCode(code)
				.orElse(_addressRepo.save(new Address(code, street, city, state, streetNumber)));
	}

	@Override
	public List<Address> findAll(){
		return _addressRepo.findAll();
	}
	
	public long total() {
		return _addressRepo.count();
	}

	@Override
	public Optional<Address> findAddressById(int addressId) {
		return _addressRepo.findById(addressId);
	}

	@Override
	public void deleteById(int addressId) {
		_addressRepo.deleteById(addressId);
	}*/

}
