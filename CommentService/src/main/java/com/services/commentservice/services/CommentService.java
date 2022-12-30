package com.services.commentservice.services;

import com.services.commentservice.entities.Comment;

import java.util.List;

public interface CommentService
{
    Comment createComment(Comment comment);
    List<Comment> readAllComments();
    Comment readCommentById(long id);
    Comment updateCommentById(long id, Comment comment);
    void deleteCommentById(long id);
}
