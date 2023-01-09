package com.services.commentservice.dtos;

import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Data
public class CommentDto
{
    private long id;
    @NotNull(message = "Comment must not be null.")
    @NotBlank(message = "Some text against the comment must exist.")
    private String body;
    @NotNull(message = "Product id must not be null.")
    @NotBlank(message = "Product id must not be blank.")
    private String productId;
    @Min(value = 1, message = "User id must be provided")
    private long userId;
}
