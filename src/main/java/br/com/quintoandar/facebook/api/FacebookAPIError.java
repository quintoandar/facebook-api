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

  private static final String MESSAGE = "message";

  private static final String ERROR_USER_TITLE = "error_user_title";

  private static final String ERROR_USER_MSG = "error_user_msg";

  private static final String TYPE = "type";

  private static final String CODE = "code";

  private static final String ERROR_SUBCODE = "error_subcode";

  @JsonProperty("error")
  public void deserializeError(Map<String, Object> error) {
    message = (String) error.getOrDefault(MESSAGE, null);
    messageTitle = (String) error.getOrDefault(ERROR_USER_TITLE, null);
    messageDetails = (String) error.getOrDefault(ERROR_USER_MSG, null);
    type = (String) error.getOrDefault(TYPE, null);
    code = (Integer) error.getOrDefault(CODE, null);
    subCode = (Integer) error.getOrDefault(ERROR_SUBCODE, null);
  }

}
