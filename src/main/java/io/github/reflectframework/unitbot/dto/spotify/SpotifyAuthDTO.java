package io.github.reflectframework.unitbot.dto.spotify;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SpotifyAuthDTO{
    private String accessToken;
    private long expiresTime;
}
