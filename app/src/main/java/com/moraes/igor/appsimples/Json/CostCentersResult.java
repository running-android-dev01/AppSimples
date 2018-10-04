package com.moraes.igor.appsimples.Json;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CostCentersResult implements Serializable {
    @JsonProperty("id")
    public int id;

    @JsonProperty("name")
    public String name;

    @JsonProperty("cnpj")
    public String cnpj;

    @JsonProperty("idCompany")
    public int idCompany;

    @JsonIgnore
    public final String endereco = "Avenida Viera Souto, 1250 - Copacabana - 22000-000 Rio de Janeiro";

    @JsonIgnore
    public final String responsavel = "Eduardo Henriques";
}
