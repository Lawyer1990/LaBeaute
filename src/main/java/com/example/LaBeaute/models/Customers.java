package com.example.LaBeaute.models;

import javax.persistence.*;
import java.util.List;

@Entity
public class Customers {
    public Customers() {

    }

    public Customers(String name, String email, String password, String number) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.number = number;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String email;
    private String password;
    private String number;

    @OneToMany(mappedBy = "customers_name", cascade = CascadeType.ALL)
    private List<Order> order;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }


}
