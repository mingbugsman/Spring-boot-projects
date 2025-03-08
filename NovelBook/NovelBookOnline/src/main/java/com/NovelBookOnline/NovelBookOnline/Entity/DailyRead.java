package com.NovelBookOnline.NovelBookOnline.Entity;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "daily_read", indexes = {
        @Index(name = "idx_createdAt", columnList = "created_at"),
        @Index(name = "idx_chapterId", columnList = "chapter_id")
})
public class DailyRead {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    @ManyToOne
    @JoinColumn(name = "chapter_id", nullable = false)
    Chapter chapter;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false, nullable = false)
    LocalDateTime createdAt;

    @Column(name = "read_count", nullable = false)
    int readCount;
}
