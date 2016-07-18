package br.com.quintoandar.facebook.api.form;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("/v2.7")
public interface FormAPI {

	@GET
	@Path("/{pageId}/leadgen_forms")
	@Produces(MediaType.APPLICATION_JSON)
	public FormList getFormList(@QueryParam("access_token") String accessToken, @PathParam("pageId") String pageId);
	
}
