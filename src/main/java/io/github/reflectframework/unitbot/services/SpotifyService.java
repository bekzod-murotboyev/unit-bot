package io.github.reflectframework.unitbot.services;

import io.github.reflectframework.unitbot.dto.spotify.SpotifyAuthDTO;
import io.github.reflectframework.unitbot.dto.spotify.auth.SpotifyAuthRequestBody;
import io.github.reflectframework.unitbot.dto.spotify.search.ItemsItem;
import io.github.reflectframework.unitbot.dto.spotify.search.SpotifySearchResponseBody;
import io.github.reflectframework.unitbot.feignclients.SpotifyApiFeign;
import io.github.reflectframework.unitbot.feignclients.SpotifyAuthFeign;
import io.github.reflectframework.unitbot.utils.SpotifySearchType;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SpotifyService {

    private final SpotifyAuthFeign authFeign;
    private final SpotifyApiFeign apiFeign;
    private final SpotifyAuthDTO spotifyAuthDTO;

    @Value("${spotify.client-id}")
    private String clientId;
    @Value("${spotify.client-secret}")
    private String clientSecret;


    public void reAuthToken() {
        var response = authFeign.authenticate(new SpotifyAuthRequestBody(clientId, clientSecret));
        spotifyAuthDTO.setAccessToken(response.getTokenType() + " " + response.getAccessToken());
        spotifyAuthDTO.setExpiresTime(System.currentTimeMillis() + response.getExpiresIn());
    }


    public String search(String text) {
        if (spotifyAuthDTO.getExpiresTime() <= System.currentTimeMillis()) {
            reAuthToken();
        }

        SpotifySearchResponseBody data = apiFeign.search(
                spotifyAuthDTO.getAccessToken(), text, SpotifySearchType.TRACK.getValue(),1);
        if (data.getTracks().getItems().isEmpty()) {
            return null;
        }
        ItemsItem item = data.getTracks().getItems().stream().filter(it -> it.getPreviewUrl() != null).findFirst().orElse(null);
        if (item != null) {
            return item.getPreviewUrl();
        }
        return null;
    }


}
