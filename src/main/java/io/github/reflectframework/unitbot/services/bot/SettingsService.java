package io.github.reflectframework.unitbot.services.bot;

import io.github.reflectframework.reflecttelegrambot.component.sender.Sender;
import io.github.reflectframework.reflecttelegrambot.entity.user.HashedUser;
import io.github.reflectframework.reflecttelegrambot.util.enums.KeyboardType;
import io.github.reflectframework.reflecttelegrambot.util.i18n.I18nUtils;
import io.github.reflectframework.reflecttelegrambot.util.marker.UserState;
import io.github.reflectframework.unitbot.services.UserService;
import io.github.reflectframework.unitbot.utils.Language;
import io.github.reflectframework.unitbot.utils.State;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;

import static io.github.reflectframework.unitbot.utils.Constant.BACK_TO_MAIN_MENU;
import static io.github.reflectframework.unitbot.utils.Constant.BACK_TO_SETTINGS;
import static io.github.reflectframework.unitbot.utils.Locale.BACK_BUTTON;
import static io.github.reflectframework.unitbot.utils.Locale.CHANGE_LANGUAGE;
import static io.github.reflectframework.unitbot.utils.Locale.CHANGE_PHONE;
import static io.github.reflectframework.unitbot.utils.Locale.CHANGE_PHONE_TITLE;
import static io.github.reflectframework.unitbot.utils.Locale.CHOOSE_ONE;
import static io.github.reflectframework.unitbot.utils.Locale.LANGUAGE_EN;
import static io.github.reflectframework.unitbot.utils.Locale.LANGUAGE_RU;
import static io.github.reflectframework.unitbot.utils.Locale.LOADING;
import static io.github.reflectframework.unitbot.utils.Locale.PHONE_BUTTON;
import static io.github.reflectframework.unitbot.utils.Locale.WRONG_OPTION;

@Service
@RequiredArgsConstructor
@ConditionalOnExpression("'${bot.mode:none}' != 'none'")
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
        sender.sendMessage(user)
                .text(CHANGE_PHONE_TITLE)
                .keyboardRow(KeyboardButton
                        .builder()
                        .text(PHONE_BUTTON)
                        .requestContact(true)
                        .build())
                .keyboardRow(BACK_BUTTON)
                .oneTimeKeyboard(true)
                .resizeKeyboard(true)
                .send();
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
        if (text.equals(I18nUtils.tr(messageSource, backButton, user.getLanguage()))) {
            sender.sendMessage(user, LOADING, KeyboardType.REMOVE);
            return showSendSettingsMenu(user);
        }
        sender.sendMessage(user, WRONG_OPTION);
        return State.CHANGE_PHONE;
    }
}
