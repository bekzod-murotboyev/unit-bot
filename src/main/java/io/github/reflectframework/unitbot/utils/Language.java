package io.github.reflectframework.unitbot.utils;

import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Language {
    EN("en"),
    RU("ru");
    private final String code;

    public static Language of(@Nullable String code) {
        for (Language language : Language.values()) {
            if (language.code.equals(code)) {
                return language;
            }
        }
        throw new IllegalArgumentException("Language not found: " + code);
    }
}
