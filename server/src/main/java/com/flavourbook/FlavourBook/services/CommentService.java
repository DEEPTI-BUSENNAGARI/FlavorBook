package com.flavourbook.FlavourBook.services;

import com.flavourbook.FlavourBook.dto.CommentDTO;
import com.flavourbook.FlavourBook.entity.Comment;
import com.flavourbook.FlavourBook.entity.Receipe;
import com.flavourbook.FlavourBook.repository.CommentRepository;
import com.flavourbook.FlavourBook.repository.ReceipeRepository;
import dto.CommentDTO;
import entity.Comment;
import entity.Recipe;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import repository.CommentRepository;
import repository.ReceipeRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final ReceipeRepository receipeRepository;

    public Comment addComment(Long recipeId, CommentDTO commentDTO) {
        Receipe recipe = receipeRepository.findById(recipeId)
                .orElseThrow(() -> new RuntimeException("Recipe not found"));
        Comment comment = new Comment();
        comment.setRecipe(recipe);
        comment.setUserId(commentDTO.getUserId());
        comment.setRating(commentDTO.getRating());
        comment.setComment(commentDTO.getComment());
        return commentRepository.save(comment);
    }

    public List<Comment> getCommentsByRecipeId(Long recipeId) {
        return commentRepository.findByRecipeId(recipeId);
    }
}
