package io.github.reflectframework.unitbot.utils;

public enum SpotifySearchType {
    ARTIST("artist"),
    PLAYLIST("playlist"),
    ALBUM("album"),
    TRACK("track");

    private final String value;

    SpotifySearchType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
