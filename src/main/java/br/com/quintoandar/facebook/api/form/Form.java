package br.com.quintoandar.facebook.api.form;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown=true)
public class Form {

	private String id;
	
	@JsonProperty("leadgen_export_csv_url")
	private String exportUrl;
	
	private String locale;
	
	private String name;

	private String status;
}
