package org.scalesys.url_shortener.repository;

import org.scalesys.url_shortener.entity.Url;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UrlRepository extends JpaRepository<Url, Long> {
    Url findByShortCode(String shortCode);
    Url findByLongUrl(String longUrl);
}
