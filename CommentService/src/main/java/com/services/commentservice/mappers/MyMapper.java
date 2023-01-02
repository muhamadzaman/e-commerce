package com.services.commentservice.mappers;

import com.services.commentservice.dtos.CommentDto;
import com.services.commentservice.entities.Comment;

public interface MyMapper
{
    CommentDto commentToCommentDto(Comment comment);
    Comment commentDtoToComment(CommentDto commentDto);
}
