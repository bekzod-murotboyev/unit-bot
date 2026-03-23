package io.github.reflectframework.unitbot.services.bot;

import io.github.reflectframework.reflecttelegrambot.component.sender.Sender;
import io.github.reflectframework.reflecttelegrambot.entity.user.HashedUser;
import io.github.reflectframework.unitbot.services.UserService;
import io.github.reflectframework.unitbot.utils.State;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.message.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardRemove;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;

import static io.github.reflectframework.unitbot.utils.Locale.CHANGE_PHONE_TITLE;
import static io.github.reflectframework.unitbot.utils.Locale.CHOOSE_ONE;
import static io.github.reflectframework.unitbot.utils.Locale.PHONE_BUTTON;
import static io.github.reflectframework.unitbot.utils.Locale.READY;
import static io.github.reflectframework.unitbot.utils.Locale.SEARCH_MODE;
import static io.github.reflectframework.unitbot.utils.Locale.SETTINGS;

@Service
@RequiredArgsConstructor
@ConditionalOnExpression("'${bot.mode:none}' != 'none'")
public class RegisterService {

    private final Sender sender;

    private final UserService userService;

    public String showFirstStartMenu(HashedUser user) {
        Message send = sender.sendMessage(user)
                .text(CHANGE_PHONE_TITLE)
                .keyboardRow(KeyboardButton.builder()
                        .text(PHONE_BUTTON)
                        .requestContact(true)
                        .build())
                .send();
        return State.SEND_PHONE.name();
    }

    public String savePhoneAndShowMainMenu(HashedUser user, String phoneNumber) {
        userService.updatePhone(user, phoneNumber);
        sender.sendMessage(user, READY)
                .replyKeyboard(new ReplyKeyboardRemove(true))
                .send();
        sender.sendMessage(user, CHOOSE_ONE)
                .inlineKeyboardRow(SEARCH_MODE, SETTINGS)
                .send();
        return State.MAIN_MENU.name();
    }

    public String showSendMainMenu(HashedUser user) {
        sender.sendMessage(user)
                .text(CHOOSE_ONE)
                .inlineKeyboardRow(SEARCH_MODE, SETTINGS)
                .send();
        return State.MAIN_MENU.name();
    }

    public String showEditMainMenu(HashedUser user) {
        sender.editMessageText(user)
                .text(CHOOSE_ONE)
                .inlineKeyboardRow(SEARCH_MODE, SETTINGS)
                .send();
        return State.MAIN_MENU.name();
    }
}
