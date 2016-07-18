package br.com.quintoandar.facebook.api;

import java.util.Arrays;
import java.util.List;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

import com.google.common.base.Optional;

import br.com.quintoandar.facebook.api.filter.Filter;
import br.com.quintoandar.facebook.api.form.FormAPI;
import br.com.quintoandar.facebook.api.form.FormList;
import br.com.quintoandar.facebook.api.lead.Lead;
import br.com.quintoandar.facebook.api.lead.LeadAPI;
import br.com.quintoandar.facebook.api.lead.LeadList;

public class FacebookAPI {

	private String accessToken;
	
	private String facebookPageId;
	
	private LeadAPI leadApi;
	
	private FormAPI formApi;
	
	public FacebookAPI(String baseUrl, String accessToken, String pageId) {
		this.accessToken = accessToken;
		this.facebookPageId = pageId;
		
		ResteasyClient client = new ResteasyClientBuilder().build();
		
		ResteasyWebTarget target = client.target(baseUrl);

		leadApi = target.proxy(LeadAPI.class);
		formApi = target.proxy(FormAPI.class);
	}
	
	public Lead getLead(String leadId) {
		return leadApi.getLead(this.accessToken, leadId);
	}
	
	public LeadList getFormLeads(String formId, Optional<Long> sinceTimestamp, Optional<String> after) {
		List<Filter> filters = Arrays.asList();
		if (sinceTimestamp.isPresent()) {
			Filter filter = new Filter();
			filter.setField("time_created");
			filter.setOperator(Filter.FilterOperator.GREATER_THAN);
			filter.setValue(sinceTimestamp.get().toString());
			filters.add(filter);
		}
		return leadApi.listFormLeads(this.accessToken, formId, filters, after.isPresent() ? after.get() : null);
	}
	
	public LeadList getAdLeads(String adId) {
		return leadApi.listAdLeads(this.accessToken, adId, null);
	}
	
	public FormList getPageForms() {
		return formApi.getFormList(accessToken, this.facebookPageId);
	}
		
}
