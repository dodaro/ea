package it.unical.inf.ea.annotation.bean;

import lombok.Data;

@Data
public class Employee
{
	private long id;

	private String name;

	public Employee() {
		System.out.println("Employee created");
	}

}