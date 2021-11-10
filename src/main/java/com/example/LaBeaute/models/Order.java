package com.example.LaBeaute.models;

import javax.persistence.*;


@Entity(name="orders")
public class Order {
   protected Order(){

    }


    public Order(String data, String time1, String time2) {
        this.data = data;
        this.time1 = time1;
        this.time2 = time2;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String data;

    private String time1;
    private String time2;

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
    public String getTime1() {
        return time1;
    }
    public void setTime1(String time1) {
        this.time1 = time1;
    }

    public String getTime2() {
        return time2;
    }

    public void setTime2(String time2) {
        this.time2 = time2;
    }
}
