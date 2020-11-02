package com.learning.coronaHelpApi.models;

import javax.persistence.Embeddable;
import org.hibernate.validator.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Embeddable
class Address {


    @NotNull
    @NotBlank
    private String street;
    @NotNull
    @NotBlank
    private String streetNumber;
    @NotNull
    @NotBlank
    private String city;
    @NotNull
    @NotBlank
    private String zipcode;

    public Address() {

    }

    public Address(String street, String streetNumber, String city, String zipcode) {
        this.street = street;
        this.streetNumber = streetNumber;
        this.city = city;
        this.zipcode = zipcode;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }
}
