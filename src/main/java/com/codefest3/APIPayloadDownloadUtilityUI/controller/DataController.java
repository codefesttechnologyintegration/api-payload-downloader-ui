package com.codefest3.APIPayloadDownloadUtilityUI.controller;

import com.codefest3.APIPayloadDownloadUtilityUI.model.response.ApiPayloadDataResponse;
import com.codefest3.APIPayloadDownloadUtilityUI.model.response.ApiPayloadResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.time.LocalDateTime;

@RestController
public class DataController {

  private final RestTemplate restTemplate;

  public DataController(RestTemplate restTemplate) {
    this.restTemplate = restTemplate;
  }

  @GetMapping("/payload/view")
  public String viewPayload(@RequestParam(value = "serviceName", required = false) String serviceName,
      @RequestParam(value = "correlationId", required = false) String correlationId) {
    return getApiPayload(serviceName, correlationId).getPayload();
  }

  public ApiPayloadDataResponse getApiPayload(String serviceName, String correlationId) {

    String baseUrl = "http://localhost:8081/v1/api/payload/content";
    URI uri = UriComponentsBuilder.fromUriString(baseUrl)
        .queryParam("serviceName", serviceName)
        .queryParam("correlationId", correlationId)
        .build().toUri();

    return restTemplate.getForObject(uri, ApiPayloadDataResponse.class);
  }
}
