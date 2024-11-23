package com.flavourbook.FlavourBook.repository;

import com.flavourbook.FlavourBook.entity.Receipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReceipeRepository extends JpaRepository<Receipe, Long> {

    // Search by recipe name
    List<Receipe> findByNameContainingIgnoreCase(String name);

}

