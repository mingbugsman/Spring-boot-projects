package com.NovelBookOnline.NovelBookOnline.Entity;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    @Column(name = "email", nullable = false ,columnDefinition = "VARCHAR(100)")
    String email;

    @Column(name = "password", nullable = false, columnDefinition = "VARCHAR(255)")
    String password;

    @Column(name = "username", nullable = false ,columnDefinition = "VARCHAR(15)")
    String username;

    @Column(name = "gender",columnDefinition = "BIT",nullable = false)
    Boolean gender;

    @Lob
    @Column(name = "user_image_data", nullable = true, columnDefinition = "LONGBLOB")
    byte[] userImageData;

    @OneToMany(mappedBy = "user", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true)
    List<Novel> novels;

    @OneToMany(mappedBy = "user", cascade = {CascadeType.MERGE,CascadeType.PERSIST})
    List<Like> likes;
    @OneToMany(mappedBy = "user", cascade = {CascadeType.MERGE,CascadeType.PERSIST})
    List<Comment> comments;


    @CreationTimestamp
    @Column(name = "created_at", updatable = false, nullable = false)
    LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name =  "updated_at", updatable = false, nullable = false)
    LocalDateTime updatedAt;

    @Column(name = "deleted_at")
    LocalDateTime deletedAt;

}
