package org.scalesys.url_shortener.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;
import org.scalesys.url_shortener.entity.AppUser;
import org.scalesys.url_shortener.entity.Url;
import org.scalesys.url_shortener.enums.UrlStatus;
import org.scalesys.url_shortener.repository.UrlRepository;
import org.scalesys.url_shortener.util.UrlEncoder;
import org.scalesys.url_shortener.util.UrlValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
public class UrlService {
    private final UrlRepository urlRepository;
    private final UserService userService;

    @Autowired
    public UrlService(UrlRepository urlRepository, UserService userService) {
        this.urlRepository = urlRepository;
        this.userService = userService;
    }

    public String shortenUrl(String longUrl) throws BadRequestException, MalformedURLException, URISyntaxException {
        log.debug("==>> shortenUrl: {}",longUrl);
        UrlValidator.validate(longUrl);
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
        AppUser appUser = userService.getCurrentUser();
        Url url = Url.builder()
                .longUrl(longUrl)
                .createdAt(LocalDateTime.now())
                .createdBy(appUser)
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
