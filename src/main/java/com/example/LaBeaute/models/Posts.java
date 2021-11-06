package com.example.LaBeaute.models;

import javax.persistence.*;
import java.util.HashSet;
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

    @OneToMany(mappedBy = "post_name",cascade = CascadeType.ALL)
    private Set<Stuff> stuff = new HashSet<>();



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


    public Set<Stuff> getStuff() {
        return stuff;
    }

    public void setStuff(Set<Stuff> stuff) {
        this.stuff = stuff;
    }
}
