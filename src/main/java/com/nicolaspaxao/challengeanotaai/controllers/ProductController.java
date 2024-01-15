package com.nicolaspaxao.challengeanotaai.controllers;

import com.nicolaspaxao.challengeanotaai.domain.products.Product;
import com.nicolaspaxao.challengeanotaai.domain.products.ProductDTO;
import com.nicolaspaxao.challengeanotaai.services.ProductService;
import com.nicolaspaxao.challengeanotaai.services.ProductService;
import jakarta.websocket.server.PathParam;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private ProductService service;

    public ProductController(ProductService service){
        this.service = service;
    }
    @PostMapping()
    public ResponseEntity<Product> insert(@RequestBody ProductDTO productDTO){
        Product newProduct =   this.service.insert(productDTO);
        return ResponseEntity.ok().body(newProduct);
    }

    @GetMapping()
    public ResponseEntity<List<Product>> getAll() {
        List<Product> products = this.service.getAll();
        return ResponseEntity.ok().body(products);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> update(@PathVariable("id") String id, @RequestBody ProductDTO productDTO) {
        Product updatedProduct =   this.service.update(id,productDTO);
        return ResponseEntity.ok().body(updatedProduct);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Product> delete(@PathVariable("id") String id) {
        this.service.delete(id);
        return ResponseEntity.ok().build();
    }
}
