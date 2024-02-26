package io.github.reflectframework.unitbot.controllers;

import io.github.reflectframework.reflecttelegrambot.annotations.BotController;
import io.github.reflectframework.reflecttelegrambot.annotations.mappings.CallbackQueryMapping;
import io.github.reflectframework.reflecttelegrambot.annotations.mappings.TextMapping;
import io.github.reflectframework.reflecttelegrambot.entities.user.HashedUser;
import io.github.reflectframework.reflecttelegrambot.utils.markers.UserState;
import io.github.reflectframework.unitbot.services.bot.SearchService;
import io.github.reflectframework.unitbot.utils.State;
import lombok.RequiredArgsConstructor;

import static io.github.reflectframework.unitbot.utils.Locale.SEARCH_MODE;

@BotController
@RequiredArgsConstructor
public class SearchController {

    private final SearchService service;

    @CallbackQueryMapping(dataRegexp = SEARCH_MODE)
    public UserState showSearchModeMenu(HashedUser user) {
        return service.showSearchModeMenu(user);
    }

    @TextMapping(states = {State.Fields.SEARCH_MODE_MENU})
    public UserState search(HashedUser user,String searchText){
        return service.search(user,searchText);
    }
}
