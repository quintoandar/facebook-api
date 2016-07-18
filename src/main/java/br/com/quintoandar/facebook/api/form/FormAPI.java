package br.com.quintoandar.facebook.api.form;

import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;

@Path("/v2.7")
public interface FormAPI {

	@Path("/{pageId}/leadgen_forms")
	public FormList getFormList(@QueryParam("access_token") String accessToken, @PathParam("pageId") String pageId);
	
}
