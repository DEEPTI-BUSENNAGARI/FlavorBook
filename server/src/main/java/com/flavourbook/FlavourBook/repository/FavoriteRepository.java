package com.flavourbook.FlavourBook.repository;


import com.flavourbook.FlavourBook.entity.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FavoriteRepository extends JpaRepository<Favorite, Long> {
    @Query("SELECT f FROM Favorite f JOIN FETCH f.recipe r WHERE f.user.id = :userId")
    List<Favorite> findByUserId(Long userId);
}
