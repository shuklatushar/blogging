package com.blogging.blogging.repositories;

import com.blogging.blogging.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category,Integer> {

}
