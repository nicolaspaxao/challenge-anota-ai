package com.nicolaspaxao.challengeanotaai.domain.category;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("categories")
@Getter
@Setter
@NoArgsConstructor
public class Category {
    @Id
    private String id;
    private String title;
    private String ownerId;
    private String description;


    public Category(CategoryDTO categoryDTO){
        title = categoryDTO.title();
        description = categoryDTO.description();
        ownerId = categoryDTO.ownerID();
    }
}
