package io.github.reflectframework.unitbot.controllers;

import io.github.reflectframework.reflecttelegrambot.annotation.BotController;
import io.github.reflectframework.reflecttelegrambot.annotation.mapping.media.MediaGroupMapping;
import io.github.reflectframework.reflecttelegrambot.annotation.mapping.media.animation.AnimationMapping;
import io.github.reflectframework.reflecttelegrambot.annotation.mapping.media.audio.AudioMapping;
import io.github.reflectframework.reflecttelegrambot.annotation.mapping.media.document.DocumentMapping;
import io.github.reflectframework.reflecttelegrambot.annotation.mapping.media.photo.PhotoMapping;
import io.github.reflectframework.reflecttelegrambot.annotation.mapping.media.video.VideoMapping;
import io.github.reflectframework.reflecttelegrambot.annotation.mapping.media.voice.VoiceMapping;
import io.github.reflectframework.reflecttelegrambot.entity.user.HashedUser;
import io.github.reflectframework.reflecttelegrambot.service.mediagroup.MediaGroupQueue;
import io.github.reflectframework.reflecttelegrambot.util.marker.UserState;
import io.github.reflectframework.unitbot.services.DocumentService;
import lombok.RequiredArgsConstructor;
import org.telegram.telegrambots.meta.api.objects.Audio;
import org.telegram.telegrambots.meta.api.objects.Document;
import org.telegram.telegrambots.meta.api.objects.Video;
import org.telegram.telegrambots.meta.api.objects.Voice;
import org.telegram.telegrambots.meta.api.objects.games.Animation;
import org.telegram.telegrambots.meta.api.objects.photo.PhotoSize;

@BotController
@RequiredArgsConstructor
public class DocumentController {

    private final DocumentService service;

    @PhotoMapping
    public UserState handlePhoto(HashedUser user, PhotoSize photoSize) {
        return service.handlePhoto(user, photoSize);
//        return service.handleStoredPhoto(user,photoSize);
    }

    @VideoMapping
    public UserState handleVideo(HashedUser user, Video video) {
        return service.handleVideo(user, video);
//        return service.handleStoredVideo(user,video);
    }

    @VoiceMapping
    public UserState handleVoice(HashedUser user, Voice voice) {
        return service.handleVoice(user, voice);
//        return service.handleStoredVoice(user,voice);
    }

    @AudioMapping
    public UserState handleVoice(HashedUser user, Audio audio) {
        return service.handleAudio(user, audio);
//        return service.handleStoredAudio(user,audio);
    }

    @DocumentMapping
    public UserState handleDocument(HashedUser user, Document document) {
//        return service.handleDocument(user,document);
        return service.handleStoredDocument(user, document);
    }

    @MediaGroupMapping
    public UserState handleMediaGroup(HashedUser user, MediaGroupQueue queue) {
        return service.handleMediaGroup(user, queue);
//        return service.handleStoredMediaGroup(user,queue);
    }

    @AnimationMapping
    public UserState handleAnimation(HashedUser user, Animation animation) {
//        return service.handleAnimation(user,animation);
        return service.handleStoredAnimation(user,animation);
    }
}
