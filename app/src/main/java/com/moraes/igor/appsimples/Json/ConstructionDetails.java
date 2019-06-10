package com.moraes.igor.appsimples.Json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ConstructionDetails implements Serializable {
    @JsonProperty("technicalManager")
    public String technicalManager;

    @JsonProperty("numberOfFloors")
    public String numberOfFloors;

    @JsonProperty("totalArea")
    public String totalArea;

    @JsonProperty("landSArea")
    public String landSArea;

    @JsonProperty("startDate")
    public String startDate;

    @JsonProperty("endDate")
    public String endDate;
}
