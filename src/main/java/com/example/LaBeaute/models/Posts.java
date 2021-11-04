package com.example.LaBeaute.models;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Posts {
    public Posts() {

    }

    public Posts(String post) {
        this.post = post;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String post;
    @OneToMany(mappedBy = "post_id", cascade = CascadeType.ALL,orphanRemoval = true)
    private Set<Stuff> stuffs;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }
}
