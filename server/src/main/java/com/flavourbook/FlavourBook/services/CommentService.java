package com.flavourbook.FlavourBook.services;

import com.flavourbook.FlavourBook.dto.CommentDTO;
import com.flavourbook.FlavourBook.entity.Comment;
import com.flavourbook.FlavourBook.entity.Receipe;
import com.flavourbook.FlavourBook.entity.User;
import com.flavourbook.FlavourBook.repository.CommentRepository;
import com.flavourbook.FlavourBook.repository.ReceipeRepository;
import com.flavourbook.FlavourBook.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final ReceipeRepository receipeRepository;
    private final UserRepository userRepository;

    public CommentService(CommentRepository commentRepository, ReceipeRepository receipeRepository, UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.receipeRepository = receipeRepository;
        this.userRepository = userRepository;
    }

    public CommentDTO addComment(CommentDTO commentDTO) {
        Receipe recipe = receipeRepository.findById(commentDTO.getRecipeId())
                .orElseThrow(() -> new RuntimeException("Recipe not found"));
        User user = userRepository.findById(commentDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Comment comment = new Comment();
        comment.setRecipe(recipe);
        comment.setUser(user);
        comment.setRating(commentDTO.getRating());
        comment.setComment(commentDTO.getComment());

        Comment savedComment = commentRepository.save(comment);
        return toDTO(savedComment);
    }

    public List<CommentDTO> getCommentsByRecipeId(Long recipeId) {
        return commentRepository.findByRecipeId(recipeId).stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public List<CommentDTO> getCommentsByUserId(Long userId) {
        return commentRepository.findByUserId(userId).stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    private CommentDTO toDTO(Comment comment) {
        CommentDTO dto = new CommentDTO();
        dto.setId(comment.getId());
        dto.setRecipeId(comment.getRecipe().getId());
        dto.setUserId(comment.getUser().getId());
        dto.setRating(comment.getRating());
        dto.setComment(comment.getComment());
        return dto;
    }
}
