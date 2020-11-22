package com.clov.poc.service.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.ControllerLinkBuilder.methodOn;

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

import com.clov.poc.service.exception.ModelNotFoundException;
import com.clov.poc.service.model.Address;
import com.clov.poc.service.service.impl.AddressService;

@RestController
@RequestMapping("/service")
public class AddressController {

	@Autowired
	private AddressService addressService;

	@PostMapping(produces = "application/json", consumes = "application/json")
	public ResponseEntity<Address> addAddress(@RequestBody @Validated Address address) {

		Address addressAdded = addressService.createAddress(address);

		return new ResponseEntity<Address>(HttpStatus.CREATED);
	}

	@PutMapping("/uAddresses")
	public ResponseEntity<Address> updateAddress(@RequestBody @Validated Address address) {

		Address uAddress = addressService.updateAddress(address);
		
		return new ResponseEntity<Address>(HttpStatus.ACCEPTED);
	}

	@GetMapping("/getAddresses")
	public ResponseEntity<List<Address>> getAddress() {
		
		List<Address> addressList = addressService.findAll();

		return new ResponseEntity<List<Address>>(HttpStatus.OK);
	}

	@GetMapping("/getAddressesByCode/{code}")
	public EntityModel<Address> findAddressByCode(@PathVariable(name = "code") String code) {

		Optional<Address> addressByCodEntityModel = addressService.findAddressByCode(code);
		
		return new EntityModel<Address>();
	}

	@DeleteMapping("/deleteAddresses/{code}")
	public ResponseEntity<Object> deleteByCode(@PathVariable("code") String code) {
		
		Object addressDeleted = addressService.deleteByCode(code);
		
		return new ResponseEntity<Object>(HttpStatus.OK);
	}

}
