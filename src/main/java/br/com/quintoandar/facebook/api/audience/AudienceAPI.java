package br.com.quintoandar.facebook.api.audience;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.quintoandar.facebook.api.common.Success;

@Path("/v2.11")
public interface AudienceAPI {

  @POST
  @Path("/act_{adAccountId}/customaudiences")
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  Audience createAudience(
      @FormParam("access_token") String auth,
      @PathParam("adAccountId") String adAccountId,
      @FormParam("customer_file_source") String customerFileSource,
      @FormParam("name") String name,
      @FormParam("description") String description,
      @FormParam("subtype") String subtype
  );

  @DELETE
  @Path("/{customAudienceId}")
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  Success deleteAudience(@PathParam("customAudienceId") String customAudienceId);

  @POST
  @Path("/{customAudienceId}/users")
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  BatchUserUpdate insertUserInAudience(
      @PathParam("customAudienceId") String customAudienceId,
      String payload
  );

  @DELETE
  @Path("/{customAudienceId}/users")
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  BatchUserUpdate removeUserFromAudience(
      @PathParam("customAudienceId") String customAudienceId,
      String payload
  );

}
