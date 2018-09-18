package br.com.quintoandar.facebook.api.log;

import java.io.IOException;

import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;

public class LoggingFilter implements ClientRequestFilter {
  @Override
  public void filter(ClientRequestContext requestContext) throws IOException {
    requestContext.setEntityStream(new LoggingOutputStreamWrapper(requestContext.getEntityStream()));
  }
}
