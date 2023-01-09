package com.services.commentservice.services;

import com.services.commentservice.dtos.CommentDto;
import com.services.commentservice.entities.Comment;

import java.util.List;

public interface CommentService
{
    CommentDto createComment(CommentDto commentDto);
    List<CommentDto> readAllComments();
    CommentDto readCommentById(long id);
    CommentDto updateCommentById(long id, CommentDto commentDto);
    void deleteCommentById(long id);
    List<CommentDto> readCommentsByProductId(String productId);
}
