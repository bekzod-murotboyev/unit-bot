package io.github.reflectframework.unitbot.feignclients;

import io.github.reflectframework.unitbot.dto.spotify.auth.SpotifyAuthRequestBody;
import io.github.reflectframework.unitbot.dto.spotify.auth.SpotifyAuthResponseBody;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "SpotifyAuthFeign", url = "https://accounts.spotify.com/api")
public interface SpotifyAuthFeign {

    @PostMapping(value = "/token", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    SpotifyAuthResponseBody authenticate(
            @RequestBody SpotifyAuthRequestBody dto
    );

}
