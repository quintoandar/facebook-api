package br.com.quintoandar.facebook.api.lead;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class LeadList {

	@JsonProperty("data")
	private List<Lead> items;
	
	@JsonProperty("paging.cursors.before")
	private String before;
	
	@JsonProperty("paging.cursors.after")
	private String after;
	
}
