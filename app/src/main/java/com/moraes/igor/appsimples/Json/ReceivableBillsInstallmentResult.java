package com.moraes.igor.appsimples.Json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ReceivableBillsInstallmentResult {
    @JsonProperty("receivableBillId")
    public int receivableBillId;

    @JsonProperty("installmentId")
    public int installmentId;

    @JsonProperty("conditionTypeId")
    public String conditionTypeId;

    @JsonProperty("dueDate")
    public String dueDate;

    @JsonProperty("balanceDue")
    public int balanceDue;

    @JsonProperty("generatedTicket")
    public boolean generatedTicket;
}
