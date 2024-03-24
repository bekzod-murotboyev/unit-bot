package io.github.reflectframework.unitbot.configurations;

import io.github.reflectframework.unitbot.dto.spotify.SpotifyAuthDTO;
import io.github.reflectframework.unitbot.dto.spotify.auth.SpotifyAuthRequestBody;
import io.github.reflectframework.unitbot.dto.spotify.auth.SpotifyAuthResponseBody;
import io.github.reflectframework.unitbot.feignclients.SpotifyAuthFeign;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {


    @Bean
    public SpotifyAuthDTO spotifyAuthDTO(
            SpotifyAuthFeign feign,
            @Value("${spotify.client-id}")
            String clientId,
            @Value("${spotify.client-secret}")
            String clientSecret
    ) {
        SpotifyAuthResponseBody response = feign.authenticate(new SpotifyAuthRequestBody(clientId, clientSecret));
        return new SpotifyAuthDTO(response.getTokenType()+" "+response.getAccessToken(), System.currentTimeMillis() + response.getExpiresIn() * 1000);
    }


}
