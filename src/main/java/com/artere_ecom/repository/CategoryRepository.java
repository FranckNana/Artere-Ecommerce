package com.artere_ecom.repository;

import com.artere_ecom.models.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Object> findByName(String name);
}
