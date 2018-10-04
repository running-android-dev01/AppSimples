package com.moraes.igor.appsimples.Json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
class SalesContractCustomers {
    @JsonProperty("id")
    public int id;

    @JsonProperty("main")
    public boolean main;

    @JsonProperty("mainspouse")
    public boolean mainspouse;

    @JsonProperty("participationPercentage")
    public int participationPercentage;
}
