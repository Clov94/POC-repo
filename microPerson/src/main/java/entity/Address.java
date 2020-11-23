package entity;

import lombok.Data;

@Data
public class Address {

	private int personId;
	private String code;
	private String street;
	private String city;
	private String state;
	private String streetNumber;
}
