package org.scalesys.url_shortener.service;

import org.scalesys.url_shortener.entity.Url;
import org.scalesys.url_shortener.enums.UrlStatus;
import org.scalesys.url_shortener.repository.UrlRepository;
import org.scalesys.url_shortener.util.UrlEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UrlService {
    private final UrlRepository urlRepository;

    @Autowired
    public UrlService(UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }

    public String shortenUrl(String longUrl) {
        Url entity = Url.builder()
                .longUrl(longUrl)
                .createdAt(LocalDateTime.now())
                .status(UrlStatus.ACTIVE.getValue())
                .build();
        urlRepository.save(entity); // generates ID

        String shortCode = UrlEncoder.encode(entity.getId());
        entity.setShortCode(shortCode);
        urlRepository.save(entity);

        return shortCode;
    }
}
