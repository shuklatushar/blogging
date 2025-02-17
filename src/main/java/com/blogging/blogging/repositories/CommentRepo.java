package com.blogging.blogging.repositories;

import com.blogging.blogging.entity.Comments;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepo extends JpaRepository<Comments,Integer> {

}
