package br.com.quintoandar.facebook.api.lead;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("/v2.7")
public interface LeadAPI {

	@GET
	@Path("/{formId}/leads")
	@Produces(MediaType.APPLICATION_JSON)
	public LeadList listFormLeads(
			@QueryParam("access_token") String auth, 
			@PathParam("formId") String formId, 
			@QueryParam("since") String since,
			@QueryParam("after") String after);
	
	@GET
	@Path("/{adId}/leads")
	@Produces(MediaType.APPLICATION_JSON)
	public LeadList listAdLeads(@QueryParam("access_token") String auth, @PathParam("adId") String formId);
	
	@GET
	@Path("/{leadId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Lead getLead(@QueryParam("access_token") String auth, @PathParam("leadId") String leadId);
	
}
