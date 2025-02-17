package com.blogging.blogging.services;

import com.blogging.blogging.payloads.PostDto;
import com.blogging.blogging.payloads.PostResponse;

import java.util.List;

public interface PostService{

     PostDto createPost(PostDto postDto,Integer userId,Integer categoryId);

     PostDto updatePost(PostDto postDto,Integer userId);

     void deletePost(Integer postId);

     PostResponse getAllPost(Integer pageNumber, Integer pageSize,String sortBy,String sortOrder);

     PostDto getPostById(Integer postId);
     List<PostDto> getPostByCategory(Integer categoryId);
     List<PostDto> getPostByUser(Integer userId);
     List<PostDto> searchPost(String Keyword);


}
