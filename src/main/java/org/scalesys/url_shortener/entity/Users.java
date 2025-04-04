package org.scalesys.url_shortener.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
public class Users {
    @Id
    private long id;
    private String email;
    private String password;
    private LocalDateTime createdAt;
}
