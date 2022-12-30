package com.services.commentservice.dtos;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Data
public class CommentDto
{
    private long id;
    private String body;
}
