package com.exercise.manager.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import com.exercise.manager.enumerators.Role;

import java.util.Date;
import java.util.List;

public class UserAccountDTO extends AbstractDTO {

    @Schema(description = "The user account's username",
            example = "john_doe",
            requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "The username cannot be empty")
    private String username;

    @Schema(description = "The user account's first name",
            example = "John",
            requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "The first name cannot be empty")
    private String firstName;

    @Schema(description = "The user account's last name",
            example = "Doe",
            requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "The last name cannot be empty")
    private String lastName;

    @Schema(description = "The user account's role",
            example = "USER",
            requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "The role cannot be null")
    private Role role;

    private List<AddressDTO> addresses;

    public UserAccountDTO(Long id, String createdBy, Date createdDate, String lastModifiedBy, Date lastModifiedDate, String username, String firstName, String lastName, Role role, List<AddressDTO> addresses) {
        super(id, createdBy, createdDate, lastModifiedBy, lastModifiedDate);
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
        this.addresses = addresses;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<AddressDTO> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<AddressDTO> addresses) {
        this.addresses = addresses;
    }

    public String toString() {
        return super.toString() + "First Name: " + firstName +
                "Last Name: " + lastName +
                "Role: " + role;
    }

}
