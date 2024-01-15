package com.nicolaspaxao.challengeanotaai.services;

import com.nicolaspaxao.challengeanotaai.domain.category.Category;
import com.nicolaspaxao.challengeanotaai.domain.category.exceptions.CategoryNotFoundException;
import com.nicolaspaxao.challengeanotaai.domain.products.Product;
import com.nicolaspaxao.challengeanotaai.domain.products.ProductDTO;
import com.nicolaspaxao.challengeanotaai.domain.products.exceptions.ProductNotFoundException;
import com.nicolaspaxao.challengeanotaai.repositories.CategoryRepository;
import com.nicolaspaxao.challengeanotaai.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProductService {
    private CategoryService categoryService;
    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository, CategoryService categoryService){
        this.productRepository = productRepository;
        this.categoryService = categoryService;
    }

    public Product insert(ProductDTO productData) {
        Category category = categoryService.getByID(productData.categoryID()).orElseThrow(CategoryNotFoundException::new);
        Product newProduct = new Product(productData);
        newProduct.setCategory(category);
        productRepository.save(newProduct);
        return newProduct;
    }

    public List<Product> getAll(){
        return this.productRepository.findAll();
    }

    public Product update(String id, ProductDTO productData){
        Product product = productRepository.findById(id).orElseThrow(ProductNotFoundException::new);

        categoryService.getByID(productData.categoryID()).ifPresent(product::setCategory);

        if(!productData.title().isEmpty()) product.setTitle(productData.title());
        if(!productData.description().isEmpty()) product.setDescription(productData.description());
        if(!productData.ownerID().isEmpty()) product.setOwnerId(productData.ownerID());
        if(!(productData.price() == null)) product.setPrice(productData.price());

        this.productRepository.save(product);

        return product;
    }

    public void delete(String id){
        Product product = productRepository.findById(id).orElseThrow(ProductNotFoundException::new);
        this.productRepository.delete(product);
    }
}
