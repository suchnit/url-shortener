package org.scalesys.url_shortener.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;
import org.scalesys.url_shortener.entity.Url;
import org.scalesys.url_shortener.enums.UrlStatus;
import org.scalesys.url_shortener.repository.UrlRepository;
import org.scalesys.url_shortener.util.UrlEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
public class UrlService {
    private final UrlRepository urlRepository;

    @Autowired
    public UrlService(UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }

    public String shortenUrl(String longUrl) throws BadRequestException {
        log.debug("==>> shortenUrl: {}",longUrl);

        if(!StringUtils.hasText(longUrl)) {
            throw new BadRequestException("Invalid longUrl");
        }

        String shortCode;
        Url urlFromDb = urlRepository.findByLongUrl(longUrl);

        if(urlFromDb == null) {
            shortCode = createUrl(longUrl).getShortCode();
        } else {
            validate(urlFromDb);
            shortCode = urlFromDb.getShortCode();
        }

        log.debug("<<== shortenUrl: {}",shortCode);
        return shortCode;
    }

    public String getLongUrl(String shortUrl) throws BadRequestException {
        log.debug("==>> getLongUrl: {}",shortUrl);
        String shortCode = shortUrl;
        final Url url = urlRepository.findByShortCode(shortCode);

        if(url == null) {
            throw new BadRequestException("Invalid short url");
        }

        validate(url);
        String longUrl = url.getLongUrl();
        log.debug("<<== getLongUrl: {}",longUrl);
        return longUrl;
    }

    private Url createUrl(String longUrl) {
        Url url = Url.builder()
                .longUrl(longUrl)
                .createdAt(LocalDateTime.now())
                //TODO set proper user
                .userId(1)
                .status(UrlStatus.ACTIVE.getValue())
                .build();
        Url savedUrl = urlRepository.save(url); // generates ID

        String shortCode = UrlEncoder.encode(savedUrl.getId());
        url.setShortCode(shortCode);
        // Async update to reduce latency
        CompletableFuture.runAsync(() -> urlRepository.save(url));

        return url;
    }

    private void validate(Url url) throws BadRequestException {
        if(url.getStatus().equals(UrlStatus.EXPIRED.getValue()) || url.getStatus().equals(UrlStatus.DELETED.getValue())) {
            throw new BadRequestException("Url has expired or is deleted");
        }
    }
}
