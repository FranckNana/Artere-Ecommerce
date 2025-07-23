package com.artere_ecom.controllers;

import com.artere_ecom.models.dto.ProductDto;
import com.artere_ecom.services.ICatalogService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ICatalogService catalogService;

    public ProductController(ICatalogService catalogService) {
        this.catalogService = catalogService;
    }

    @PostMapping
    public ResponseEntity<ProductDto> create(@RequestBody ProductDto productDto) {
        return ResponseEntity.ok(catalogService.createProduct(productDto));
    }

    @PutMapping
    public ResponseEntity<ProductDto> update(@RequestBody ProductDto productDto) {
        return ResponseEntity.ok(catalogService.updateProduct(productDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        catalogService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{productId}/link-to/{categoryId}")
    public ResponseEntity<Void> linkToCategory(@PathVariable Long productId, @PathVariable Long categoryId) {
        catalogService.linkProductToCategory(productId, categoryId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{productId}/unlink")
    public ResponseEntity<Void> unlinkFromCategory(@PathVariable Long productId) {
        catalogService.unlinkProductFromCategory(productId);
        return ResponseEntity.ok().build();
    }
}
