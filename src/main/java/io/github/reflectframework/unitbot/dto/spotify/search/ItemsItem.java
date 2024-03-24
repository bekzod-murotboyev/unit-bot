package io.github.reflectframework.unitbot.dto.spotify.search;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class ItemsItem{
	private int discNumber;
	private Album album;
	private List<String> availableMarkets;
	private String type;
	private ExternalIds externalIds;
	private String uri;
	private int durationMs;
	private boolean explicit;
	private List<ArtistsItem> artists;
	@JsonProperty("preview_url")
	private String previewUrl;
	private int popularity;
	private String name;
	private int trackNumber;
	private String href;
	private String id;
	private boolean isLocal;
	private ExternalUrls externalUrls;
}