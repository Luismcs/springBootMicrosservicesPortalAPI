package com.exercise.exercise.model;

import com.exercise.exercise.enumerators.Role;
import jakarta.persistence.*;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

import java.util.List;

@Entity(name = "user_accounts")
@Audited
public class UserAccount extends AbstractEntity {

    @Column
    private String username;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    @Enumerated(EnumType.STRING)
    private Role role;

    @NotAudited
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "userAccount", cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
    private List<Address> addresses;

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
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

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

}
