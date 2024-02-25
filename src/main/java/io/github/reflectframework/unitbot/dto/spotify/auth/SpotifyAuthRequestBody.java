package io.github.reflectframework.unitbot.dto.spotify.auth;

import com.fasterxml.jackson.annotation.JsonProperty;
import feign.form.FormProperty;
import lombok.Data;

@Data
public class SpotifyAuthRequestBody {
    @FormProperty("grant_type")
    private String grantType = "client_credentials";
    @FormProperty("client_id")
    private String clientId;
    @FormProperty("client_secret")
    private String clientSecret;

    public SpotifyAuthRequestBody(String clientId, String clientSecret) {
        this.clientId = clientId;
        this.clientSecret = clientSecret;
    }
}