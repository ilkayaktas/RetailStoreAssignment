package com.retailstore.retailstoreassignment.domain.model.entity;

import com.retailstore.retailstoreassignment.domain.model.enums.UserType;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Builder
@Document("user")
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @Getter @Setter private String id;
    @Getter @Setter private String name;
    @Getter @Setter private String lastName;
    @Getter @Setter private String email;
    @Getter @Setter private String password;
    @Getter @Setter private UserType userType;
    @Getter @Setter private LocalDateTime createdAt;
    @Getter @Setter private LocalDateTime updatedAt;
}
