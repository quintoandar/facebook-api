package br.com.quintoandar.facebook.api.lead;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.Iterables;

import lombok.Data;

@Data
public class Lead {
	
	public static final String DATE_PATTERN = "yyyy-MM-dd'T'HH:mm:ssZ";
	public static final String DATE_TIMEZONE = "GMT";
	
	private String id;
	
	@JsonProperty("ad_id")
	private String adId;
	
	@JsonProperty("form_id")
	private String formId;
	
	@JsonProperty("created_time")
	@JsonFormat(pattern=DATE_PATTERN, timezone=DATE_TIMEZONE)
	private Date createdDate;
	
	@JsonProperty("field_data")
	private List<LeadField> data;
	
	public LeadField getFieldByName(String name) {
		return Iterables.tryFind(getData(), p -> name.equals(p.getName())).orNull();
	}
	
	public String getSingleFieldValueByName(String name) {
		LeadField field = getFieldByName(name);
		if (field != null && field.getValues() != null && field.getValues().size() > 0) {
			return field.getValues().get(0);
		}
		return null;
	}
}
