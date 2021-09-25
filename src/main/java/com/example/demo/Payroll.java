package com.example.demo;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity
class Employee {

	private @Id @GeneratedValue Long id;
	private String name;
	private String secondName;
	@ElementCollection()
	private List<String> addresses;
	private LocalDate birthDate;
	@ElementCollection()
	private List<String> phoneNumbers;
	@Lob
	private byte[] photo;

	Employee() {
	}

	Employee(String name, String secondName, List<String> addresses, LocalDate birthDate, List<String> phoneNumbers,
			byte[] photo) {

		this.name = name;
		this.secondName = secondName;
		this.addresses = addresses;
		this.birthDate = birthDate;
		this.phoneNumbers = phoneNumbers;
		this.photo = photo;

	}

	public Long getId() {
		return this.id;
	}

	public String getName() {
		return this.name;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSecondName() {
		return secondName;
	}

	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}

	public List<String> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<String> addresses) {
		this.addresses = addresses;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	public List<String> getPhoneNumbers() {
		return phoneNumbers;
	}

	public void setPhoneNumbers(List<String> phoneNumbers) {
		this.phoneNumbers = phoneNumbers;
	}

	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

}