package org.scalesys.url_shortener.controller;

import org.apache.coyote.BadRequestException;
import org.scalesys.url_shortener.service.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.MalformedURLException;
import java.net.URISyntaxException;

@RequestMapping("/api/v1/url/")
@RestController
public class UrlController {
    private final UrlService urlService;

    @Autowired
    public UrlController(UrlService urlService) {
        this.urlService = urlService;
    }

    @PostMapping("shorten")
    public ResponseEntity<String> shortenUrl(@RequestParam String longUrl) throws BadRequestException, MalformedURLException, URISyntaxException {
        final String shortenedUrl = urlService.shortenUrl(longUrl);
        return new ResponseEntity<>(shortenedUrl, HttpStatus.OK);
    }

    @GetMapping("{shortUrl}")
    public ResponseEntity<String> getLongUrl(@PathVariable String shortUrl) throws BadRequestException {
        final String shortenedUrl = urlService.getLongUrl(shortUrl);
        return new ResponseEntity<>(shortenedUrl, HttpStatus.MOVED_TEMPORARILY);
    }
}
