package io.github.reflectframework.unitbot.controllers;

import io.github.reflectframework.reflecttelegrambot.annotation.BotController;
import io.github.reflectframework.reflecttelegrambot.annotation.mapping.LocationMapping;
import io.github.reflectframework.reflecttelegrambot.annotation.mapping.TextMapping;
import io.github.reflectframework.reflecttelegrambot.entity.user.HashedUser;
import io.github.reflectframework.reflecttelegrambot.util.marker.UserState;
import io.github.reflectframework.unitbot.services.LocationService;
import lombok.RequiredArgsConstructor;
import org.telegram.telegrambots.meta.api.objects.location.Location;

@BotController
@RequiredArgsConstructor
public class LocationController {

    private final LocationService service;

    @TextMapping(regexp = "/location")
    public UserState handleLocationMenu(HashedUser user){
        return service.handleLocationMenu(user);
    }

    @LocationMapping
    public UserState handleLocation(HashedUser user, Location location){
        return service.handleLocation(user,location);
    }
}
