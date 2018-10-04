package com.moraes.igor.appsimples.Json;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class BillsInstallment {
    @JsonProperty("resultSetMetadata")
    public ResultSetMetadata resultSetMetadata;

    @JsonProperty("results")
    public List<BillsInstallmentResult> billsResultList;
}
