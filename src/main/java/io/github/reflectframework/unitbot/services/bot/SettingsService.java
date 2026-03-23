package io.github.reflectframework.unitbot.services.bot;

import io.github.reflectframework.reflecttelegrambot.component.sender.Sender;
import io.github.reflectframework.reflecttelegrambot.entity.user.HashedUser;
import io.github.reflectframework.unitbot.services.UserService;
import io.github.reflectframework.unitbot.utils.Language;
import io.github.reflectframework.unitbot.utils.State;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardRemove;
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

@Service
@RequiredArgsConstructor
@ConditionalOnExpression("'${bot.mode:none}' != 'none'")
public class SettingsService {
    private final UserService userService;

    private final Sender sender;

    public String showEditSettingsMenu(HashedUser user) {
        sender.editMessageText(user)
                .text(CHOOSE_ONE)
                .inlineKeyboardRow(CHANGE_PHONE, CHANGE_LANGUAGE)
                .inlineKeyboardRow(Map.of(BACK_TO_MAIN_MENU, BACK_BUTTON))
                .send();
        return State.SETTINGS_MENU.name();
    }


    public String showSendSettingsMenu(HashedUser user) {
        sender.sendMessage(user)
                .text(CHOOSE_ONE)
                .inlineKeyboardRow(CHANGE_PHONE, CHANGE_LANGUAGE)
                .inlineKeyboardRow(Map.of(BACK_TO_MAIN_MENU, BACK_BUTTON))
                .send();
        return State.SETTINGS_MENU.name();
    }

    public String showChangeLanguageMenu(HashedUser user) {
        sender.editMessageText(user)
                .text(CHOOSE_ONE)
                .inlineKeyboardRow(LANGUAGE_EN, LANGUAGE_RU)
                .inlineKeyboardRow(Map.of(BACK_TO_SETTINGS, BACK_BUTTON))
                .send();
        return State.CHANGE_LANGUAGE.name();
    }

    public String showChangePhoneMenu(HashedUser user) {
        sender.deleteMessage(user.getChatId(), user.getLastMessageId()).send();
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
        return State.CHANGE_PHONE.name();
    }

    public String changeLanguage(HashedUser user, String data) {
        switch (data) {
            case LANGUAGE_EN -> userService.updateLanguage(user, Language.EN);
            case LANGUAGE_RU -> userService.updateLanguage(user, Language.RU);
        }

        return showEditSettingsMenu(user);
    }

    public String changeContactAndShowSettingsMenu(HashedUser user, String phone) {
        userService.updatePhone(user, phone);
        return showSendSettingsMenu(user);
    }

    public String backToSettingsMenuFromChangePhone(HashedUser user) {
        sender.sendMessage(user, LOADING)
                .replyKeyboard(new ReplyKeyboardRemove(true))
                .send();
        return showSendSettingsMenu(user);
    }
}
