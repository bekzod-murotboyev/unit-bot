package io.github.reflectframework.unitbot.entities;

import io.github.reflectframework.reflecttelegrambot.entity.user.TelegramUserDetails;
import io.github.reflectframework.unitbot.utils.Language;
import io.github.reflectframework.unitbot.utils.State;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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

    @Column(name = "chat_id")
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
    public String getState() {
        return state.name();
    }

    @Nullable
    @Override
    public String getLanguageCode() {
        return language.getCode();
    }
}
