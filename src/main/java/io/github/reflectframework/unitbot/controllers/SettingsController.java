package io.github.reflectframework.unitbot.controllers;

import io.github.reflectframework.reflecttelegrambot.annotation.BotController;
import io.github.reflectframework.reflecttelegrambot.annotation.mapping.CallbackQueryMapping;
import io.github.reflectframework.reflecttelegrambot.annotation.mapping.ContactMapping;
import io.github.reflectframework.reflecttelegrambot.annotation.mapping.TextMapping;
import io.github.reflectframework.reflecttelegrambot.entity.user.HashedUser;
import io.github.reflectframework.reflecttelegrambot.util.marker.UserState;
import io.github.reflectframework.unitbot.services.bot.SettingsService;
import io.github.reflectframework.unitbot.utils.State;
import lombok.RequiredArgsConstructor;

import static io.github.reflectframework.unitbot.utils.Constant.BACK_TO_SETTINGS;
import static io.github.reflectframework.unitbot.utils.Locale.*;

@BotController
@RequiredArgsConstructor
public class SettingsController {

    private final SettingsService settingsService;

    @CallbackQueryMapping(dataRegexp = SETTINGS, states = {State.Fields.MAIN_MENU})
    public UserState showSettingsMenu(HashedUser user) {
        return settingsService.showEditSettingsMenu(user);
    }

    @CallbackQueryMapping(dataRegexp = BACK_TO_SETTINGS, states = {State.Fields.CHANGE_PHONE, State.Fields.CHANGE_LANGUAGE})
    public UserState backToSettingsMenu(HashedUser user) {
        return settingsService.showEditSettingsMenu(user);
    }

    @CallbackQueryMapping(dataRegexp = CHANGE_PHONE)
    public UserState showChangePhoneMenu(HashedUser user) {
        return settingsService.showChangePhoneMenu(user);
    }

    @CallbackQueryMapping(dataRegexp = CHANGE_LANGUAGE)
    public UserState showChangeLanguageMenu(HashedUser user) {
        return settingsService.showChangeLanguageMenu(user);
    }

    @CallbackQueryMapping(dataRegexp = "(" + LANGUAGE_EN + "|" + LANGUAGE_RU + "+)", target = CallbackQueryMapping.CallbackQueryMappingTarget.QUERY_DATA)
    public UserState changeLanguage(HashedUser user, String data) {
        return settingsService.changeLanguage(user, data);
    }

    @ContactMapping(states = {State.Fields.CHANGE_PHONE}, target = ContactMapping.ContactMappingTarget.PHONE_NUMBER)
    public UserState changeContactAndShowSettingsMenu(HashedUser user, String phone) {
        return settingsService.changeContactAndShowSettingsMenu(user, phone);
    }
    @TextMapping(states = {State.Fields.CHANGE_PHONE, CHANGE_LANGUAGE})
    public UserState backToSettingsMenuFromChangePhone(HashedUser user,String text) {
        return settingsService.backToSettingsMenuFromChangePhone(user,text);
    }

}
