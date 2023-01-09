package com.services.commentservice.implementations.services;

import com.services.commentservice.dtos.CommentDto;
import com.services.commentservice.entities.Comment;
import com.services.commentservice.mappers.MyMapper;
import com.services.commentservice.repositories.CommentRepository;
import com.services.commentservice.services.CommentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class CommentServiceImplementation implements CommentService
{
    private CommentRepository commentRepository;
    private MyMapper myMapper;
    public CommentServiceImplementation(CommentRepository commentRepository, MyMapper myMapper)
    {
        this.commentRepository = commentRepository;
        this.myMapper = myMapper;
    }

    @Override
    public CommentDto createComment(CommentDto commentDto)
    {
        Comment comment = myMapper.commentDtoToComment(commentDto);
        Comment savedComment = commentRepository.save(comment);
        CommentDto savedCommentDto = myMapper.commentToCommentDto(savedComment);
        return savedCommentDto;
    }

    @Override
    public List<CommentDto> readAllComments()
    {
        return commentRepository
                .findAll()
                .stream()
                .map(comment -> myMapper.commentToCommentDto(comment))
                .collect(Collectors.toList());
    }

    @Override
    public List<CommentDto> readCommentsByProductId(String productId)
    {
        return commentRepository
                .findAllByProductId(productId)
                .stream()
                .map(comment -> myMapper.commentToCommentDto(comment))
                .collect(Collectors.toList());
    }

    @Override
    public CommentDto readCommentById(long id)
    {
        Comment comment = findComment(id);
        return myMapper.commentToCommentDto(comment);
    }
    @Override
    public CommentDto updateCommentById(long id, CommentDto commentDto)
    {
        Comment newComment = myMapper.commentDtoToComment(commentDto);
        Comment oldComment = findComment(id);
        newComment.setId(oldComment.getId());
        commentRepository.save(newComment);
        return myMapper.commentToCommentDto(newComment);
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
