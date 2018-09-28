package com.moraes.igor.appsimples.Json;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ReceivableBills {
    @JsonProperty("receivableBillId")
    public int receivableBillId;

    @JsonProperty("documentId")
    public String documentId;

    @JsonProperty("documentNumber")
    public String documentNumber;

    @JsonProperty("issueDate")
    public String issueDate;

    @JsonProperty("receivableBillValue")
    public long receivableBillValue;

    @JsonProperty("companyId")
    public int companyId;
}
