package com.example.LaBeaute.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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
    private List<Stuff> stuff = new ArrayList<>();

    public void addStuffPost(Stuff stuff_id){
        this.stuff.add(stuff_id);
        stuff_id.setPost_name(this);
    }

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
