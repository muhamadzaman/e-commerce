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

    public CommentController(CommentService commentService)
    { this.commentService = commentService; }

    @PostMapping
    private ResponseEntity<CommentDto> saveComment(@Valid @RequestBody CommentDto commentDto)
    {
        CommentDto postCommentDto = commentService.createComment(commentDto);
        return ResponseEntity.status(HttpStatus.OK).body(postCommentDto);
    }
    @GetMapping
    private ResponseEntity<List<CommentDto>> getAllComments()
    {
        List<CommentDto> allComments = commentService.readAllComments();
        return ResponseEntity.ok(allComments);
    }
    @GetMapping("/{id}") ResponseEntity<CommentDto> getCommentById(@PathVariable long id)
    {
        CommentDto commentDto = commentService.readCommentById(id);
        return ResponseEntity.ok(commentDto);
    }
    @PutMapping("/{id}/update")
    private ResponseEntity<CommentDto> updateComment(@PathVariable long id, @RequestBody CommentDto commentDto)
    {
        CommentDto updatedComment = commentService.updateCommentById(id, commentDto);
        return ResponseEntity.ok(updatedComment);
    }
    @DeleteMapping("/{id}/delete")
    private void deleteComment(@PathVariable long id)
    { commentService.deleteCommentById(id); }
}
