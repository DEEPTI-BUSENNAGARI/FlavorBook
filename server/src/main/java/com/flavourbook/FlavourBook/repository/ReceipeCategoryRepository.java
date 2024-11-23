package com.flavourbook.FlavourBook.repository;

import com.flavourbook.FlavourBook.entity.ReceipeCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReceipeCategoryRepository extends JpaRepository<ReceipeCategory, Long> {
    List<ReceipeCategory> findByNameContainingIgnoreCase(String name);
}
