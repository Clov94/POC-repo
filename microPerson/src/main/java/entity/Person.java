package entity;

import java.util.List;

import lombok.Data;

@Data
public class Person {

	private Integer id;

	private String firstName;

	private String lastName;

	private String age;

	private String bloodType;

	private List<Address> address;


}
