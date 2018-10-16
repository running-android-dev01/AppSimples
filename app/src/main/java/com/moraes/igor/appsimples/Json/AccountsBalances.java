package com.moraes.igor.appsimples.Json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AccountsBalances {
    @JsonProperty("resultSetMetadata")
    public ResultSetMetadata resultSetMetadata;

    @JsonProperty("results")
    public List<AccountsBalancesResult> accountsBalancesResultList;
}
