package com.nicolaspaxao.challengeanotaai.services;

import com.nicolaspaxao.challengeanotaai.domain.category.Category;
import com.nicolaspaxao.challengeanotaai.domain.category.exceptions.CategoryNotFoundException;
import com.nicolaspaxao.challengeanotaai.domain.products.Product;
import com.nicolaspaxao.challengeanotaai.domain.products.ProductDTO;
import com.nicolaspaxao.challengeanotaai.domain.products.exceptions.ProductNotFoundException;
import com.nicolaspaxao.challengeanotaai.repositories.CategoryRepository;
import com.nicolaspaxao.challengeanotaai.repositories.ProductRepository;
import com.nicolaspaxao.challengeanotaai.services.aws.AwsSnsService;
import com.nicolaspaxao.challengeanotaai.services.aws.MessageDTO;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProductService {
    private final CategoryService categoryService;
    private final ProductRepository productRepository;
    private final AwsSnsService snsService;

    public ProductService(ProductRepository productRepository, CategoryService categoryService, AwsSnsService snsService){
        this.productRepository = productRepository;
        this.categoryService = categoryService;
        this.snsService = snsService;
    }

    public Product insert(ProductDTO productData) {
        Category category = categoryService.getByID(productData.categoryId()).orElseThrow(CategoryNotFoundException::new);
        Product newProduct = new Product(productData);
        newProduct.setCategory(category);
        this.productRepository.save(newProduct);
        snsService.publish(new MessageDTO(newProduct.getOwnerId()));
        return newProduct;
    }

    public List<Product> getAll(){
        return this.productRepository.findAll();
    }

    public Product update(String id, ProductDTO productData){
        Product product = productRepository.findById(id).orElseThrow(ProductNotFoundException::new);

        if(productData.categoryId() != null){
            categoryService.getByID(productData.categoryId()).ifPresent(product::setCategory);
        }


        if(!productData.title().isEmpty()) product.setTitle(productData.title());
        if(!productData.description().isEmpty()) product.setDescription(productData.description());
        if(!productData.ownerId().isEmpty()) product.setOwnerId(productData.ownerId());
        if(!(productData.price() == null)) product.setPrice(productData.price());

        this.productRepository.save(product);

        snsService.publish(new MessageDTO(product.getOwnerId()));

        return product;
    }

    public void delete(String id){
        Product product = productRepository.findById(id).orElseThrow(ProductNotFoundException::new);
        this.productRepository.delete(product);
    }
}
