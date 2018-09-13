package br.com.quintoandar.facebook.api;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;
import org.jboss.resteasy.spi.LoggableFailure;

import br.com.quintoandar.facebook.api.audience.Audience;
import br.com.quintoandar.facebook.api.audience.AudienceAPI;
import br.com.quintoandar.facebook.api.audience.BatchUserUpdate;
import br.com.quintoandar.facebook.api.audience.UsersPayload;
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

	private static final String AUDIENCE_DELETION_ERROR = "Could not delete Facebook audience with id: {}";
	
	public FacebookAPI(String baseUrl, String accessToken, String pageId, String adAccountId) {
		this.accessToken = accessToken;
		this.facebookPageId = pageId;
		this.adAccountId = adAccountId;
		
		ResteasyClient client = new ResteasyClientBuilder().build();

		client.register(new LoggingFilter());
		
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
		return formApi.getFormList(this.accessToken, this.facebookPageId);
	}

	public Audience createAudience(
			String customerFileSource,
			String name,
			String description,
			String subtype,
			Long retentionDays
	) {
		try {
			return audienceAPI.createAudience(
					this.accessToken,
					this.adAccountId,
					customerFileSource,
					name,
					description,
					subtype,
					null);
		} catch (LoggableFailure e) {
			throw new FacebookAPIException(e.getResponse());
		} catch (WebApplicationException e) {
			throw new FacebookAPIException(e.getResponse());
		}

	}

	public void deleteAudience(String customAudienceId) {
		try {
			if(!audienceAPI.deleteAudience(this.accessToken, customAudienceId).getSuccess()) {
				throw new FacebookAPIException(String.format(AUDIENCE_DELETION_ERROR, customAudienceId));
			}
		} catch (LoggableFailure e) {
			throw  new FacebookAPIException(e.getResponse());
		} catch (WebApplicationException e) {
			throw  new FacebookAPIException(e.getResponse());
		}
	}

	public BatchUserUpdate insertUserInAudience(String customAudienceId, UsersPayload payload) {
		try {
			Map<String, Object> payloadMap = new HashMap<>();
			payloadMap.put("payload", payload);
			return audienceAPI.insertUserInAudience(this.accessToken, customAudienceId, payloadMap);
		} catch (LoggableFailure e) {
			throw  new FacebookAPIException(e.getResponse());
		} catch (WebApplicationException e) {
			throw  new FacebookAPIException(e.getResponse());
		}
	}

	public BatchUserUpdate removeUserFromAudience(String customAudienceId, UsersPayload payload) {
		try {
			Map<String, Object> payloadMap = new HashMap<>();
			payloadMap.put("payload", payload);
			return audienceAPI.removeUserFromAudience(this.accessToken, customAudienceId, payloadMap);
		} catch (LoggableFailure e) {
			throw  new FacebookAPIException(e.getResponse());
		} catch (WebApplicationException e) {
			throw  new FacebookAPIException(e.getResponse());
		}
	}

	public class LoggingOutputStreamWrapper extends OutputStream {
		final Logger logger = Logger.getLogger(LoggingOutputStreamWrapper.class.getName());
		ByteArrayOutputStream myBuffer = new ByteArrayOutputStream();
		private OutputStream target;
		private Level loggingLevel = Level.OFF;

		public LoggingOutputStreamWrapper(OutputStream target) {
			this.target = target;
		}

		@Override
		public void write(int data) throws IOException {
			try {
				myBuffer.write(data);
				target.write(data);
				// When using @FormParam this logs will have to be enabled for debugging
				logger.log(loggingLevel, myBuffer.toString());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		@Override
		public void write(byte [] data){
			try {
				myBuffer.write(data);
				target.write(data);
				// When using @FormParam this logs will have to be enabled for debugging
				logger.log(loggingLevel, myBuffer.toString());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		@Override
		public void flush() {
			logger.log(loggingLevel, myBuffer.toString());
			try {
				target.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		@Override
		public void close(){
			// This is the standard log to use
			logger.log(loggingLevel, myBuffer.toString());
			try {
				target.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public class LoggingFilter implements ClientRequestFilter {
		@Override
		public void filter(ClientRequestContext requestContext) throws IOException {
			requestContext.setEntityStream(new LoggingOutputStreamWrapper(requestContext.getEntityStream()));
		}
	}

}
