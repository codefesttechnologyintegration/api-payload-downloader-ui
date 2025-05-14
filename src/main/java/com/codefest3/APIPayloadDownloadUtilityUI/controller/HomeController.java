package com.codefest3.APIPayloadDownloadUtilityUI.controller;

import com.codefest3.APIPayloadDownloadUtilityUI.model.DataPayload;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/payload/fetch")
    public String fetchPayload(@RequestParam("service") String service,
                               @RequestParam(value = "fromDate", required = false) String fromDate,
                               @RequestParam(value = "toDate", required = false) String toDate,
                               @RequestParam("identifierType") String identifierType,
                               @RequestParam(value = "reservationNumber", required = false) String reservationNumber,
                               @RequestParam(value = "correlationId", required = false) String correlationId,
                               Model model) {
        List<DataPayload> datapayloads = getAllDataPayloads();
        model.addAttribute("datapayloads", datapayloads);
        model.addAttribute("service", service);
        model.addAttribute("fromDate", fromDate);
        model.addAttribute("toDate", toDate);
        model.addAttribute("identifierType", identifierType);
        model.addAttribute("reservationNumber", reservationNumber);
        model.addAttribute("correlationId", correlationId);
        model.addAttribute("responseAvailable", true);

        return "home";
    }

    public List<DataPayload> getAllDataPayloads() {
        List<DataPayload> dataPayloads = new ArrayList<>();
        DataPayload dataPayload=new DataPayload("TestService 1","Reservation Number 1",
            "Correlation ID 1","11-11-2024");

        DataPayload dataPayload1=new DataPayload("TestService 2","Reservation Number 2",
            "Correlation ID 2","11-11-2027");
        dataPayloads.add(dataPayload);
        dataPayloads.add(dataPayload1);
        return dataPayloads;
    }
}
