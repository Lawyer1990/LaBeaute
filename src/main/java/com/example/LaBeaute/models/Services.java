package com.example.LaBeaute.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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
