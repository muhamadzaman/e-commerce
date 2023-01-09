package com.services.commentservice.repositories;

import com.services.commentservice.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long>
{ List<Comment> findAllByProductId(String id); }
