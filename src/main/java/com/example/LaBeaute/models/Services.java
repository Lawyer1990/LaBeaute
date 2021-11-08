package com.example.LaBeaute.models;

import javax.persistence.*;
import java.util.List;

@Entity
public class Services {
    public Services() {

    }

    public Services(String service) {
        this.service = service;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String service;

    @OneToMany(mappedBy = "service", cascade = CascadeType.ALL)
    private List<Order> order;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }


}
