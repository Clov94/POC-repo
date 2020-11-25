package com.clov.poc.microa.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.annotation.Rollback;

import com.clov.poc.microa.exception.ModelNotFoundException;
import com.clov.poc.microa.model.Address;
import com.clov.poc.microa.repo.IAddressRepo;

@ExtendWith(MockitoExtension.class)
class AddressServiceTest {

	@InjectMocks
	private AddressService addressService;

	@Mock
	private IAddressRepo addressRepo;

	@Test
	void testCreateAddress() {

		Address address = new Address();
		address.setCity("gdl");
		address.setCode(1);
		address.setPersonId(1);
		address.setState("jalisco");
		address.setStreet("madero");
		address.setStreetNumber("1");

		when(addressRepo.save(any(Address.class))).thenReturn(address);

		assertEquals(address, addressService.createAddress(address));
	}

	@Test
	void testUpdateAddress() {

		Address address = new Address();
		address.setCity("gdl");
		address.setCode(1);
		address.setPersonId(1);
		address.setState("jalisco");
		address.setStreet("madero");
		address.setStreetNumber("1");

		when(addressRepo.save(any(Address.class))).thenReturn(address);

		assertEquals(address, addressService.updateAddress(address));
	}

	@Test
	void testFindAll() {

		Address address = new Address();
		address.setCity("gdl");
		address.setCode(1);
		address.setPersonId(1);
		address.setState("jalisco");
		address.setStreet("madero");
		address.setStreetNumber("1");


		when(addressRepo.findAll()).thenReturn(Arrays.asList(address));
		assertEquals(Arrays.asList(address), addressService.findAll());
	}

	@Test
	void testFindAllIsEmpty() {

		when(addressRepo.findAll()).thenReturn(Collections.emptyList());
		assertThrows(ModelNotFoundException.class, () -> addressService.findAll());
	}

	@Test
	void testFindAddressByCode() {
		Address address = new Address();
		address.setCity("gdl");
		address.setCode(1);
		address.setPersonId(1);
		address.setState("jalisco");
		address.setStreet("madero");
		address.setStreetNumber("1");

		when(addressRepo.findByCode(address.getCode())).thenReturn(Optional.of(address));

		assertEquals(Optional.of(address), addressService.findAddressByCode(address.getCode()));

	}

	@Test
	void testFindAddressByCodeWhenIsNotPresent() {

		when(addressRepo.findByCode(anyInt())).thenReturn(Optional.empty());
		assertThrows(ModelNotFoundException.class, () -> addressService.findAddressByCode(1));

	}

	@Test
	void testFindByPersonId() {
		Address address = new Address();
		List<Address> addresses = Arrays.asList(address);
		address.setCity("gdl");
		address.setCode(1);
		address.setPersonId(1);
		address.setState("jalisco");
		address.setStreet("madero");
		address.setStreetNumber("1");

		when(addressRepo.findByPersonId(address.getPersonId())).thenReturn(addresses);

		assertEquals(addresses, addressService.findByPersonId(address.getPersonId()));

	}

	@Test
	@Rollback(false)
	void testDeleteByCode() {
		Address address = new Address();
		address.setCity("gdl");
		address.setCode(1);
		address.setPersonId(1);
		address.setState("jalisco");
		address.setStreet("madero");
		address.setStreetNumber("1");

		when(addressRepo.findByCode(address.getPersonId())).thenReturn(Optional.of(address));
		addressService.deleteByCode(address.getCode());

		verify(addressRepo, times(1)).findByCode(anyInt());
		verify(addressRepo, times(1)).delete(any(Address.class));
	}

	@Test
	void testDeleteByCodeException() {

		when(addressRepo.findByCode(anyInt())).thenReturn(Optional.empty());
		assertThrows(ModelNotFoundException.class, () -> addressService.deleteByCode(1));

	}

}
