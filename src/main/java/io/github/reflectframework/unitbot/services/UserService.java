package io.github.reflectframework.unitbot.services;

import io.github.reflectframework.reflecttelegrambot.entities.user.HashedUser;
import io.github.reflectframework.reflecttelegrambot.entities.user.TelegramUserDetails;
import io.github.reflectframework.reflecttelegrambot.services.user.TelegramUserDetailsService;
import io.github.reflectframework.unitbot.entities.UserEntity;
import io.github.reflectframework.unitbot.repositories.UserRepository;
import io.github.reflectframework.unitbot.utils.Language;
import io.github.reflectframework.unitbot.utils.State;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.User;

@Service
@RequiredArgsConstructor
public class UserService implements TelegramUserDetailsService {

    private final UserRepository userRepository;

    @Nullable
    @Override
    public TelegramUserDetails findByChatId(long chatId) {
        return userRepository.findByChatId(chatId);
    }

    @Nonnull
    @Override
    public TelegramUserDetails save(@Nonnull User user, long chatId) {
        return userRepository.save(new UserEntity(user.getFirstName(), user.getUserName(), chatId, State.INIT, Language.EN));
    }

    @Override
    public void update(@Nonnull HashedUser user) {
        UserEntity entity = userRepository.findByChatId(user.getChatId());
        if (user.getState() instanceof State state && user.getLanguage() instanceof Language language) {
            entity.setState(state);
            entity.setLanguage(language);
            userRepository.save(entity);
        }
    }

    public void updatePhone(HashedUser user, String phoneNumber) {
        UserEntity userEntity = userRepository.findByChatId(user.getChatId());
        userEntity.setPhoneNumber(phoneNumber);
        userRepository.save(userEntity);
    }

    public void updateLanguage(HashedUser user, Language language) {
        UserEntity userEntity = userRepository.findByChatId(user.getChatId());
        user.setLanguage(language);
        userEntity.setLanguage(language);
        userRepository.save(userEntity);
    }
}
