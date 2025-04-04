package org.scalesys.url_shortener.enums;

import lombok.Getter;

@Getter
public enum UrlStatus {
    ACTIVE("ACTIVE"),
    EXPIRED("EXPIRED"),
    DELETED("DELETED");

    private final String value;

    UrlStatus(String value) {
        this.value = value;
    }

}
