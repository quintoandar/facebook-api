package br.com.quintoandar.facebook.api.filter;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class Filter {

	@JsonProperty
	private String field;
	
	@JsonProperty
	private FilterOperator operator;
	
	@JsonProperty
	private String value;
	
	public static enum FilterOperator {  
		EQUAL,
		NOT_EQUAL,
		GREATER_THAN,
		GREATER_THAN_OR_EQUAL,
		LESS_THAN,
		LESS_THAN_OR_EQUAL,
		IN_RANGE,
		NOT_IN_RANGE,
		CONTAIN,
		NOT_CONTAIN,
		IN,
		NOT_IN,
		ANY,
		ALL,
		NONE
	}
}
