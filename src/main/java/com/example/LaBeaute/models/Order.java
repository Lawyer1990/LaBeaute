package com.example.LaBeaute.models;

import javax.persistence.*;


@Entity(name="orders")
public class Order {
   protected Order(){

    }



    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String data;
    private String time;

    @ManyToOne(fetch = FetchType.EAGER)
    private Statuses status;

    @ManyToOne(fetch = FetchType.EAGER)
    private Stuff stuff_name;

    @ManyToOne(fetch = FetchType.EAGER)
    private Services service;

    @ManyToOne(fetch = FetchType.EAGER)
    private Customers customers_name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }



    public Statuses getStatus() {
        return status;
    }

    public void setStatus(Statuses status) {
        this.status = status;
    }

    public Stuff getStuff_name() {
        return stuff_name;
    }

    public void setStuff_name(Stuff stuff_name) {
        this.stuff_name = stuff_name;
    }

    public Services getService() {
        return service;
    }

    public void setService(Services service) {
        this.service = service;
    }

    public Customers getCustomers_name() {
        return customers_name;
    }

    public void setCustomers_name(Customers customers_name) {
        this.customers_name = customers_name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
