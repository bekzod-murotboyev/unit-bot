package io.github.reflectframework.unitbot.services;

import io.github.reflectframework.reflecttelegrambot.component.sender.Sender;
import io.github.reflectframework.reflecttelegrambot.entity.user.HashedUser;
import io.github.reflectframework.reflecttelegrambot.network.client.Reflector;
import io.github.reflectframework.reflecttelegrambot.payload.media.Media;
import io.github.reflectframework.reflecttelegrambot.service.mediagroup.MediaGroupQueue;
import io.github.reflectframework.reflecttelegrambot.util.marker.UserState;
import io.github.reflectframework.unitbot.utils.Locale;
import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Audio;
import org.telegram.telegrambots.meta.api.objects.Document;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Video;
import org.telegram.telegrambots.meta.api.objects.Voice;
import org.telegram.telegrambots.meta.api.objects.games.Animation;
import org.telegram.telegrambots.meta.api.objects.media.InputMedia;
import org.telegram.telegrambots.meta.api.objects.media.InputMediaAnimation;
import org.telegram.telegrambots.meta.api.objects.media.InputMediaAudio;
import org.telegram.telegrambots.meta.api.objects.media.InputMediaDocument;
import org.telegram.telegrambots.meta.api.objects.media.InputMediaPhoto;
import org.telegram.telegrambots.meta.api.objects.media.InputMediaVideo;
import org.telegram.telegrambots.meta.api.objects.photo.PhotoSize;

@Service
@RequiredArgsConstructor
public class DocumentService {

    private final Sender sender;
    private final Reflector reflector;

    public UserState handlePhoto(HashedUser user, PhotoSize photoSize) {
        sender.sendPhoto(user)
                .photo(photoSize.getFileId())
                .caption(Locale.READY)
                .keyboardRow(Locale.READY, Locale.LOADING)
                .keyboardRow(Locale.BACK_BUTTON)
                .oneTimeKeyboard(true)
                .resizeKeyboard(true)
                .send();
        return user.getState();
    }

    public UserState handleStoredPhoto(HashedUser user, PhotoSize photoSize) {
        byte[] file = reflector.getFile(photoSize.getFileId());
        if (file == null) {
            sender.sendMessage(user, Locale.WRONG_OPTION).send();
            return user.getState();
        }

        sender.sendPhoto(user)
                .photo(new InputFile(new ByteArrayInputStream(file), photoSize.getFileId()))
                .caption(Locale.READY)
                .send();
        return user.getState();
    }

    public UserState handleVideo(HashedUser user, Video video) {
        sender.sendVideo(user)
                .video(video.getFileId())
                .caption(Locale.READY)
                .keyboardRow(Locale.READY, Locale.LOADING)
                .keyboardRow(Locale.BACK_BUTTON)
                .oneTimeKeyboard(true)
                .resizeKeyboard(true)
                .send();
        return user.getState();
    }

    public UserState handleStoredVideo(HashedUser user, Video video) {
        byte[] file = reflector.getFile(video.getFileId());
        if (file == null) {
            sender.sendMessage(user, Locale.WRONG_OPTION).send();
            return user.getState();
        }

        sender.sendVideo(user)
                .video(new InputFile(new ByteArrayInputStream(file), video.getFileId()))
                .caption(Locale.READY)
                .send();
        return user.getState();
    }

    public UserState handleVoice(HashedUser user, Voice voice) {
        sender.sendVoice(user)
                .voice(voice.getFileId())
                .caption(Locale.READY)
                .keyboardRow(Locale.READY, Locale.LOADING)
                .keyboardRow(Locale.BACK_BUTTON)
                .oneTimeKeyboard(true)
                .resizeKeyboard(true)
                .send();
        return user.getState();
    }

    public UserState handleStoredVoice(HashedUser user, Voice voice) {
        byte[] file = reflector.getFile(voice.getFileId());
        if (file == null) {
            sender.sendMessage(user, Locale.WRONG_OPTION).send();
            return user.getState();
        }

        sender.sendVoice(user)
                .voice(new InputFile(new ByteArrayInputStream(file), voice.getFileId()))
                .caption(Locale.READY)
                .send();
        return user.getState();
    }

    public UserState handleAudio(HashedUser user, Audio audio) {
        sender.sendAudio(user)
                .audio(audio.getFileId())
                .caption(Locale.READY)
                .keyboardRow(Locale.READY, Locale.LOADING)
                .keyboardRow(Locale.BACK_BUTTON)
                .oneTimeKeyboard(true)
                .resizeKeyboard(true)
                .send();
        return user.getState();
    }

    public UserState handleStoredAudio(HashedUser user, Audio audio) {
        byte[] file = reflector.getFile(audio.getFileId());
        if (file == null) {
            sender.sendMessage(user, Locale.WRONG_OPTION).send();
            return user.getState();
        }

        sender.sendAudio(user)
                .audio(new InputFile(new ByteArrayInputStream(file), audio.getFileName()))
                .caption(Locale.READY)
                .send();
        return user.getState();
    }

    public UserState handleDocument(HashedUser user, Document document) {
        sender.sendDocument(user)
                .document(document.getFileId())
                .caption(Locale.READY)
                .keyboardRow(Locale.READY, Locale.LOADING)
                .keyboardRow(Locale.BACK_BUTTON)
                .oneTimeKeyboard(true)
                .resizeKeyboard(true)
                .send();
        return user.getState();
    }

    public UserState handleStoredDocument(HashedUser user, Document document) {
        byte[] file = reflector.getFile(document.getFileId());
        if (file == null) {
            sender.sendMessage(user, Locale.WRONG_OPTION).send();
            return user.getState();
        }

        sender.sendDocument(user)
                .document(new InputFile(new ByteArrayInputStream(file), document.getFileName()))
                .caption(Locale.READY)
                .send();
        return user.getState();
    }

    public UserState handleMediaGroup(HashedUser user, MediaGroupQueue queue) {
        sender.sendMediaGroup(user)
                .medias(queue.takeAsList().stream().map(this::mediaOf).toList())
                .caption(Locale.READY)
                .send();
        return user.getState();
    }

    private InputMedia mediaOf(Media media) {
        return switch (media.getType()) {
            case PHOTO -> new InputMediaPhoto(media.getFileId());
            case VIDEO -> new InputMediaVideo(media.getFileId());
            case DOCUMENT -> new InputMediaDocument(media.getFileId());
            case AUDIO -> new InputMediaAudio(media.getFileId());
            case ANIMATION -> new InputMediaAnimation(media.getFileId());
        };
    }

    public UserState handleStoredMediaGroup(HashedUser user, MediaGroupQueue queue) {
        List<InputMedia> mediaList = new ArrayList<>();
        for (Media media : queue.takeAsList()) {
            switch (media.getType()) {
                case PHOTO ->
                        mediaList.add(new InputMediaPhoto(new ByteArrayInputStream(Objects.requireNonNull(reflector.getFile(media.getFileId()))), media.getFileId()));
                case VIDEO -> mediaList.add(new InputMediaVideo(media.getFileId()));
                case DOCUMENT ->
                        mediaList.add(new InputMediaDocument(new ByteArrayInputStream(Objects.requireNonNull(reflector.getFile(media.getFileId()))), media.getFilename()));
                case AUDIO ->
                        mediaList.add(new InputMediaAudio(new ByteArrayInputStream(Objects.requireNonNull(reflector.getFile(media.getFileId()))), media.getFilename()));
                case ANIMATION ->
                        mediaList.add(new InputMediaAnimation(new ByteArrayInputStream(Objects.requireNonNull(reflector.getFile(media.getFileId()))), media.getFilename()));
            }
        }
        sender.sendMediaGroup(user)
                .medias(mediaList)
                .caption(Locale.READY)
                .send();
        return user.getState();
    }

    public UserState handleAnimation(HashedUser user, Animation animation) {
        sender.sendAnimation(user)
                .animation(animation.getFileId())
                .caption(Locale.READY)
                .keyboardRow(Locale.READY, Locale.LOADING)
                .keyboardRow(Locale.BACK_BUTTON)
                .oneTimeKeyboard(true)
                .resizeKeyboard(true)
                .send();
        return user.getState();
    }

    public UserState handleStoredAnimation(HashedUser user, Animation animation) {
        byte[] file = reflector.getFile(animation.getFileId());
        if (file == null) {
            sender.sendMessage(user, Locale.WRONG_OPTION).send();
            return user.getState();
        }

        sender.sendVideo(user)
                .video(new InputFile(new ByteArrayInputStream(file), animation.getFileId()))
                .caption(Locale.READY)
                .send();
        return user.getState();
    }
}
