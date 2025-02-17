package com.blogging.blogging.repositories;

import com.blogging.blogging.entity.Category;
import com.blogging.blogging.entity.Post;
import com.blogging.blogging.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepo extends JpaRepository<Post,Integer> {

     List<Post> findByUser(User user);
     List<Post> findByCategory(Category category);

     @Query("Select p from Post p where p.title like :key")
     List<Post> findByTitleContaining(@Param("key") String keyword);
}
