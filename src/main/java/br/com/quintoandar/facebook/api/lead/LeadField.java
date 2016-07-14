package br.com.quintoandar.facebook.api.lead;

import java.util.List;

import lombok.Data;

@Data
public class LeadField {
	
	private String name;
	
	private List<String> values;
	
}
