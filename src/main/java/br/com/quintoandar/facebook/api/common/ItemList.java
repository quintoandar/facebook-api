package br.com.quintoandar.facebook.api.common;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ItemList<T> {

	@JsonProperty("data")
	private List<T> items;
	
	@JsonProperty("paging.cursors.before")
	private String before;
	
	@JsonProperty("paging.cursors.after")
	private String after;
	
	@JsonProperty("paging.next")
	private String next;
	
	@JsonProperty("paging.previous")
	private String previous;
	
}
