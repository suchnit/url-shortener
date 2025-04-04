package org.scalesys.url_shortener.repository;

import org.scalesys.url_shortener.entity.Url;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UrlRepository extends JpaRepository<Url, Long> {
}
