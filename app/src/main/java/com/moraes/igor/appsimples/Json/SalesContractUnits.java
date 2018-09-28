package com.moraes.igor.appsimples.Json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SalesContractUnits {
    @JsonProperty("id")
    public int id;

    @JsonProperty("main")
    public boolean main;

    @JsonProperty("name")
    public String name;

    @JsonProperty("participationPercentage")
    public int participationPercentage;
}
