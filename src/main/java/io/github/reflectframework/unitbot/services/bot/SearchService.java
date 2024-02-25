package io.github.reflectframework.unitbot.services.bot;

import io.github.reflectframework.reflecttelegrambot.components.sender.base.Sender;
import io.github.reflectframework.reflecttelegrambot.entities.user.HashedUser;
import io.github.reflectframework.reflecttelegrambot.utils.markers.UserState;
import io.github.reflectframework.unitbot.services.SpotifyService;
import io.github.reflectframework.unitbot.utils.State;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static io.github.reflectframework.unitbot.utils.Locale.MP3_NOT_FOUND;
import static io.github.reflectframework.unitbot.utils.Locale.SEARCH_MODE_TITLE;

@Service
@RequiredArgsConstructor
public class SearchService {

    private final Sender sender;
    private final SpotifyService spotifyService;

    public UserState showSearchModeMenu(HashedUser user) {
        sender.editMessageText(user, SEARCH_MODE_TITLE);
        return State.SEARCH_MODE_MENU;
    }

    public UserState search(HashedUser user, String searchText) {
        String searchResult = spotifyService.search(searchText);

        if (searchResult != null) {
            sender.sendAudio(user, searchResult);
        } else {
            sender.sendMessage(user, MP3_NOT_FOUND);
        }

        return State.SEARCH_MODE_MENU;
    }
}
