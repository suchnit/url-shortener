package org.scalesys.url_shortener.controller;

import org.scalesys.url_shortener.service.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1/url/")
@RestController
public class UrlController {
    private final UrlService urlService;

    @Autowired
    public UrlController(UrlService urlService) {
        this.urlService = urlService;
    }

    @PostMapping
    public ResponseEntity<String> shortenUrl(String longUrl) {
        final String shortenedUrl = urlService.shortenUrl(longUrl);
        return new ResponseEntity<>(shortenedUrl, HttpStatus.OK);
    }
}
