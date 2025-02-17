package com.blogging.blogging.entity;

import com.blogging.blogging.payloads.CategoryDto;
import com.blogging.blogging.payloads.UserDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.Nullable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer postId;

    @Column(name = "post_title", nullable =false,length = 100)
    private String title;

    private String content;

    private String ImageName;
    private Date addedDate;

    @ManyToOne
    @JoinColumn(name="category_id")
    private Category category;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<Comments> comments=new ArrayList<>();

}
