package org.scalesys.url_shortener.repository;

import org.scalesys.url_shortener.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users, Long> {
}
