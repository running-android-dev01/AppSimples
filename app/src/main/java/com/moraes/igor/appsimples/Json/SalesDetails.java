package com.moraes.igor.appsimples.Json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SalesDetails implements Serializable {
    @JsonProperty("keysDelivery")
    public String keysDelivery;

    @JsonProperty("generalSalesValue")
    public String generalSalesValue;
}
