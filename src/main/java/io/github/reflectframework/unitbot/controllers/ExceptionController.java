package io.github.reflectframework.unitbot.controllers;


import io.github.reflectframework.reflecttelegrambot.annotation.BotController;
import io.github.reflectframework.reflecttelegrambot.annotation.mappings.TextMapping;
import io.github.reflectframework.reflecttelegrambot.component.sender.Sender;
import io.github.reflectframework.reflecttelegrambot.entity.user.HashedUser;
import io.github.reflectframework.reflecttelegrambot.util.marker.UserState;
import lombok.RequiredArgsConstructor;

import static io.github.reflectframework.unitbot.utils.Locale.UNKNOWN_OPTION;

@BotController(order = Integer.MAX_VALUE)
@RequiredArgsConstructor
public class ExceptionController {

    private final Sender sender;

    @TextMapping(regexp = "[\\w.-]*")
    public UserState exceptionHandler(HashedUser user) {
        sender.sendMessage(user, UNKNOWN_OPTION);
        return user.getState();
    }

}
