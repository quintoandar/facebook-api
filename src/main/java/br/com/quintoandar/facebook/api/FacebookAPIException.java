package br.com.quintoandar.facebook.api;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

public class FacebookAPIException extends WebApplicationException {

  private String facebookMessage;

  FacebookAPIException(Response response) {
    super(response);
    switch (response.getStatus()) {
      case 100:
        facebookMessage = "Invalid parameter";
        break;
      case 105:
        facebookMessage = "The number of parameters exceeded the maximum for this operation";
      case 200:
        facebookMessage = "Permissions error";
        break;
      case 294:
        facebookMessage = "Managing advertisements requires an access token with the extended permission for ads_management";
        break;
      case 2650:
        facebookMessage = "Failed to update the custom audience";
        break;
      case 2651:
        facebookMessage = "Failed to create lookalike custom audience";
        break;
      case 2656:
        facebookMessage = "Failed to delete custom audience because associated lookalikes exist";
        break;
    }
  }

  FacebookAPIException(String facebookMessage) {
    super();
    this.facebookMessage = facebookMessage;
  }

}
