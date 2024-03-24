package io.github.reflectframework.unitbot.dto.spotify.search;

import lombok.Data;

import java.util.List;

@Data
public class Tracks{
	private String next;
	private int total;
	private int offset;
	private Object previous;
	private int limit;
	private String href;
	private List<ItemsItem> items;
}