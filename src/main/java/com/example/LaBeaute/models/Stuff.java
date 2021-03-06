package com.example.LaBeaute.models;

import javax.persistence.*;
import java.util.List;

@Entity
public class Stuff {
    public Stuff() {

    }

    public Stuff(String name, String email, String password, String number) {
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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "post_name")
    private Posts post_name;

    @OneToMany(mappedBy = "stuff_name", cascade = CascadeType.ALL)
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Posts getPost_name() {
        return post_name;
    }

    public void setPost_name(Posts post_name) {
        this.post_name = post_name;
    }
}
