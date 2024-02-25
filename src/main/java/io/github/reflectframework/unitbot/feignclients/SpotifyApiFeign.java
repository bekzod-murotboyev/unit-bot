package io.github.reflectframework.unitbot.feignclients;

import io.github.reflectframework.unitbot.dto.spotify.search.SpotifySearchResponseBody;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "SpotifyApiFeign", url = "https://api.spotify.com/v1")
public interface SpotifyApiFeign {


    @GetMapping("/search")
    SpotifySearchResponseBody search(
            @RequestHeader("Authorization") String authorization,
            @RequestParam("q") String text,
            @RequestParam String type,
            @RequestParam int limit
    );

}
