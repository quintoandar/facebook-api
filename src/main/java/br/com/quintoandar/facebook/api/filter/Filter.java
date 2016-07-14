package br.com.quintoandar.facebook.api.filter;

import lombok.Data;

@Data
public class Filter<T> {

	private String field;
	
	private FilterOperator operator;
	
	private T value;	
	
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
