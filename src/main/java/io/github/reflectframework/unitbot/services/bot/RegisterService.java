package io.github.reflectframework.unitbot.services.bot;

import io.github.reflectframework.reflecttelegrambot.component.sender.Sender;
import io.github.reflectframework.reflecttelegrambot.entity.user.HashedUser;
import io.github.reflectframework.reflecttelegrambot.util.enums.KeyboardType;
import io.github.reflectframework.reflecttelegrambot.util.marker.UserState;
import io.github.reflectframework.unitbot.services.UserService;
import io.github.reflectframework.unitbot.utils.State;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static io.github.reflectframework.unitbot.utils.Locale.*;

@Service
@RequiredArgsConstructor
public class RegisterService {

    private final Sender sender;

    private final UserService userService;

    public UserState showFirstStartMenu(HashedUser user) {
        sender.sendMessage(user, CHANGE_PHONE_TITLE, KeyboardType.CONTACT);
        return State.SEND_PHONE;
    }

    public UserState savePhoneAndShowMainMenu(HashedUser user, String phoneNumber) {
        userService.updatePhone(user, phoneNumber);
        sender.sendMessage(user, READY, KeyboardType.REMOVE);
        sender.sendMessage(user, CHOOSE_ONE, List.of(List.of(SEARCH_MODE,SETTINGS)));
        return State.MAIN_MENU;
    }

    public UserState showSendMainMenu(HashedUser user) {
        sender.sendMessage(user, CHOOSE_ONE, List.of(List.of(SEARCH_MODE,SETTINGS)));
        return State.MAIN_MENU;
    }

    public UserState showEditMainMenu(HashedUser user) {
        sender.editMessageText(user, CHOOSE_ONE, List.of(List.of(SEARCH_MODE,SETTINGS)));
        return State.MAIN_MENU;
    }
}
