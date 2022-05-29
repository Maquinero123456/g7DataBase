package rest;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named(value = "individual")
@RequestScoped
public class Individuo {
	

	private Products products = null;
	private String activeCustomer;
	private String identificationNumber;
	private String dateOfBirth;
	private Name name;
	private Address address;

	public Products getProducts() {
		return products;
	}

	public void setProducts(Products products) {
		this.products = products;
	}

	public String getActiveCustomer() {
	return activeCustomer;
	}

	public void setActiveCustomer(String activeCustomer) {
	this.activeCustomer = activeCustomer;
	}

	public String getIdentificationNumber() {
	return identificationNumber;
	}

	public void setIdentificationNumber(String identificationNumber) {
	this.identificationNumber = identificationNumber;
	}

	public String getDateOfBirth() {
	return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
	this.dateOfBirth = dateOfBirth;
	}

	public Name getName() {
	return name;
	}

	public void setName(Name name) {
	this.name = name;
	}

	public Address getAddress() {
	return address;
	}

	public void setAddress(Address address) {
	this.address = address;
	}

	@Override
	public String toString() {
	StringBuilder sb = new StringBuilder();
		sb.append(Individuo.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
		sb.append("products");
		sb.append('=');
		sb.append((this.products));
		sb.append(',');
		sb.append("activeCustomer");
		sb.append('=');
		sb.append((this.activeCustomer));
		sb.append(',');
		sb.append("identificationNumber");
		sb.append('=');
		sb.append((this.identificationNumber));
		sb.append(',');
		sb.append("dateOfBirth");
		sb.append('=');
		sb.append(((this.dateOfBirth == null)?"non-existent":this.dateOfBirth));
		sb.append(',');
		sb.append("name");
		sb.append('=');
		sb.append(((this.name == null)?"<null>":this.name));
		sb.append(',');
		sb.append("address");
		sb.append('=');
		sb.append(((this.address == null)?"<null>":this.address));
		sb.append(',');
		if (sb.charAt((sb.length()- 1)) == ',') {
			sb.setCharAt((sb.length()- 1), ']');
		} else {
			sb.append(']');
		}
		return sb.toString();
	}

}

