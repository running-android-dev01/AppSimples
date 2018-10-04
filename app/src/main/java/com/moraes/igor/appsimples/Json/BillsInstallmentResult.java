package com.moraes.igor.appsimples.Json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BillsInstallmentResult {
    @JsonProperty("indexId")
    public int indexId;

    @JsonProperty("baseDate")
    public String baseDate;

    @JsonProperty("dueDate")
    public String dueDate;

    @JsonProperty("billDate")
    public String billDate;

    @JsonProperty("amount")
    public int amount;

    @JsonProperty("installmentNumber")
    public int installmentNumber;

    @JsonProperty("links")
    public List<Links> links;
}
