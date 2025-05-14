package com.codefest3.APIPayloadDownloadUtilityUI.controller;

import com.codefest3.APIPayloadDownloadUtilityUI.model.response.ApiPayloadResponse;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
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
    public String fetchPayload(@RequestParam("service") String serviceName,
                               @RequestParam(value = "confirmationNumber", required = false) String confirmationNumber,
                               @RequestParam(value = "correlationId", required = false) String correlationId,
                               @RequestParam(value = "startDate", required = false) String startDate,
                               @RequestParam(value = "toDate", required = false) String endDate,
                               @RequestParam("identifierType") String identifierType,

                               Model model) {
        ApiPayloadResponse apiPayloadResponse = getApiPayload(serviceName, confirmationNumber, correlationId,
            startDate, endDate);

        model.addAttribute("payloadDetails", apiPayloadResponse.getPayloadDetailsList());
        model.addAttribute("service", serviceName);
        model.addAttribute("startDate", startDate);
        model.addAttribute("endDate", endDate);
        model.addAttribute("identifierType", identifierType);
        model.addAttribute("confirmationNumber", confirmationNumber);
        model.addAttribute("correlationId", correlationId);
        model.addAttribute("responseAvailable", true);

        return "home";
    }

    public ApiPayloadResponse getApiPayload(String serviceName, String confirmationNumber, String correlationId,
        String startDate, String endDate) {

        LocalDateTime localStartDate = LocalDate.parse(startDate).atStartOfDay();
        LocalDateTime localEndDate = LocalDate.parse(endDate).atStartOfDay();

        String baseUrl = "http://localhost:8081/v1/api/payload/fetch";
        URI uri = UriComponentsBuilder.fromUriString(baseUrl)
            .queryParam("serviceName", serviceName)
            .queryParam("confirmationNumber", confirmationNumber)
            .queryParam("correlationId", correlationId)
            .queryParam("startDate", localStartDate)
            .queryParam("endDate", localEndDate)
            .build().toUri();

        return restTemplate.getForObject(uri, ApiPayloadResponse.class);
    }
}
