package br.com.quintoandar.facebook.api.common;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown=true)
public class Paging {

	@JsonProperty("next")
	private String next;
	
	@JsonProperty("previous")
	private String previous;
	
}
