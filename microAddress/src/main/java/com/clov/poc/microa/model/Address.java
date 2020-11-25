package com.clov.poc.microa.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

import lombok.Data;


@Data
@Entity
@Table(name = "ADDRESS")
public class Address {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CODE", nullable = false)
	private int code;

	@Column(name = "PERSON_ID", nullable = false)
	@Min(value = 1, message = "Person Id is not valid")
	private int personId;
	
	@Column(name = "STREET")
	private String street;
	
	@Column(name = "CITY")
	@Pattern(regexp = "^[a-zA-Z]*$")
	private String city;
	
	@Column(name = "STATE")
	private String state;
	
	@Column(name = "STREET_NUMBER")
	private String streetNumber;

}
