package io.github.reflectframework.unitbot.utils;

import io.github.reflectframework.reflecttelegrambot.util.marker.UserState;
import lombok.experimental.FieldNameConstants;

@FieldNameConstants(onlyExplicitlyIncluded = true)
public enum State implements UserState {
    @FieldNameConstants.Include INIT,
    @FieldNameConstants.Include SEND_PHONE,
    @FieldNameConstants.Include MAIN_MENU,
    @FieldNameConstants.Include SETTINGS_MENU,
    @FieldNameConstants.Include CHANGE_PHONE,
    @FieldNameConstants.Include CHANGE_LANGUAGE,
    @FieldNameConstants.Include SEARCH_MODE_MENU,
}
