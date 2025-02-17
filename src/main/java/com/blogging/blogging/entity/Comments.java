package com.blogging.blogging.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Comments {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer commentId;

    private String content;

    @ManyToOne
    private Post post;

    @ManyToOne
    private User user;


}
