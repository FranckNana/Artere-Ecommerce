package com.artere_ecom.services.impl;

import com.artere_ecom.models.dto   .CategoryDto;
import com.artere_ecom.models.dto.ProductDto;
import com.artere_ecom.models.entities.Category;
import com.artere_ecom.models.entities.Product;
import com.artere_ecom.repository.CategoryRepository;
import com.artere_ecom.repository.ProductRepository;
import com.artere_ecom.services.ICatalogService;
import com.artere_ecom.utils.CatalogMapper;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CatalogService implements ICatalogService {

    private static final Logger logger = LoggerFactory.getLogger(CatalogService.class);

    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    public CatalogService(CategoryRepository categoryRepository, ProductRepository productRepository) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }

    @PostConstruct
    public void init() {
        String name = "Catalogue Principal";

        boolean exists = categoryRepository.findByName(name).isPresent();
        if (!exists) {
            Category parent = new Category();
            parent.setName(name);
            parent.setDescription("Catégorie racine");
            categoryRepository.save(parent);
            logger.info("✅ Catégorie racine créée via @PostConstruct");
        } else {
            logger.info("ℹ️ Catégorie racine déjà présente");
        }
    }


    /****************************
    CATEGORIES
     ****************************/

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category parent = null;
        if (categoryDto.parentId != null) {
            parent = categoryRepository.findById(categoryDto.parentId)
                    .orElseThrow(() -> new RuntimeException("Parent category not found"));
            categoryDto.setParentId(parent.getId());
        }

        Category categoryEntity = CatalogMapper.toEntity(categoryDto, parent);
        return CatalogMapper.toDto(categoryRepository.save(categoryEntity));
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto) {
        Category category = categoryRepository.findById(categoryDto.getId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        category.setName(categoryDto.getName());
        category.setDescription(categoryDto.getDescription());

        return CatalogMapper.toDto(categoryRepository.save(category));
    }

    @Override
    public void deleteCategory(Long categoryId) {
        if (!categoryRepository.existsById(categoryId)) {
            throw new RuntimeException("Category not found");
        }
        categoryRepository.deleteById(categoryId);
    }



    /****************************
     produits
     ****************************/
    @Override
    public ProductDto createProduct(ProductDto productDto) {
        Category category = categoryRepository.findById(productDto.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        Product product = CatalogMapper.toEntity(productDto, category);
        return CatalogMapper.toDto(productRepository.save(product));
    }

    @Override
    public ProductDto updateProduct(ProductDto productDto) {
        Product product = productRepository.findById(productDto.getId())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        CatalogMapper.updateEntity(product, productDto);
        return CatalogMapper.toDto(productRepository.save(product));
    }

    @Override
    public void deleteProduct(Long productId) {
        if (!productRepository.existsById(productId)) {
            throw new RuntimeException("Product not found");
        }
        productRepository.deleteById(productId);
    }

    /****************************
     LAISONS
     ****************************/

    @Override
    public void linkProductToCategory(Long productId, Long categoryId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category not found"));

        product.setCategory(category);
        productRepository.save(product);
    }

    @Override
    public void unlinkProductFromCategory(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        product.setCategory(null);
        productRepository.save(product);
    }

    @Override
    public void linkSubcategoryToParent(Long childId, Long parentId) {
        Category child = categoryRepository.findById(childId)
                .orElseThrow(() -> new RuntimeException("Child category not found"));
        Category parent = categoryRepository.findById(parentId)
                .orElseThrow(() -> new RuntimeException("Parent category not found"));

        child.setParent(parent);
        categoryRepository.save(child);
    }

    @Override
    public void unlinkSubcategoryFromParent(Long childId) {
        Category child = categoryRepository.findById(childId)
                .orElseThrow(() -> new RuntimeException("Category not found"));

        child.setParent(null);
        categoryRepository.save(child);
    }
}
