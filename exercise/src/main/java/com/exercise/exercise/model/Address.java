package com.exercise.exercise.model;

import jakarta.persistence.*;
import org.hibernate.envers.Audited;

@Entity(name = "addresses")
@Audited
public class Address extends AbstractEntity {

    @Column
    private String street;

    @Column
    private String city;

    @Column
    private String country;

    @Column
    private String postalCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_account_id")
    private UserAccount userAccount;

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public UserAccount getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(UserAccount userAccount) {
        this.userAccount = userAccount;
    }

    public String toString() {
        return super.toString() + "Street: " + street +
                "City: " + city +
                "Country: " + country +
                "PostalCode: " + postalCode +
                "UserAccount" + userAccount.getId();

    }


}
