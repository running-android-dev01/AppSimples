package com.moraes.igor.appsimples.Json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PaymentConditions {
    @JsonProperty("conditionTypeId")
    public String conditionTypeId;

    @JsonProperty("conditionTypeName")
    public String conditionTypeName;

    @JsonProperty("totalValue")
    public long totalValue;

    @JsonProperty("installmentsNumber")
    public int installmentsNumber;

    @JsonProperty("firstPayment")
    public String firstPayment;

    @JsonProperty("bearerId")
    public int bearerId;

    @JsonProperty("bearerName")
    public String bearerName;
}
