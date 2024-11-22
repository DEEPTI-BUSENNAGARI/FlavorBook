package com.flavourbook.FlavourBook.repository;

import com.flavourbook.FlavourBook.entity.Receipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReceipeRepository extends JpaRepository<Receipe, Long> {
    List<Receipe> findByTitleContainingIgnoreCase(String query);

    @Query("SELECT r FROM Receipe r LEFT JOIN Comment c ON r.id = c.receipe.id GROUP BY r.id ORDER BY COUNT(c.id) DESC")
    List<Receipe> findPopularRecipes();

    List<Receipe> findByPrepTimeLessThanEqual(int time);

}
