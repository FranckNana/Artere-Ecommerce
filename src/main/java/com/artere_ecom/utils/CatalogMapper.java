package com.artere_ecom.utils;

import com.artere_ecom.models.dto.CategoryDto;
import com.artere_ecom.models.dto.ProductDto;
import com.artere_ecom.models.entities.Category;
import com.artere_ecom.models.entities.Product;

import java.util.stream.Collectors;

public class CatalogMapper {

    public static CategoryDto toDto(Category category) {
        return new CategoryDto(
                category.getId(),
                category.getName(),
                category.getDescription(),
                category.getParent() != null ? category.getParent().getId() : null,
                category.getSubcategories() != null
                        ? category.getSubcategories().stream().map(Category::getId).collect(Collectors.toList())
                        : null,
                category.getProducts() != null
                        ? category.getProducts().stream().map(Product::getId).collect(Collectors.toList())
                        : null
        );
    }

    public static ProductDto toDto(Product product) {
        return new ProductDto(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getStockQuantity(),
                product.getCategory() != null ? product.getCategory().getId() : null
        );
    }


    public static Category toEntity(CategoryDto categoryDto, Category parentCategory) {
        Category category = new Category();
        category.setName(categoryDto.getName());
        category.setDescription(categoryDto.getDescription());
        category.setParent(parentCategory);
        return category;
    }

    public static Product toEntity(ProductDto productDto, Category category) {
        Product product = new Product();
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setStockQuantity(productDto.getStockQuantity());
        product.setCategory(category);
        return product;
    }

    public static void updateEntity(Product product, ProductDto productDto) {
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setStockQuantity(productDto.getStockQuantity());
    }
}
