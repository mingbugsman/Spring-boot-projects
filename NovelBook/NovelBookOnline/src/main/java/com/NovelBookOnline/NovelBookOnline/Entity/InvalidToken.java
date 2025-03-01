package com.NovelBookOnline.NovelBookOnline.Entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@Entity
@Table(name = "black_list")
public class InvalidToken {

    @Id
    @Column(name = "jwt_id", nullable = false)
    String jwtId;

    @Column(name = "revocation_date", nullable = false)
    LocalDateTime revocationDate;

}
