package io.github.reflectframework.unitbot.entities;

import io.github.reflectframework.reflecttelegrambot.entities.user.TelegramUserDetails;
import io.github.reflectframework.reflecttelegrambot.utils.markers.UserLanguage;
import io.github.reflectframework.reflecttelegrambot.utils.markers.UserState;
import io.github.reflectframework.unitbot.utils.Language;
import io.github.reflectframework.unitbot.utils.State;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "_user")
@Getter
@Setter
@NoArgsConstructor
public class UserEntity implements TelegramUserDetails {

    private String name;

    private String username;

    private String phoneNumber;

    private long chatId;

    @Enumerated(EnumType.STRING)
    private State state;

    @Enumerated(EnumType.STRING)
    private Language language;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    public UserEntity(String name, String username, long chatId, State state, Language language) {
        this.name = name;
        this.username = username;
        this.chatId = chatId;
        this.state = state;
        this.language = language;
    }


    @Nonnull
    @Override
    public Long getChatId() {
        return chatId;
    }

    @Nonnull
    @Override
    public UserState getState() {
        return state;
    }

    @Nullable
    @Override
    public UserLanguage getLanguage() {
        return language;
    }
}
