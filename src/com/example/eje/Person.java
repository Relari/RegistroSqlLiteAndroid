package com.example.eje;

public class Person {

	private Integer id;
	private String name;
	private String documentNumber;
	
	public Person() {}

	public Person(Integer id, String name, String documentNumber) {
		super();
		this.id = id;
		this.name = name;
		this.documentNumber = documentNumber;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDocumentNumber() {
		return documentNumber;
	}

	public void setDocumentNumber(String documentNumber) {
		this.documentNumber = documentNumber;
	}

	@Override
	public String toString() {
		return "Person [id=" + id + ", name=" + name + ", documentNumber=" + documentNumber + "]";
	}
	
	
}
