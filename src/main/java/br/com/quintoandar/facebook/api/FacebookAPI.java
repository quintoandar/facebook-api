package br.com.quintoandar.facebook.api;

import java.util.List;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

import br.com.quintoandar.facebook.api.lead.Lead;
import br.com.quintoandar.facebook.api.lead.LeadAPI;

public class FacebookAPI {

	private String accessToken;
	
	private LeadAPI leadApi;
	
	public FacebookAPI(String baseUrl, String accessToken) {
		this.accessToken = accessToken;
		
		ResteasyClient client = new ResteasyClientBuilder().build();
		
		ResteasyWebTarget target = client.target(baseUrl);

		leadApi = target.proxy(LeadAPI.class);
	}
	
	public Lead getLead(String leadId) {
		return leadApi.getLead(this.accessToken, leadId);
	}
	
	public List<Lead> getFormLeads(String formId) {
		return leadApi.listFormLeads(this.accessToken, formId, null).getItems();
	}
	
	public List<Lead> getAdLeads(String adId) {
		return leadApi.listAdLeads(this.accessToken, adId, null).getItems();
	}
		
}
