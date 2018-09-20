package br.com.quintoandar.facebook.api.audience;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;
import lombok.Data;

@Data
public class BatchUserUpdate {

  @JsonProperty("audience_id")
  String audienceId;

  @JsonProperty("session_id")
  String sessionId;

  @JsonProperty("num_received")
  Long numReceived;

  @JsonProperty("num_invalid_entries")
  Long numInvalidEntries;

  @JsonProperty("invalid_entry_samples")
  Map<String, String> invalidEntrySamples;

}
