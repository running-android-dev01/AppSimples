package com.moraes.igor.appsimples.Json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UnitsResult {
    @JsonProperty("id")
    public int id;

    @JsonProperty("enterpriseId")
    public int enterpriseId;

    @JsonProperty("name")
    public String name;

    @JsonProperty("propertyType")
    public String propertyType;

    @JsonProperty("note")
    public String note;

    @JsonProperty("commercialStock")
    public String commercialStock;

    @JsonProperty("latitude")
    public String latitude;

    @JsonProperty("longitude")
    public String longitude;

    @JsonProperty("deliveryDate")
    public String deliveryDate;

    @JsonProperty("privateArea")
    public int privateArea;

    @JsonProperty("commonArea")
    public int commonArea;

    @JsonProperty("terrainArea")
    public int terrainArea;

    @JsonProperty("nonProportionalCommonArea")
    public int nonProportionalCommonArea;

    @JsonProperty("idealFraction")
    public int idealFraction;

    @JsonProperty("generalSaleValueFraction")
    public double generalSaleValueFraction;

    @JsonProperty("terrainValue")
    public double terrainValue;

    @JsonProperty("indexedQuantity")
    public int indexedQuantity;
}
