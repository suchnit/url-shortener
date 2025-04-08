package org.scalesys.url_shortener.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
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
    private int hits;
    private int userId;
    private String status;
}
