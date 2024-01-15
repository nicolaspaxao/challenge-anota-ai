package com.nicolaspaxao.challengeanotaai.domain.products;

import com.nicolaspaxao.challengeanotaai.domain.category.Category;
import com.nicolaspaxao.challengeanotaai.domain.category.CategoryDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("products")
@Getter
@Setter
@NoArgsConstructor
public class Product {
    @Id
    private String id;
    private String title;
    private String ownerId;
    private String description;
    private Integer price;
    private Category category;

    public Product(ProductDTO productDTO){
        title = productDTO.title();
        description = productDTO.description();
        ownerId = productDTO.ownerID();
        price = productDTO.price();
    }
}
