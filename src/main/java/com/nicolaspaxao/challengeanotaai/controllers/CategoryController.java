package com.nicolaspaxao.challengeanotaai.controllers;

import com.nicolaspaxao.challengeanotaai.domain.category.Category;
import com.nicolaspaxao.challengeanotaai.domain.category.CategoryDTO;
import com.nicolaspaxao.challengeanotaai.services.CategoryService;
import jakarta.websocket.server.PathParam;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
    private CategoryService service;

    public CategoryController(CategoryService service){
        this.service = service;
    }
    @PostMapping()
    public ResponseEntity<Category> insert(@RequestBody CategoryDTO categoryDTO){
      Category newCategory =   this.service.insert(categoryDTO);
      return ResponseEntity.ok().body(newCategory);
    }

    @GetMapping()
    public ResponseEntity<List<Category>> getAll() {
        List<Category> categories = this.service.getAll();
        return ResponseEntity.ok().body(categories);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Category> update(@PathVariable("id") String id, @RequestBody CategoryDTO categoryDTO) {
        Category updatedCategory =   this.service.update(id,categoryDTO);
        return ResponseEntity.ok().body(updatedCategory);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Category> delete(@PathVariable("id") String id) {
        this.service.delete(id);
        return ResponseEntity.ok().build();
    }

}
