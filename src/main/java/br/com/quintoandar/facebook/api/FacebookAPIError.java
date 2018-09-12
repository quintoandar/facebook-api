package br.com.quintoandar.facebook.api;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class FacebookAPIError {

  private String message;

  private String messageTitle;

  private String messageDetails;

  private Integer code;

  private Integer subCode;

  private String type;

  @JsonProperty("error")
  public void deserializeError(Map<String, Object> error) {
    message = (String) error.getOrDefault("message", null);
    messageTitle = (String) error.getOrDefault("error_user_title", null);
    messageDetails = (String) error.getOrDefault("error_user_msg", null);
    type = (String) error.getOrDefault("type", null);
    code = (Integer) error.getOrDefault("code", null);
    subCode = (Integer) error.getOrDefault("error_subcode", null);
  }

}
