package com.flavourbook.FlavourBook.controller;

import com.flavourbook.FlavourBook.dto.CommentDTO;
import com.flavourbook.FlavourBook.entity.Comment;
import com.flavourbook.FlavourBook.services.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recipes/{recipeId}/comments")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<Comment> addComment(@PathVariable Long recipeId, @RequestBody CommentDTO commentDTO) {
        return ResponseEntity.ok(commentService.addComment(recipeId, commentDTO));
    }

    @GetMapping
    public ResponseEntity<List<Comment>> getComments(@PathVariable Long recipeId) {
        return ResponseEntity.ok(commentService.getCommentsByRecipeId(recipeId));
    }
}
