package facebookAPI.lead;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/api/v2")
public interface LeadAPI {

	@GET
	@Path("/{formId}/leads")
	@Produces(MediaType.APPLICATION_JSON)
	public LeadList listFormLeads(@FormParam("access_token") String auth, @PathParam("formId") String formId);
	
	@GET
	@Path("/{adId}/leads")
	@Produces(MediaType.APPLICATION_JSON)
	public LeadList listAdLeads(@FormParam("access_token") String auth, @PathParam("adId") String formId);
	
	@GET
	@Path("/{leadId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Lead getLead(@FormParam("access_token") String auth, @PathParam("leadId") String leadId);
	
}
