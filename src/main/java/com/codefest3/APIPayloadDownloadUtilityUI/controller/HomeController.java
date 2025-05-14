package com.codefest3.APIPayloadDownloadUtilityUI.controller;

import com.codefest3.APIPayloadDownloadUtilityUI.model.response.ApiPayloadResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {
    private final RestTemplate restTemplate;
    public HomeController(RestTemplate restTemplate) {
    this.restTemplate = restTemplate;
    }

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/payload/fetch")
    public String fetchPayload(@RequestParam("service") String serviceNumber,
                               @RequestParam(value = "reservationNumber", required = false) String reservationNumber,
                               @RequestParam(value = "correlationId", required = false) String correlationId,
                               @RequestParam(value = "fromDate", required = false) String fromDate,
                               @RequestParam(value = "toDate", required = false) String toDate,
                               @RequestParam("identifierType") String identifierType,

                               Model model) {
        ApiPayloadResponse apiPayloadResponse = getApiPayload();

        model.addAttribute("payloadDetails", apiPayloadResponse.getPayloadDetailsList());
        model.addAttribute("service", serviceNumber);
        model.addAttribute("fromDate", fromDate);
        model.addAttribute("toDate", toDate);
        model.addAttribute("identifierType", identifierType);
        model.addAttribute("reservationNumber", reservationNumber);
        model.addAttribute("correlationId", correlationId);
        model.addAttribute("responseAvailable", true);

        return "home";
    }

    public ApiPayloadResponse getApiPayload() {

        String baseUrl = "http://localhost:8081/v1/api/payload/fetch";
        URI uri = UriComponentsBuilder.fromUriString(baseUrl)
            .queryParam("serviceName", "ServiceA")
            .queryParam("confirmationNumber", "12345")
            .queryParam("correlationId", "abcde-12345")
            .queryParam("startDate", "2025-05-12T00:00:00")
            .queryParam("endDate", "2025-05-13T00:00:00")
            .build().toUri();

        return restTemplate.getForObject(uri, ApiPayloadResponse.class);
    }
}
