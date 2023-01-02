package com.services.commentservice.controller;

import com.services.commentservice.dtos.CommentDto;
import com.services.commentservice.entities.Comment;
import com.services.commentservice.mappers.MyMapper;
import com.services.commentservice.services.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/comments")
public class CommentController
{
    private CommentService commentService;
    private MyMapper myMapper;
    public CommentController(CommentService commentService, MyMapper myMapper)
    {
        this.commentService = commentService;
        this.myMapper = myMapper;
    }

    @PostMapping
    private ResponseEntity<Comment> saveComment(@Valid @RequestBody CommentDto commentDto)
    {
        Comment comment = myMapper.commentDtoToComment(commentDto);
        commentService.createComment(comment);
        return ResponseEntity.status(HttpStatus.OK).body(comment);
    }
    @GetMapping
    private ResponseEntity<List<CommentDto>> getAllComments()
    {
        List<CommentDto> allComments = commentService
                .readAllComments()
                .stream().map(comment -> myMapper.commentToCommentDto(comment))
                .collect(Collectors.toList());

        return ResponseEntity.ok(allComments);
    }
    @GetMapping("/{id}") ResponseEntity<CommentDto> getCommentById(@PathVariable long id)
    {
        Comment comment = commentService.readCommentById(id);
        CommentDto commentDto = myMapper.commentToCommentDto(comment);
        return ResponseEntity.ok(commentDto);
    }
    @PutMapping("/{id}/update")
    private ResponseEntity<Comment> updateComment(@PathVariable long id, @RequestBody CommentDto commentDto)
    {
        Comment comment = myMapper.commentDtoToComment(commentDto);
        comment = commentService.updateCommentById(id, comment);
        return ResponseEntity.ok(comment);
    }

    @DeleteMapping("/{id}/delete")
    private void deleteComment(@PathVariable long id)
    { commentService.deleteCommentById(id); }
}
