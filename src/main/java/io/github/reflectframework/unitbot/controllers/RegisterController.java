package io.github.reflectframework.unitbot.controllers;

import io.github.reflectframework.reflecttelegrambot.annotations.BotController;
import io.github.reflectframework.reflecttelegrambot.annotations.mappings.CallbackQueryMapping;
import io.github.reflectframework.reflecttelegrambot.annotations.mappings.ContactMapping;
import io.github.reflectframework.reflecttelegrambot.annotations.mappings.TextMapping;
import io.github.reflectframework.reflecttelegrambot.entities.user.HashedUser;
import io.github.reflectframework.reflecttelegrambot.utils.markers.UserState;
import io.github.reflectframework.unitbot.services.bot.RegisterService;
import io.github.reflectframework.unitbot.utils.Constant;
import io.github.reflectframework.unitbot.utils.State;
import lombok.RequiredArgsConstructor;

@BotController
@RequiredArgsConstructor
public class RegisterController {

    private final RegisterService registerService;
    @TextMapping(regexp = "/start", states = {State.Fields.INIT})
    public UserState showFirstStartMenu(HashedUser user) {
        return registerService.showFirstStartMenu(user);
    }

    @TextMapping(regexp = "/start")
    public UserState savePhoneAndShowMainMenu(HashedUser user) {
        return registerService.showSendMainMenu(user);
    }

    @ContactMapping(states = {State.Fields.SEND_PHONE}, target = ContactMapping.ContactMappingTarget.PHONE_NUMBER)
    public UserState savePhoneAndShowMainMenu(HashedUser user, String phoneNumber) {
        return registerService.savePhoneAndShowMainMenu(user, phoneNumber);
    }

    @CallbackQueryMapping(dataRegexp = Constant.BACK_TO_MAIN_MENU, states = {State.Fields.SETTINGS_MENU})
    public UserState showMainMenu(HashedUser user) {
        return registerService.showEditMainMenu(user);
    }

}
