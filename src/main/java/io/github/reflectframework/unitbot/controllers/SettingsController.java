package io.github.reflectframework.unitbot.controllers;

import io.github.reflectframework.reflecttelegrambot.annotation.BotController;
import io.github.reflectframework.reflecttelegrambot.annotation.mapping.CallbackQueryMapping;
import io.github.reflectframework.reflecttelegrambot.annotation.mapping.ContactMapping;
import io.github.reflectframework.reflecttelegrambot.annotation.mapping.TextMapping;
import io.github.reflectframework.reflecttelegrambot.entity.user.HashedUser;
import io.github.reflectframework.unitbot.services.bot.SettingsService;
import io.github.reflectframework.unitbot.utils.State;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;

import static io.github.reflectframework.unitbot.utils.Constant.BACK_TO_SETTINGS;
import static io.github.reflectframework.unitbot.utils.Locale.BACK_BUTTON;
import static io.github.reflectframework.unitbot.utils.Locale.CHANGE_LANGUAGE;
import static io.github.reflectframework.unitbot.utils.Locale.CHANGE_PHONE;
import static io.github.reflectframework.unitbot.utils.Locale.LANGUAGE_EN;
import static io.github.reflectframework.unitbot.utils.Locale.LANGUAGE_RU;
import static io.github.reflectframework.unitbot.utils.Locale.SETTINGS;

@BotController
@RequiredArgsConstructor
@ConditionalOnExpression("'${bot.mode:none}' != 'none'")
public class SettingsController {

    private final SettingsService settingsService;

    @CallbackQueryMapping(dataRegexp = SETTINGS, states = {State.Fields.MAIN_MENU})
    public String showSettingsMenu(HashedUser user) {
        return settingsService.showEditSettingsMenu(user);
    }

    @CallbackQueryMapping(dataRegexp = BACK_TO_SETTINGS, states = {State.Fields.CHANGE_PHONE, State.Fields.CHANGE_LANGUAGE})
    public String backToSettingsMenu(HashedUser user) {
        return settingsService.showEditSettingsMenu(user);
    }

    @CallbackQueryMapping(dataRegexp = CHANGE_PHONE)
    public String showChangePhoneMenu(HashedUser user) {
        return settingsService.showChangePhoneMenu(user);
    }

    @CallbackQueryMapping(dataRegexp = CHANGE_LANGUAGE)
    public String showChangeLanguageMenu(HashedUser user) {
        return settingsService.showChangeLanguageMenu(user);
    }

    @CallbackQueryMapping(dataRegexp = "(" + LANGUAGE_EN + "|" + LANGUAGE_RU + "+)", target = CallbackQueryMapping.CallbackQueryMappingTarget.QUERY_DATA)
    public String changeLanguage(HashedUser user, String data) {
        return settingsService.changeLanguage(user, data);
    }

    @ContactMapping(states = {State.Fields.CHANGE_PHONE}, target = ContactMapping.ContactMappingTarget.PHONE_NUMBER)
    public String changeContactAndShowSettingsMenu(HashedUser user, String phone) {
        return settingsService.changeContactAndShowSettingsMenu(user, phone);
    }

    @TextMapping(regexp = BACK_BUTTON, translateRegexp = true, states = {State.Fields.CHANGE_PHONE, CHANGE_LANGUAGE})
    public String backToSettingsMenuFromChangePhone(HashedUser user) {
        return settingsService.backToSettingsMenuFromChangePhone(user);
    }

}
