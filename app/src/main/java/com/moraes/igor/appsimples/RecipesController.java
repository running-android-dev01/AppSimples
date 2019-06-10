package com.moraes.igor.appsimples;

import android.util.Log;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.moraes.igor.appsimples.Json.AccountsBalances;
import com.moraes.igor.appsimples.Json.BillsInstallment;
import com.moraes.igor.appsimples.Json.CostCenters;
import com.moraes.igor.appsimples.Json.Enterprises;
import com.moraes.igor.appsimples.Json.EnterprisesResult;
import com.moraes.igor.appsimples.Json.ReceivableBillsInstallment;
import com.moraes.igor.appsimples.Json.SalesContracts;
import com.moraes.igor.appsimples.Json.Units;

/*import org.springframework.http.ContentCodingType;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsAndroidClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Authenticator;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.PasswordAuthentication;
import java.net.URL;

class RecipesController {
    RecipesController() {
    }

    private String getJson(String link){
        String retorno;
        URL url;
        StringBuilder response = new StringBuilder();
        try {
            url = new URL(link);
        } catch (MalformedURLException e) {
            throw new IllegalArgumentException("invalid url");
        }

        HttpURLConnection conn = null;
        try {
            Authenticator.setDefault(new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication("construsoft-api", "sTgZn4YrKLfk".toCharArray());
                }
            });

            conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(false);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            conn.setRequestMethod("GET");
            //System.setProperty("http.agent", "Chrome");

            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");



            // handle the response
            int status = conn.getResponseCode();
            if (status != 200) {
                throw new IOException("Post failed with error code " + status);
            } else {
                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                conn.disconnect();
            }

            retorno = response.toString();
        }
        return retorno;
    }

    CostCenters getCostCenters() {
        CostCenters costCenters = null;
        try {
            String json = getJson("https://api.sienge.com.br/construsoft/public/api/v1/cost-centers");

            ObjectMapper mapper = new ObjectMapper();
            costCenters = mapper.readValue(json, CostCenters.class);
        } catch (Exception e) {
            Log.e(this.getClass().getName(), "ERRO = ", e);
        }
        return costCenters;
    }


    SalesContracts getSalesContracts() {
        SalesContracts salesContracts = null;
        try {
            String json = getJson("https://api.sienge.com.br/construsoft/public/api/v1/sales-contracts");

            ObjectMapper mapper = new ObjectMapper();
            salesContracts = mapper.readValue(json, SalesContracts.class);
        } catch (Exception e) {
            Log.e(this.getClass().getName(), "ERRO = ", e);
        }
        return salesContracts;
    }


    Units getUnits() {
        Units units = null;
        try {
            String json = getJson("https://api.sienge.com.br/construsoft/public/api/v1/units");

            ObjectMapper mapper = new ObjectMapper();
            units = mapper.readValue(json, Units.class);
        } catch (Exception e) {
            Log.e(this.getClass().getName(), "ERRO = ", e);
        }
        return units;
    }

    ReceivableBillsInstallment getReceivableBillsInstallment(int customerId ) {
        ReceivableBillsInstallment receivableBillsInstallment = null;
        try {
            String json = getJson(String.format("https://api.sienge.com.br/construsoft/public/api/v1/accounts-receivable/receivable-bills/%s/installments", Integer.toString(customerId)));

            ObjectMapper mapper = new ObjectMapper();
            receivableBillsInstallment = mapper.readValue(json, ReceivableBillsInstallment.class);
        } catch (Exception e) {
            Log.e(this.getClass().getName(), "ERRO = ", e);
        }
        return receivableBillsInstallment;
    }


    BillsInstallment getBillsInstallment(int customerId ) {
        BillsInstallment billsInstallment = null;
        try {
            String json = getJson(String.format("https://api.sienge.com.br/construsoft/public/api/v1/bills/%s/installments", Integer.toString(customerId)));

            ObjectMapper mapper = new ObjectMapper();
            billsInstallment = mapper.readValue(json, BillsInstallment.class);
        } catch (Exception e) {
            Log.e(this.getClass().getName(), "ERRO = ", e);
        }
        return billsInstallment;
    }

    AccountsBalances getAccountsBalances() {
        AccountsBalances accountsBalances = null;
        try {
            String json = getJson("https://api.sienge.com.br/construsoft/public/api/v1/accounts-balances?balanceDate=2018-01-01");

            ObjectMapper mapper = new ObjectMapper();
            accountsBalances = mapper.readValue(json, AccountsBalances.class);
        } catch (Exception e) {
            Log.e(this.getClass().getName(), "ERRO = ", e);
        }
        return accountsBalances;
    }


    Enterprises getEnterprises() {
        Enterprises enterprises = null;
        try {
            String json = getJson("https://api.sienge.com.br/construsoft/public/api/v1/enterprises");

            ObjectMapper mapper = new ObjectMapper();
            enterprises = mapper.readValue(json, Enterprises.class);
        } catch (Exception e) {
            Log.e(this.getClass().getName(), "ERRO = ", e);
        }
        return enterprises;
    }


    EnterprisesResult getEnterprises(int id) {
        EnterprisesResult enterprisesResult = null;
        try {
            String json = getJson("https://api.sienge.com.br/construsoft/public/api/v1/enterprises/" + id);

            ObjectMapper mapper = new ObjectMapper();
            enterprisesResult = mapper.readValue(json, EnterprisesResult.class);
        } catch (Exception e) {
            Log.e(this.getClass().getName(), "ERRO = ", e);
        }
        return enterprisesResult;
    }

}
