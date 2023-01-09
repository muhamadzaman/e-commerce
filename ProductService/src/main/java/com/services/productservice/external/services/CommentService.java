package com.services.productservice.external.services;

import com.services.productservice.entities.Comment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "COMMENT-SERVICE")
public interface CommentService
{
    @GetMapping("/comments/product/{productId}")
    List<Comment> getComments(@PathVariable String productId);
}
