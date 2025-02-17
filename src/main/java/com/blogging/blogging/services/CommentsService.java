package com.blogging.blogging.services;

import com.blogging.blogging.payloads.CommentsDto;

public interface CommentsService {

    public CommentsDto createComment(Integer postId,Integer userId,CommentsDto commentsDto);
    public void deleteComment(Integer commentId);
}
