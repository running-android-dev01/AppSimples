package com.moraes.igor.appsimples.Json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ResultSetMetadata {
    @JsonProperty("count")
    public int count;

    @JsonProperty("offset")
    public int offset;

    @JsonProperty("limit")
    public int limit;
}
