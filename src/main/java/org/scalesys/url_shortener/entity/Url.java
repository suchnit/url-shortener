package org.scalesys.url_shortener.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Url {
    @Id
    @GeneratedValue
    private long id;
    private String longUrl;
    private String shortCode;
    private LocalDateTime createdAt;
    private LocalDateTime expiresAt;
    private long hits;
    private String status;

    @ManyToOne
    @JoinColumn(name = "created_by")
    private AppUser createdBy;
}
