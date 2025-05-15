package com.codefest3.APIPayloadDownloadUtilityUI.model.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class ApiPayloadDataResponse {
  public String payload;

}

