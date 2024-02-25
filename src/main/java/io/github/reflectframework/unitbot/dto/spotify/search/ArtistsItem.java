package io.github.reflectframework.unitbot.dto.spotify.search;

import lombok.Data;

@Data
public class ArtistsItem{
	private String name;
	private String href;
	private String id;
	private String type;
	private ExternalUrls externalUrls;
	private String uri;
}