package org.scalesys.url_shortener.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;
import org.springframework.util.StringUtils;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

@Slf4j
public class UrlValidator {
    public static void validate(String url) throws MalformedURLException, BadRequestException, URISyntaxException {
        if(!StringUtils.hasText(url)) {
            throw new BadRequestException("Invalid longUrl");
        }

        try {
            new URL(url).toURI();
        } catch (Exception e) {
            log.error("Invalid url: {}",url,e);
            throw e;
        }
    }
}
