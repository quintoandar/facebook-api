package br.com.quintoandar.facebook.api;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import lombok.Getter;

public class FacebookAPIException extends WebApplicationException {

  @Getter
  private FacebookAPIError error;

  FacebookAPIException(Response response) {
    super(response);
    this.error = response.readEntity(FacebookAPIError.class);
  }

  FacebookAPIException(String message) {
    FacebookAPIError tempError = new FacebookAPIError();
    tempError.setMessage(message);
  }

}
