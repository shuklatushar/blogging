package com.blogging.blogging.controller;


import com.blogging.blogging.payloads.ApiResponse;
import com.blogging.blogging.payloads.PostDto;
import com.blogging.blogging.payloads.PostResponse;
import com.blogging.blogging.services.PostService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.PreparedStatement;
import java.util.List;

import static com.blogging.blogging.config.Constants.*;

@RestController
@RequestMapping("/api")
public class PostController {
    @Autowired
    private PostService postService;

    @PostMapping("/user/{userId}/category/{categoryId}/post")
    public ResponseEntity<PostDto> createPost(@PathVariable Integer userId,
                                              @PathVariable Integer categoryId,
                                              @RequestBody PostDto postDto){
                    PostDto savedPost=postService.createPost(postDto, userId, categoryId);
                   return new ResponseEntity<>(savedPost, HttpStatus.CREATED);
    }
    @GetMapping("/user/{userId}/post")
    public ResponseEntity<List<PostDto>> getPostByUser(@PathVariable Integer userId){
        List<PostDto> postDtoList=postService.getPostByUser(userId);
        return new ResponseEntity<>(postDtoList,HttpStatus.OK);
    }
    @GetMapping("/category/{categoryId}/post")
    public ResponseEntity<List<PostDto>> getPostByCategory(@PathVariable Integer categoryId){
        List<PostDto> postDtoList=postService.getPostByCategory(categoryId);
        return new ResponseEntity<>(postDtoList,HttpStatus.OK);
    }
    @GetMapping("/post/{postId}")
    public ResponseEntity<PostDto> getPostById(@PathVariable Integer postId){
            PostDto post=postService.getPostById(postId);
            return new ResponseEntity<>(post,HttpStatus.OK);

    }
    @GetMapping("/post")
    public ResponseEntity<PostResponse> getAllPost(@RequestParam(value = "pageNumber",defaultValue = PAGE_NUMBER) Integer pageNumber,
                                                   @RequestParam(value = "pageSize",defaultValue = PAGE_SIZE) Integer pageSize,
                                                   @RequestParam(value = "sortBy",defaultValue = SORT_BY,required = false) String sortBy,
                                                   @RequestParam(value = "sortOrder",defaultValue = SORT_ORDER,required = false) String sortOrder){
        PostResponse postResponse=postService.getAllPost(pageNumber,pageSize,sortBy,sortOrder);
        return new ResponseEntity<>(postResponse,HttpStatus.OK);
    }
    @DeleteMapping("/post/{postId}")
    public ApiResponse deletePost(@PathVariable Integer postId){
        postService.deletePost(postId);
        return new ApiResponse("successfully deleted post !!",true);
    }
    @PutMapping("/post/{postId}")
    public ResponseEntity<PostDto> updatePost(@PathVariable Integer postId, @Valid @RequestBody PostDto postDto){
        PostDto updatedPost=postService.updatePost(postDto,postId);
        return new ResponseEntity<PostDto>(updatedPost,HttpStatus.OK);
    }

    @GetMapping("/post/search/{keyword}")
    public ResponseEntity<List<PostDto>> searchPost(@PathVariable String keyword){
        List<PostDto> post=postService.searchPost(keyword);
        return new ResponseEntity<List<PostDto>>(post,HttpStatus.OK);
    }

}
