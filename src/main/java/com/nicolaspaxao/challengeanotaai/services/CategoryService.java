package com.nicolaspaxao.challengeanotaai.services;

import com.nicolaspaxao.challengeanotaai.domain.category.Category;
import com.nicolaspaxao.challengeanotaai.domain.category.CategoryDTO;
import com.nicolaspaxao.challengeanotaai.domain.category.exceptions.CategoryNotFoundException;
import com.nicolaspaxao.challengeanotaai.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    private CategoryRepository repository;

    public CategoryService(CategoryRepository repository){
        this.repository = repository;
    }

    public Category insert(CategoryDTO categoryData) {
        Category newCategory = new Category(categoryData);
        repository.save(newCategory);
        return newCategory;
    }

    public List<Category> getAll(){
     return this.repository.findAll();
    }

    public Optional<Category> getByID(String id){
        return this.repository.findById(id);
    }

    public Category update(String id, CategoryDTO categoryData){
        Category category = repository.findById(id).orElseThrow(CategoryNotFoundException::new);

        if(!categoryData.title().isEmpty()) category.setTitle(categoryData.title());
        if(!categoryData.description().isEmpty()) category.setDescription(categoryData.description());
        if(!categoryData.ownerID().isEmpty()) category.setOwnerId(categoryData.ownerID());

        this.repository.save(category);

        return category;
    }

    public void delete(String id){
        Category category = repository.findById(id).orElseThrow(CategoryNotFoundException::new);
        this.repository.delete(category);
    }

}
