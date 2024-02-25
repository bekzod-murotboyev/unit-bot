package io.github.reflectframework.unitbot.services.bot;

import io.github.reflectframework.reflecttelegrambot.components.sender.base.Sender;
import io.github.reflectframework.reflecttelegrambot.entities.user.HashedUser;
import io.github.reflectframework.reflecttelegrambot.utils.enums.KeyboardType;
import io.github.reflectframework.reflecttelegrambot.utils.extensions.Extension;
import io.github.reflectframework.reflecttelegrambot.utils.markers.UserState;
import io.github.reflectframework.unitbot.services.UserService;
import io.github.reflectframework.unitbot.utils.Language;
import io.github.reflectframework.unitbot.utils.State;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.List;

import static io.github.reflectframework.unitbot.utils.Constant.BACK_TO_MAIN_MENU;
import static io.github.reflectframework.unitbot.utils.Constant.BACK_TO_SETTINGS;
import static io.github.reflectframework.unitbot.utils.Locale.*;

@Service
@RequiredArgsConstructor
public class SettingsService {
    private final UserService userService;

    private final Sender sender;

    private final MessageSource messageSource;

    @Value("${bot.i18.key.back-button}")
    private String backButton;

    public UserState showEditSettingsMenu(HashedUser user) {
        sender.editMessageText(user, CHOOSE_ONE, List.of(List.of(CHANGE_PHONE, CHANGE_LANGUAGE),
                List.of(BACK_TO_MAIN_MENU)));
        return State.SETTINGS_MENU;
    }


    public UserState showSendSettingsMenu(HashedUser user) {
        sender.sendMessage(user, CHOOSE_ONE, List.of(List.of(CHANGE_PHONE, CHANGE_LANGUAGE),
                List.of(BACK_TO_MAIN_MENU)));
        return State.SETTINGS_MENU;
    }

    public UserState showChangeLanguageMenu(HashedUser user) {
        sender.editMessageText(user, CHOOSE_ONE, List.of(List.of(LANGUAGE_EN, LANGUAGE_RU), List.of(BACK_TO_SETTINGS)));
        return State.CHANGE_LANGUAGE;
    }

    public UserState showChangePhoneMenu(HashedUser user) {
        sender.deleteMessage(user);
        sender.sendMessage(user, CHANGE_PHONE_TITLE, KeyboardType.CONTACT_WITH_BACK);
        return State.CHANGE_PHONE;
    }

    public UserState changeLanguage(HashedUser user, String data) {
        switch (data) {
            case LANGUAGE_EN -> userService.updateLanguage(user, Language.EN);
            case LANGUAGE_RU -> userService.updateLanguage(user, Language.RU);
        }

        return showEditSettingsMenu(user);
    }

    public UserState changeContactAndShowSettingsMenu(HashedUser user, String phone) {
        userService.updatePhone(user, phone);
        return showSendSettingsMenu(user);
    }

    public UserState backToSettingsMenuFromChangePhone(HashedUser user, String text) {
        if (text.equals(Extension.tr(messageSource, backButton, user.getLanguage()))) {
            sender.sendMessage(user, LOADING, KeyboardType.REMOVE);
            return showSendSettingsMenu(user);
        }
        sender.sendMessage(user, WRONG_OPTION);
        return State.CHANGE_PHONE;
    }
}
