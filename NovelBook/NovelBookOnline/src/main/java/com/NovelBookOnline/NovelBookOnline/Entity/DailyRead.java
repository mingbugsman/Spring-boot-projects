package com.NovelBookOnline.NovelBookOnline.Entity;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "daily_read", indexes = {
        @Index(name = "idx_id_createdAt", columnList = "chapter_id,created_at"),
        @Index(name = "idx_daily_read", columnList = "chapter_id, created_at, read_count")
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
    LocalDate createdAt;

    @Column(name = "read_count", nullable = false)
    int readCount;
}
