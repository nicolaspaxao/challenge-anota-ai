package com.nicolaspaxao.challengeanotaai.repositories;

import com.nicolaspaxao.challengeanotaai.domain.products.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {
}
