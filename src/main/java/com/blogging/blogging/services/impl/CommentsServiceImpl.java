package com.blogging.blogging.services.impl;

import com.blogging.blogging.entity.Comments;
import com.blogging.blogging.entity.Post;
import com.blogging.blogging.entity.User;
import com.blogging.blogging.exceptions.ResourceNotFoundException;
import com.blogging.blogging.payloads.CommentsDto;
import com.blogging.blogging.repositories.CommentRepo;
import com.blogging.blogging.repositories.PostRepo;
import com.blogging.blogging.repositories.UserRepo;
import com.blogging.blogging.services.CommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentsServiceImpl implements CommentsService {
    @Autowired
    private CommentRepo commentRepo;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private PostRepo postRepo;

    @Override
    public CommentsDto createComment(Integer postId,Integer userId,CommentsDto commentsDto) {
        User user =userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("user","userId",userId));
        Post post =postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("post","postId",postId));

        Comments comments=toComment(commentsDto);
        comments.setPost(post);
        comments.setUser(user);
        Comments savedComment=commentRepo.save(comments);

          return toCommentsDto(savedComment);
    }

    @Override
    public void deleteComment(Integer commentId) {

        Comments comment =commentRepo.findById(commentId).orElseThrow(()-> new ResourceNotFoundException("comment","commentId",commentId));
        commentRepo.delete(comment);
    }
    public Comments toComment(CommentsDto commentDto){
        Comments comments=new Comments();
        comments.setContent(commentDto.getContent());
       return comments;
    }
    public CommentsDto toCommentsDto(Comments comments){
        CommentsDto commentsDto=new CommentsDto();
        commentsDto.setCommentId(comments.getCommentId());
        commentsDto.setContent(comments.getContent());
        return commentsDto;
    }
}
