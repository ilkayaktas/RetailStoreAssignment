package com.retailstore.retailstoreassignment.domain.model.entity;

import com.retailstore.retailstoreassignment.domain.model.enums.ItemType;
import lombok.*;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Item {
    @Id
    @Getter @Setter private String id;
    @Getter @Setter private String name;
    @Getter @Setter private Double price;
    @Getter @Setter private LocalDateTime createdAt;
    @Getter @Setter private ItemType itemType;
}
