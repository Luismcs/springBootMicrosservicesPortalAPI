package com.exercise.exercise.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

//DTO EM MAIUSCULO
public class AddressDTO extends AbstractDTO {

    @Schema(description = "The address's street",
            example = "Park Avenue",
            requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "The street cannot be empty")
    private String street;

    @Schema(description = "The address's city",
            example = "New York",
            requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "The city cannot be empty")
    private String city;

    @Schema(description = "The address's country",
            example = "USA",
            requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "The country cannot be empty")
    private String country;

    @Schema(description = "The address's postalCode",
            example = "10011",
            requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "The postal-code cannot be empty")
    private String postalCode;

    @Schema(description = "The address's userAccount ID",
            example = "1",
            requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "The userAccountId cannot be empty")
    private Long userAccountId;

    public AddressDTO(Long id, String createdBy, Date createdDate, String lastModifiedBy, Date lastModifiedDate, String street, String city, String country, String postalCode, Long userAccountId) {
        super(id,createdBy, createdDate, lastModifiedBy, lastModifiedDate);
        this.street = street;
        this.city = city;
        this.country = country;
        this.postalCode = postalCode;
        this.userAccountId = userAccountId;
    }

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

    public Long getUserAccountId() {
        return userAccountId;
    }

    public void setUserAccountId(Long userAccountId) {
        this.userAccountId = userAccountId;
    }

    public String toString() {
        return super.toString() + "Street: " + street +
                "City: " + city +
                "Country: " + country +
                "PostalCode: " + postalCode +
                "UserAccountId" + userAccountId;

    }
}
