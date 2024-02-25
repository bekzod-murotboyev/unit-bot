package io.github.reflectframework.unitbot.dto.spotify.search;

import java.util.List;
import lombok.Data;

@Data
public class Album{
	private List<ImagesItem> images;
	private List<String> availableMarkets;
	private String releaseDatePrecision;
	private String type;
	private String uri;
	private int totalTracks;
	private List<ArtistsItem> artists;
	private String releaseDate;
	private String name;
	private String albumType;
	private String href;
	private String id;
	private ExternalUrls externalUrls;
}