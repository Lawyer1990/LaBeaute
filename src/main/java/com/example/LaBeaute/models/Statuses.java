package com.example.LaBeaute.models;

import javax.persistence.*;
import java.util.List;

@Entity
public class Statuses {
    public Statuses() {

    }

    public Statuses(String status) {
        this.status = status;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String status;

    @OneToMany(mappedBy = "status", cascade = CascadeType.ALL)
    private List<Order> order;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
