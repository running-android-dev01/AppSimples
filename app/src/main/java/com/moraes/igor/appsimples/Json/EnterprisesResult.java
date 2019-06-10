package com.moraes.igor.appsimples.Json;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class EnterprisesResult implements Serializable {
    @JsonProperty("id")
    public int id;

    @JsonProperty("name")
    public String name;

    @JsonProperty("cnpj")
    public String cnpj;

    @JsonProperty("type")
    public String type;

    @JsonProperty("adress")
    public String adress;

    @JsonProperty("creationDate")
    public String creationDate;

    @JsonProperty("modificationDate")
    public String modificationDate;

    @JsonProperty("createdBy")
    public String createdBy;

    @JsonProperty("modifiedBy")
    public String modifiedBy;

    @JsonProperty("constructionDetails")
    public ConstructionDetails constructionDetails;

    @JsonProperty("salesDetails")
    public SalesDetails salesDetails;

}
