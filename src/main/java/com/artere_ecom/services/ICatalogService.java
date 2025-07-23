package com.artere_ecom.services;

import com.artere_ecom.models.dto.CategoryDto;
import com.artere_ecom.models.dto.ProductDto;

public interface ICatalogService {
    public CategoryDto createCategory(CategoryDto categoryDto);
    public CategoryDto updateCategory(CategoryDto categoryDto);
    public void deleteCategory(Long categoryId);

    public ProductDto createProduct(ProductDto productDto);
    public ProductDto updateProduct(ProductDto productDto);
    public void deleteProduct(Long productId);

    public void linkProductToCategory(Long productId, Long categoryId);
    public void unlinkProductFromCategory(Long productId);
    public void linkSubcategoryToParent(Long childId, Long parentId);
    public void unlinkSubcategoryFromParent(Long childId);
}
