package com.retailstore.retailstoreassignment.domain.model.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Builder
@Document("bill")
@AllArgsConstructor
@NoArgsConstructor
public class Bill {
    @Id
    @Getter @Setter private String id;
    @Getter @Setter private String userId;
    @Getter @Setter private Double price;
    @Getter @Setter private LocalDateTime createdAt;
}
