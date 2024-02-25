package io.github.reflectframework.unitbot.utils;

import io.github.reflectframework.reflecttelegrambot.utils.markers.UserLanguage;

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
