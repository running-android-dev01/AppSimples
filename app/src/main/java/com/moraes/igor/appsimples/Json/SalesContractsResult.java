package com.moraes.igor.appsimples.Json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SalesContractsResult {
    @JsonProperty("id")
    public int id;

    @JsonProperty("enterpriseId")
    public int enterpriseId;

    @JsonProperty("receivableBillId")
    public int receivableBillId;

    @JsonProperty("number")
    public String number;

    @JsonProperty("correctionType")
    public String correctionType;

    @JsonProperty("situation")
    public String situation;

    @JsonProperty("discountType")
    public String discountType;

    @JsonProperty("cancellationReason")
    public String cancellationReason;

    @JsonProperty("discountPercentage")
    public String discountPercentage;

    @JsonProperty("value")
    public long value;

    @JsonProperty("totalSellingValue")
    public long totalSellingValue;

    @JsonProperty("contractDate")
    public String contractDate;

    @JsonProperty("issueDate")
    public String issueDate;

    @JsonProperty("cancellationDate")
    public String cancellationDate;

    @JsonProperty("loyaltyCustomer")
    public boolean loyaltyCustomer;

    @JsonProperty("salesContractCustomers")
    public List<SalesContractCustomers> salesContractCustomers;

    @JsonProperty("salesContractUnits")
    public List<SalesContractUnits>  salesContractUnits;

    @JsonProperty("paymentConditions")
    public List<PaymentConditions> paymentConditions;

    @JsonProperty("links")
    public List<Links> links;
}
