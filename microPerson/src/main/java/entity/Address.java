package entity;

import lombok.Data;

@Data
public class Address {

	private Integer personId;
	private int code;
	private String street;
	private String city;
	private String state;
	private String streetNumber;
}
