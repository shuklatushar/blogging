package com.blogging.blogging.controller;

import com.blogging.blogging.payloads.ApiResponse;
import com.blogging.blogging.payloads.CommentsDto;
import com.blogging.blogging.services.CommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/api/comment")
public class CommentsController {

    @Autowired
    CommentsService commentsService;

    @PostMapping("/post/{postId}/user/{userId}")
    public ResponseEntity<CommentsDto> createComment(@PathVariable Integer postId,@PathVariable Integer userId,@RequestBody CommentsDto commentsDto){
             CommentsDto savedComment=commentsService.createComment(postId,userId,commentsDto);
             return new ResponseEntity<>(savedComment, HttpStatus.CREATED);
    }
    @DeleteMapping("/{commentId}")
    public ResponseEntity<ApiResponse> deleteComment(@PathVariable Integer commentId){
        commentsService.deleteComment(commentId);
        return new ResponseEntity<>(new ApiResponse("Comment Deleted Successfully!!",true), HttpStatus.OK);
    }
}
