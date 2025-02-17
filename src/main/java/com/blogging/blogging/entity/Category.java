package com.blogging.blogging.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer categoryId;

    @Column(name = "title")
    private String categoryTitle;

    @Column(name="description")
    private String CategoryDescription;

    @OneToMany(mappedBy = "category" , cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<Post> posts=new ArrayList<>();
}
