package com.NovelBookOnline.NovelBookOnline.Entity;


import com.NovelBookOnline.NovelBookOnline.Entity.Like.Like;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "users",
        indexes = {
          @Index(name = "idx_deletedAt", columnList = "deleted_at")
        },
        uniqueConstraints = {
            @UniqueConstraint(columnNames = "username"),
            @UniqueConstraint(columnNames = "email")}
)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    @Column(name = "email", nullable = false ,columnDefinition = "VARCHAR(100)")
    String email;

    @Column(name = "password", nullable = false, columnDefinition = "VARCHAR(255)")
    String password;

    @Column(name = "username", columnDefinition = "VARCHAR(12)")
    String username;

    @Column(name = "gender",columnDefinition = "BIT")
    Boolean gender;

    @Lob
    @Column(name = "user_image_data", nullable = true, columnDefinition = "LONGBLOB")
    byte[] userImageData;

    @Column(name = "description", columnDefinition = "VARCHAR(50)")
    String description;

    @OneToOne
    @JoinColumn(name = "author_id")
    Author author;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    List<Like> likes;

    @OneToMany(mappedBy = "user", cascade = {CascadeType.MERGE,CascadeType.PERSIST})
    List<Comment> comments;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(  name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    Set<Role> roles = new HashSet<>();

    @CreationTimestamp
    @Column(name = "created_at", updatable = false, nullable = false)
    LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name =  "updated_at", updatable = false, nullable = false)
    LocalDateTime updatedAt;

    @Column(name = "deleted_at")
    LocalDateTime deletedAt;

}
