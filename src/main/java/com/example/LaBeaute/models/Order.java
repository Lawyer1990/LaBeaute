package com.example.LaBeaute.models;

import javax.persistence.*;

@Entity
public class Order {
   protected Order(){

    }

    public Order(Long id, String data, Long time, Statuses status_id,
                 Stuff stuff_id, Services service_id, Customers name_customer_id) {
        this.id = id;
        this.data = data;
        this.time = time;

    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String data;
    private Long time;


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

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }


}
