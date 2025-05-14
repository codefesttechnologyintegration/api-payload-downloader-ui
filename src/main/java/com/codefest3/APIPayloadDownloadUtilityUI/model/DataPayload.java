package com.codefest3.APIPayloadDownloadUtilityUI.model;

public class DataPayload {

  private String service;
  private String reservationNumber;
  private String correlationId;
  private String timeStamp;

  public DataPayload(String service, String reservationNumber, String correlationId, String timeStamp) {
    this.service = service;
    this.reservationNumber = reservationNumber;
    this.correlationId = correlationId;
    this.timeStamp = timeStamp;
  }

  public String getService() {
    return service;
  }

  public String getReservationNumber() {
    return reservationNumber;
  }

  public String getCorrelationId() {
    return correlationId;
  }

  public String getTimeStamp() {
    return timeStamp;
  }
}
