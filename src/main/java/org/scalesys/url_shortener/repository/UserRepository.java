package org.scalesys.url_shortener.repository;

import org.scalesys.url_shortener.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<AppUser, Long> {
    Optional<AppUser> findByName(String username);
}
