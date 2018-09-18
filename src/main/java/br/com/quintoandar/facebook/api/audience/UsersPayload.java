package br.com.quintoandar.facebook.api.audience;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

import lombok.Data;

@Data
public class UsersPayload {

  @JsonProperty("schema")
  private List<Schema> schema;

  @JsonProperty("data")
  List<List<String>> data;

  @JsonProperty("is_raw")
  Integer isRaw;

  public UsersPayload() {
    schema = new ArrayList<>(Arrays.asList(Schema.FIRST_NAME, Schema.LAST_NAME, Schema.EMAIL, Schema.PHONE));
  }

  public enum Schema {
    FIRST_NAME("FN"),
    LAST_NAME("LN"),
    EMAIL,
    PHONE;

    String tag;

    Schema(String tag) {
      this.tag = tag;
    }

    Schema() {
      this.tag = this.name();
    }

    @JsonValue
    public String jsonValue() {
      return this.tag;
    }
  }

}
