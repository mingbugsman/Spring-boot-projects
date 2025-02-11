package com.TicketSelling.TicketSelling.Entity;

import com.TicketSelling.TicketSelling.Enum.ConcertStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "concerts")
public class Concert {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    @JoinColumn(name = "concert_name", nullable = false)
    String concertName;

    @Enumerated(EnumType.STRING)
    @JoinColumn(name = "concert_status", nullable = false)
    ConcertStatus concertStatus = ConcertStatus.SCHEDULED;

    // created date event
    @CreationTimestamp
    @Column(name = "date", updatable = false)
    LocalDateTime date;

    // event start date
    @Column(name = "start_time")
    LocalDateTime startDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hall_id", nullable = false)
    Hall hall;

    @ManyToMany(mappedBy = "concerts")
    Set<Band> bands;


    @UpdateTimestamp
    @Column(name = "updated_At")
    LocalDateTime updatedAt;
}
