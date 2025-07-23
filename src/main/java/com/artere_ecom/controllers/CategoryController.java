package com.artere_ecom.controllers;

import com.artere_ecom.models.dto.CategoryDto;
import com.artere_ecom.services.ICatalogService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final ICatalogService catalogService;

    public CategoryController(ICatalogService catalogService) {
        this.catalogService = catalogService;
    }

    @PostMapping
    public ResponseEntity<CategoryDto> create(@RequestBody CategoryDto categoryDto) {
        return ResponseEntity.ok(catalogService.createCategory(categoryDto));
    }

    @PutMapping
    public ResponseEntity<CategoryDto> update(CategoryDto categoryDto) {
        return ResponseEntity.ok(catalogService.updateCategory(categoryDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        catalogService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{childId}/link-to/{parentId}")
    public ResponseEntity<Void> linkSubcategory(@PathVariable Long childId, @PathVariable Long parentId) {
        catalogService.linkSubcategoryToParent(childId, parentId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{childId}/unlink")
    public ResponseEntity<Void> unlinkSubcategory(@PathVariable Long childId) {
        catalogService.unlinkSubcategoryFromParent(childId);
        return ResponseEntity.ok().build();
    }
}
