package br.com.quintoandar.facebook.api.audience;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import lombok.Data;

@Data
public class UsersPayload {

  private List<Schema> schemas;

  List<List<String>> data;

  @JsonProperty("is_raw")
  Integer isRaw;

  public UsersPayload() {
    schemas = new ArrayList<>(Arrays.asList(Schema.FIRST_NAME, Schema.LAST_NAME, Schema.EMAIL, Schema.EMAIL));
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
  }

}
