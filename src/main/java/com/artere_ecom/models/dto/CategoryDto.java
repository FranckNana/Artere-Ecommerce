package com.artere_ecom.models.dto;

import java.util.List;

public class CategoryDto {
    public Long id;
    public String name;
    public String description;
    public Long parentId;
    public List<Long> subcategoryIds;
    public List<Long> productIds;

    public CategoryDto() {
    }

    public CategoryDto(Long id, String name, String description, Long parentId, List<Long> subcategoryIds, List<Long> productIds) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.parentId = parentId;
        this.subcategoryIds = subcategoryIds;
        this.productIds = productIds;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public List<Long> getSubcategoryIds() {
        return subcategoryIds;
    }

    public void setSubcategoryIds(List<Long> subcategoryIds) {
        this.subcategoryIds = subcategoryIds;
    }

    public List<Long> getProductIds() {
        return productIds;
    }

    public void setProductIds(List<Long> productIds) {
        this.productIds = productIds;
    }
}
