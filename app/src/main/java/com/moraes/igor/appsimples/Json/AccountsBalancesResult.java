package com.moraes.igor.appsimples.Json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AccountsBalancesResult {
    @JsonProperty("amount")
    public double amount;

    @JsonProperty("balanceDate")
    public String balanceDate;

    @JsonProperty("accountNumber")
    public String accountNumber;

    @JsonProperty("companyId")
    public int companyId;

    @JsonProperty("links")
    public List<Links> links;
}
