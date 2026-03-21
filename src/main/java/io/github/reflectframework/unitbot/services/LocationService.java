package io.github.reflectframework.unitbot.services;

import io.github.reflectframework.reflecttelegrambot.component.sender.Sender;
import io.github.reflectframework.reflecttelegrambot.entity.user.HashedUser;
import io.github.reflectframework.reflecttelegrambot.util.marker.UserState;
import io.github.reflectframework.unitbot.utils.Locale;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.location.Location;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardRemove;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;

@Service
@RequiredArgsConstructor
public class LocationService {

    private final Sender sender;

    public UserState handleLocationMenu(HashedUser user) {
        sender.sendMessage(user)
                .text(Locale.CHOOSE_ONE)
                .keyboardRow(KeyboardButton
                        .builder()
                        .text(Locale.LOCATION_BUTTON)
                        .requestLocation(true)
                        .build())
                .resizeKeyboard(true)
                .send();
        return user.getState();
    }

    public UserState handleLocation(HashedUser user, Location location) {
        sender.sendLocation(user)
                .latitude(location.getLatitude())
                .longitude(location.getLongitude())
                .replyKeyboard(new ReplyKeyboardRemove(true))
                .send();
        return user.getState();
    }
}
