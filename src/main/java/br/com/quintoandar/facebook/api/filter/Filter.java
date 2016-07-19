package br.com.quintoandar.facebook.api.filter;

import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
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

	@Override
	public String toString() {
		return "{ 'field': '" + this.field + "', 'operator': '" + this.operator.name() + "', 'value': '" + this.value + "'}";
	}

	/*
	ParamFilter toParam() {
		return null;
	}
	
	public static class ParamFilter {
		public String toString() {
			
		}
	}
	*/
}
