package com.nicolaspaxao.challengeanotaai.repositories;

import com.nicolaspaxao.challengeanotaai.domain.category.Category;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends MongoRepository<Category, String> {
}
