package br.com.quintoandar.facebook.api;

import br.com.quintoandar.facebook.api.audience.Audience;
import br.com.quintoandar.facebook.api.audience.AudienceAPI;
import br.com.quintoandar.facebook.api.audience.BatchUserUpdate;
import br.com.quintoandar.facebook.api.common.Success;
import java.util.List;
import java.util.Optional;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

import br.com.quintoandar.facebook.api.filter.Filter;
import br.com.quintoandar.facebook.api.filter.Filtering;
import br.com.quintoandar.facebook.api.form.FormAPI;
import br.com.quintoandar.facebook.api.form.FormList;
import br.com.quintoandar.facebook.api.lead.Lead;
import br.com.quintoandar.facebook.api.lead.LeadAPI;
import br.com.quintoandar.facebook.api.lead.LeadList;

public class FacebookAPI {

	private String accessToken;
	
	private String facebookPageId;

	private String adAccountId;
	
	private LeadAPI leadApi;
	
	private FormAPI formApi;

	private AudienceAPI audienceAPI;
	
	public FacebookAPI(String baseUrl, String accessToken, String pageId, String adAccountId) {
		this.accessToken = accessToken;
		this.facebookPageId = pageId;
		this.adAccountId = adAccountId;
		
		ResteasyClient client = new ResteasyClientBuilder().build();
		
		ResteasyWebTarget target = client.target(baseUrl);

		leadApi = target.proxy(LeadAPI.class);
		formApi = target.proxy(FormAPI.class);
		audienceAPI = target.proxy(AudienceAPI.class);
	}
	
	public Lead getLead(String leadId) {
		return leadApi.getLead(this.accessToken, leadId);
	}
	
	public LeadList getFormLeads(String formId, List<Filter> filters, Optional<String> after) {
		return leadApi.listFormLeads(this.accessToken, formId, new Filtering(filters), after.orElse(null));
	}
	
	@Deprecated
	public LeadList getFormLeads(String formId, List<Filter> filters, com.google.common.base.Optional<String> after) {
		return leadApi.listFormLeads(this.accessToken, formId, new Filtering(filters), after.isPresent() ? after.get() : null);
	}
	
	public LeadList getAdLeads(String adId) {
		return leadApi.listAdLeads(this.accessToken, adId, null);
	}
	
	public FormList getPageForms() {
		return formApi.getFormList(accessToken, this.facebookPageId);
	}

	public Audience createAudience(
			String adAccountId,
			String customerFileSource,
			String auth,
			String name,
			String description,
			String subtype
	) {
		return audienceAPI.createAudience(adAccountId, customerFileSource, auth, name, description, subtype);
	}

	public Success deleteAudience(String customAudienceId) {
		return audienceAPI.deleteAudience(customAudienceId);
	}

	public BatchUserUpdate insertUserInAudience(String customAudienceId, String payload) {
		return audienceAPI.insertUserInAudience(customAudienceId, payload);
	}

	public BatchUserUpdate removeUserFromAudience(String customAudienceId, String payload) {
		return audienceAPI.removeUserFromAudience(customAudienceId, payload);
	}
		
}
