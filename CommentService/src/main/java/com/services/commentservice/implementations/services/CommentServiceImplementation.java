package com.services.commentservice.implementations.services;

import com.services.commentservice.entities.Comment;
import com.services.commentservice.repositories.CommentRepository;
import com.services.commentservice.services.CommentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class CommentServiceImplementation implements CommentService
{
    private CommentRepository commentRepository;
    public CommentServiceImplementation(CommentRepository commentRepository)
    { this.commentRepository = commentRepository; }

    @Override
    public Comment createComment(Comment comment)
    { return commentRepository.save(comment); }

    @Override
    public List<Comment> readAllComments()
    { return commentRepository.findAll(); }

    @Override
    public Comment readCommentById(long id)
    { return findComment(id); }

    @Override
    public Comment updateCommentById(long id, Comment newComment)
    {
        Comment oldComment = findComment(id);
        newComment.setId(oldComment.getId());
        return commentRepository.save(newComment);
    }

    @Override
    public void deleteCommentById(long id)
    {
        Comment comment = findComment(id);
        commentRepository.deleteById(comment.getId());
    }

    private Comment findComment(long id)
    {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException
                        ("Comment with given id is not found on the server. Requested id = " + id));

        return comment;
    }

}
