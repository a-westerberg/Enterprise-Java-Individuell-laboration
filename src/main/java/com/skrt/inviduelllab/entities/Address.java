package com.skrt.inviduelllab.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50, nullable = false)
    private String street;

    @Column(length = 10, nullable = false)
    private String postalCode;

    @Column(length = 25, nullable = false)
    private String city;

    @OneToMany(mappedBy = "address")
    @JsonIgnore
    private List<Member> members;

    public Address() {
    }

    public Address(String city, String postalCode, String street) {
        this.city = city;
        this.postalCode = postalCode;
        this.street = street;
    }

    public Long getId() {
        return id;
    }

    public String getStreet() {
        return street;
    }
    public void setStreet(String street) {
        this.street = street;
    }

    public String getPostalCode() {
        return postalCode;
    }
    public void setPostalCode(String zip) {
        this.postalCode = zip;
    }

    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }

    public List<Member> getMembers() {
        return members;
    }
    public void setMembers(List<Member> members) {
        this.members = members;
    }
}
