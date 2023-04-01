package com.springdatastore.tutorial;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.cloud.datastore.Key;
import com.google.cloud.spring.data.datastore.core.mapping.Entity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Entity(name = "addresses")
@Getter
@Setter
public class Address {

    @Id
    private Key addressKey;

    private String city;

    private String country;

    @JsonIgnore
    private User user;

    public Address() {
    }

    public Address(String city, String country) {
        this.city = city;
        this.country = country;
    }

    @Override
    public String toString() {
        return "Address{" +
                "city='" + city + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}
