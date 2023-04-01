package com.springdatastore.tutorial;

import com.google.cloud.spring.data.datastore.core.mapping.Entity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Reference;

import java.util.List;

@Entity(name = "users")
@Getter
@Setter
public class User {

    @Id
    private Long id;

    private String name;

    private String email;

    private String country;

    @Reference
    private List<Address> address;

    public User() {
    }

    public User(String name, String email, String country) {
        this.name = name;
        this.email = email;
        this.country = country;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}
