package com.flavourbook.FlavourBook.repository;

import com.flavourbook.FlavourBook.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByRecipeId(Long recipeId);

    List<Comment> findByUserId(Long userId);
}
