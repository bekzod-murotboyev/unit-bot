package io.github.reflectframework.unitbot.utils;

import io.github.reflectframework.reflecttelegrambot.util.marker.UserLanguage;

public enum Language implements UserLanguage {
    EN("en"),
    RU("ru");


    private final String code;

    Language(String code) {
        this.code = code;
    }

    @Override
    public String getCode() {
        return code;
    }
}
