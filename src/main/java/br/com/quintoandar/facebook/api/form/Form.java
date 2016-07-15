package br.com.quintoandar.facebook.api.form;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class Form {

	private String id;
	
	@JsonProperty("lead_gen_export_url")
	private String exportUrl;
	
	private String locale;
	
	private String name;
}
