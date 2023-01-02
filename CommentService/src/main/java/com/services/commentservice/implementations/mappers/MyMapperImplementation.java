package com.services.commentservice.implementations.mappers;

import com.services.commentservice.dtos.CommentDto;
import com.services.commentservice.entities.Comment;
import com.services.commentservice.mappers.MyMapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class MyMapperImplementation implements MyMapper
{
    private ModelMapper modelMapper;
    public MyMapperImplementation(ModelMapper modelMapper)
    { this.modelMapper = modelMapper; }

    @Override
    public CommentDto commentToCommentDto(Comment comment) { return modelMapper.map(comment, CommentDto.class); }
    @Override
    public Comment commentDtoToComment(CommentDto commentDto)
    {
        return modelMapper.map(commentDto, Comment.class);
    }
}
