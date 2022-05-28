
package rest;

import java.util.List;
import javax.annotation.Generated;

public class IndividualProducts {

    private List<ProductProducts> products = null;
    private Boolean activeCustomer;
    private String identificationNumber;
    private String dateOfBirth;
    private NameProducts name;
    private AddressProducts address;

    public List<ProductProducts> getProducts() {
        return products;
    }

    public void setProducts(List<ProductProducts> products) {
        this.products = products;
    }

    public Boolean getActiveCustomer() {
        return activeCustomer;
    }

    public void setActiveCustomer(Boolean activeCustomer) {
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

    public NameProducts getName() {
        return name;
    }

    public void setName(NameProducts name) {
        this.name = name;
    }

    public AddressProducts getAddress() {
        return address;
    }

    public void setAddress(AddressProducts address) {
        this.address = address;
    }

}
