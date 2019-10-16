package com.moraes.igor.appsimples;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.moraes.igor.appsimples.Json.AccountsBalances;
import com.moraes.igor.appsimples.Json.AccountsBalancesResult;
import com.moraes.igor.appsimples.Json.BillsInstallment;
import com.moraes.igor.appsimples.Json.BillsInstallmentResult;
import com.moraes.igor.appsimples.Json.Enterprises;
import com.moraes.igor.appsimples.Json.EnterprisesResult;
import com.moraes.igor.appsimples.Json.ReceivableBillsInstallment;
import com.moraes.igor.appsimples.Json.ReceivableBillsInstallmentResult;
import com.moraes.igor.appsimples.Json.SalesContractUnits;
import com.moraes.igor.appsimples.Json.SalesContracts;
import com.moraes.igor.appsimples.Json.SalesContractsResult;
import com.moraes.igor.appsimples.Json.Units;
import com.moraes.igor.appsimples.Json.UnitsResult;
import com.moraes.igor.appsimples.model.Empreendimento;
import com.moraes.igor.appsimples.model.SaldoConta;
import com.moraes.igor.appsimples.model.Unidades;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.logging.Handler;


public class SeeEngeService extends IntentService {
    public SeeEngeService() {
        super("SeeEngeService");
    }


    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            FirebaseFirestore db = FirebaseFirestore.getInstance();

            //List<EnterprisesResult> lEnterprisesResult = new ArrayList<>();

            RecipesController recipesController = new RecipesController();
            Enterprises enterprises = recipesController.getEnterprises();
            for (EnterprisesResult result: enterprises.enterprisesResults) {
                final EnterprisesResult resultFinal = recipesController.getEnterprises(result.id);
                if (resultFinal.salesDetails!=null && resultFinal.salesDetails.generalSalesValue!=null &&  Double.parseDouble(resultFinal.salesDetails.generalSalesValue)>0.0){
                    CollectionReference enterprisesRef = db.collection("Enterprises");
                    Query query = enterprisesRef.whereEqualTo("id", resultFinal.id);
                    query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            Log.d("SeeEngeService", "onComplete");
                            if (task.isSuccessful()) {
                                QuerySnapshot querySnapshot = task.getResult();
                                if (querySnapshot.size()==0){
                                    db.collection("Enterprises")
                                            .add(resultFinal)
                                            .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                                @Override
                                                public void onSuccess(DocumentReference documentReference) {
                                                    Log.d("SeeEngeService", "DocumentSnapshot added with ID: " + documentReference.getId());
                                                }
                                            })
                                            .addOnFailureListener(new OnFailureListener() {
                                                @Override
                                                public void onFailure(@NonNull Exception e) {
                                                    Log.w("SeeEngeService", "Error adding document", e);
                                                }
                                            });
                                }
                            } else {
                                Log.e("SeeEngeService", "Error getting documents."+ task.getException());
                            }

                        }
                    });


                    List<ReceivableBillsInstallmentResult> receivableBillsInstallmentResults = new ArrayList<>();
                    List<BillsInstallmentResult> billsInstallmentResults = new ArrayList<>();


                    SalesContracts salesContracts = recipesController.getSalesContracts();
                    Units units = recipesController.getUnits();
                    AccountsBalances accountsBalances = recipesController.getAccountsBalances();

                    if (salesContracts!=null){
                        for (SalesContractsResult s: salesContracts.costCentersResults) {
                            if (s.enterpriseId==resultFinal.id){
                                ReceivableBillsInstallment receivableBillsInstallment = recipesController.getReceivableBillsInstallment(s.receivableBillId);
                                if (receivableBillsInstallment!=null){
                                    receivableBillsInstallmentResults.addAll(receivableBillsInstallment.costCentersResults);
                                }
                                BillsInstallment billsInstallment = recipesController.getBillsInstallment(s.receivableBillId);
                                if (billsInstallment!=null){
                                    billsInstallmentResults.addAll(billsInstallment.billsResultList);
                                }
                            }
                        }
                    }


                    CollectionReference empreendimentoRef = db.collection("Empreendimento");
                    Query queryEmpreendimento = empreendimentoRef.whereEqualTo("id", resultFinal.id);
                    queryEmpreendimento.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            Log.d("SeeEngeService", "onComplete");
                            if (task.isSuccessful()) {
                                QuerySnapshot querySnapshot = task.getResult();
                                if (querySnapshot.size()==0) {
                                    Locale current = getApplicationContext().getResources().getConfiguration().locale;
                                    SimpleDateFormat df1 = new SimpleDateFormat("dd/MM/yyyy", current);
                                    SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd", current);
                                    Calendar hoje = Calendar.getInstance();
                                    hoje.set(Calendar.HOUR, 0);
                                    hoje.set(Calendar.MINUTE, 0);
                                    hoje.set(Calendar.SECOND, 0);

                                    Calendar cInicioObras = Calendar.getInstance();
                                    Calendar cFimObras = Calendar.getInstance();
                                    Calendar cEntregaChaves = Calendar.getInstance();

                                    try{
                                        if (resultFinal.constructionDetails!=null && !TextUtils.isEmpty(resultFinal.constructionDetails.startDate)){
                                            Date convertedDate = df2.parse(resultFinal.constructionDetails.startDate);
                                            cInicioObras.setTime(convertedDate);
                                        }


                                        if (resultFinal.constructionDetails!=null && !TextUtils.isEmpty(resultFinal.constructionDetails.endDate)){
                                            Date convertedDate = df2.parse(resultFinal.constructionDetails.endDate);
                                            cFimObras.setTime(convertedDate);
                                        }


                                        if (resultFinal.salesDetails!=null && !TextUtils.isEmpty(resultFinal.salesDetails.keysDelivery)){
                                            Date convertedDate = df2.parse(resultFinal.salesDetails.keysDelivery);
                                            cEntregaChaves.setTime(convertedDate);
                                        }

                                    } catch (ParseException e) {
                                        Log.e("SeeEngeService", e.getMessage(), e);
                                    }

                                    //cEntregaChaves.set(Calendar.YEAR, 2019);
                                    //cEntregaChaves.set(Calendar.MONTH, 5);
                                    //cEntregaChaves.set(Calendar.DAY_OF_MONTH, 30);


                                    Empreendimento empreendimento = new Empreendimento();
                                    empreendimento.id = resultFinal.id;
                                    empreendimento.empreendimento = resultFinal.name;
                                    empreendimento.endereco = resultFinal.adress;

                                    if (resultFinal.constructionDetails!=null){
                                        empreendimento.valorMProjetado = 0.0;
                                        empreendimento.pavimentos = Integer.parseInt(resultFinal.constructionDetails.numberOfFloors);
                                        empreendimento.unidades = 0;
                                        empreendimento.areaTotalTerreno = Double.parseDouble(resultFinal.constructionDetails.totalArea);
                                        empreendimento.areaTerreno = Double.parseDouble(resultFinal.constructionDetails.landSArea);
                                    }

                                    empreendimento.inicioObras = df1.format(cInicioObras.getTime());
                                    empreendimento.fimDasObras = df1.format(cFimObras.getTime());
                                    empreendimento.entragaDasChaves = df1.format(cEntregaChaves.getTime());


                                    empreendimento.duracaoAteMomento = TimeUnit.MILLISECONDS.toDays(hoje.getTimeInMillis() - cInicioObras.getTimeInMillis());
                                    empreendimento.faltaParaTerminar = TimeUnit.MILLISECONDS.toDays(cFimObras.getTimeInMillis() - hoje.getTimeInMillis());

                                    empreendimento.vendidas = 0;
                                    empreendimento.disponiveis = 0;
                                    empreendimento.indisponivel = 0;

                                    //empreendimento.vgv = 4800000.00;
                                    empreendimento.vgv = 0;
                                    if (resultFinal.salesDetails!=null && !TextUtils.isEmpty(resultFinal.salesDetails.generalSalesValue)){
                                        empreendimento.vgv = Double.parseDouble(resultFinal.salesDetails.generalSalesValue);
                                    }

                                    empreendimento.percentVgv = 0.0;
                                    empreendimento.vendido = 0.0;
                                    empreendimento.aVender = 0.0;
                                    empreendimento.diferencaVgv = 0.0;
                                    empreendimento.recebido = 0.0;
                                    empreendimento.aReceber = 0.0;
                                    empreendimento.recFinanciadoOutros = 0.0;

                                    empreendimento.valorAReceber = 0.0;
                                    empreendimento.valorAReceber0A30 = 0.0;
                                    empreendimento.valorAReceber31A60 = 0.0;
                                    empreendimento.valorAReceber61A120 = 0.0;
                                    empreendimento.valorAReceber121 = 0.0;

                                    empreendimento.valorAPagar = 0.0;
                                    empreendimento.valorAPagar0A30 = 0.0;
                                    empreendimento.valorAPagar31A60 = 0.0;
                                    empreendimento.valorAPagar61A120 = 0.0;
                                    empreendimento.valorAPagar121 = 0.0;

                                    empreendimento.valorFluxoProjetado = 0.0;
                                    empreendimento.valorFluxoProjetado0A30 = 0.0;
                                    empreendimento.valorFluxoProjetado31A60 = 0.0;
                                    empreendimento.valorFluxoProjetado61A120 = 0.0;
                                    empreendimento.valorFluxoProjetado121 = 0.0;

                                    empreendimento.lUnidades = new ArrayList<>();

                                    for (BillsInstallmentResult billsInstallmentResult: billsInstallmentResults) {
                                        empreendimento.valorAPagar += billsInstallmentResult.amount;
                                        try {
                                            Date convertedDate = df2.parse(billsInstallmentResult.dueDate);
                                            Calendar cal = Calendar.getInstance();
                                            cal.setTime(convertedDate);

                                            long tempo = TimeUnit.MILLISECONDS.toDays(cal.getTimeInMillis() - hoje.getTimeInMillis());
                                            if (tempo>120){
                                                empreendimento.valorAPagar121 += billsInstallmentResult.amount;
                                            }else if (tempo>60){
                                                empreendimento.valorAPagar61A120 += billsInstallmentResult.amount;
                                            }else if (tempo>30){
                                                empreendimento.valorAPagar31A60 += billsInstallmentResult.amount;
                                            }else {
                                                empreendimento.valorAPagar0A30 += billsInstallmentResult.amount;
                                            }
                                        } catch (ParseException e) {
                                            Log.e("SeeEngeService", e.getMessage(), e);
                                        }
                                    }


                                    //double vgvDiferenca = 0.0;


                                    Calendar ultimaParcela = null;
                                    empreendimento.aVender = 0;
                                    if (units!=null){
                                        for (UnitsResult unitsResult: units.unitsResults) {
                                            if (unitsResult.enterpriseId==resultFinal.id){
                                                Unidades unidades = new Unidades();
                                                unidades.unidade = unitsResult.name;
                                                unidades.qtdAtraso = 0;
                                                unidades.atraso = 0;
                                                unidades.ultimaRecebido = "";
                                                unidades.diferencaVgv = 0.0;

                                                switch (unitsResult.commercialStock) {
                                                    case "V":
                                                        empreendimento.vendidas += 1;
                                                        break;
                                                    case "D":
                                                        empreendimento.disponiveis += 1;
                                                        break;
                                                    default:
                                                        empreendimento.indisponivel += 1;
                                                        break;
                                                }



                                                for (SalesContractsResult salesContractsResult: Objects.requireNonNull(salesContracts).costCentersResults) {
                                                    if (salesContractsResult.enterpriseId==resultFinal.id){
                                                        for (SalesContractUnits salesContractUnits: salesContractsResult.salesContractUnits) {
                                                            if (unitsResult.name.equals(salesContractUnits.name)){
                                                                unidades.contrato = salesContractsResult.totalSellingValue;
                                                                unidades.aReceber = 0.0;
                                                                for (ReceivableBillsInstallmentResult receivableBillsInstallmentResult: receivableBillsInstallmentResults) {
                                                                    if (salesContractsResult.receivableBillId==receivableBillsInstallmentResult.receivableBillId){
                                                                        unidades.aReceber += receivableBillsInstallmentResult.balanceDue;
                                                                        empreendimento.valorAReceber += receivableBillsInstallmentResult.balanceDue;
                                                                        try {
                                                                            Date convertedDate = df2.parse(receivableBillsInstallmentResult.dueDate);
                                                                            Calendar cal = Calendar.getInstance();
                                                                            cal.setTime(convertedDate);

                                                                            if (ultimaParcela==null && receivableBillsInstallmentResult.balanceDue==0){
                                                                                ultimaParcela = Calendar.getInstance();
                                                                                ultimaParcela.setTime(convertedDate);

                                                                                unidades.ultimaRecebido = df1.format(ultimaParcela.getTime());
                                                                            }else if (ultimaParcela!=null && ultimaParcela.compareTo(cal)<=0 &&  receivableBillsInstallmentResult.balanceDue==0 ){
                                                                                ultimaParcela = Calendar.getInstance();
                                                                                ultimaParcela.setTime(convertedDate);

                                                                                unidades.ultimaRecebido = df1.format(ultimaParcela.getTime());
                                                                            }

                                                                            long tempo = TimeUnit.MILLISECONDS.toDays(cal.getTimeInMillis() - hoje.getTimeInMillis());
                                                                            if (tempo>120){
                                                                                empreendimento.valorAReceber121 += receivableBillsInstallmentResult.balanceDue;
                                                                            }else if (tempo>60){
                                                                                empreendimento.valorAReceber61A120 += receivableBillsInstallmentResult.balanceDue;
                                                                            }else if (tempo>30){
                                                                                empreendimento.valorAReceber31A60 += receivableBillsInstallmentResult.balanceDue;
                                                                            }else {
                                                                                empreendimento.valorAReceber0A30 += receivableBillsInstallmentResult.balanceDue;
                                                                            }



                                                                            if (tempo<0 && receivableBillsInstallmentResult.balanceDue>0){
                                                                                unidades.qtdAtraso += 1;
                                                                                unidades.atraso += receivableBillsInstallmentResult.balanceDue;
                                                                            }
                                                                        } catch (ParseException e) {
                                                                            // TODO Auto-generated catch block
                                                                        }
                                                                    }
                                                                }

                                                                unidades.recebido = unidades.contrato-unidades.aReceber;

                                                                empreendimento.recebido += unidades.recebido;
                                                                empreendimento.aReceber += unidades.aReceber;

                                                                //empreendimento.vendido += unidades.contrato;
                                                                empreendimento.recFinanciadoOutros += (unidades.recebido + unidades.aReceber);
                                                            }
                                                        }
                                                    }
                                                }

                                                unidades.percentualRecebido = 0.0;
                                                if (unidades.contrato>0){
                                                    unidades.percentualRecebido = (unidades.recebido/unidades.contrato)*100;
                                                }

                                                unidades.codSituacao = unitsResult.commercialStock;
                                                switch (unitsResult.commercialStock) {
                                                    case "D":
                                                        unidades.situacao = "Disponível";
                                                        break;
                                                    case "V":
                                                        unidades.situacao = "Vendida";
                                                        break;
                                                    case "L":
                                                        unidades.situacao = "Indisponível";
                                                        break;
                                                    case "C":
                                                        unidades.situacao = "Indisponível";
                                                        break;
                                                    case "R":
                                                        unidades.situacao = "Indisponível";
                                                        break;
                                                    case "E":
                                                        unidades.situacao = "Indisponível";
                                                        break;
                                                    case "M":
                                                        unidades.situacao = "Indisponível";
                                                        break;
                                                    case "P":
                                                        unidades.situacao = "Indisponível";
                                                        break;
                                                    case "T":
                                                        unidades.situacao = "Indisponível";
                                                        break;
                                                    case "G":
                                                        unidades.situacao = "Indisponível";
                                                        break;
                                                }

                                                empreendimento.percentVgv += unitsResult.generalSaleValueFraction;
                                                unidades.valorVgv = empreendimento.vgv*unitsResult.generalSaleValueFraction;
                                                unidades.area = (unitsResult.privateArea+unitsResult.commonArea);
                                                if (unidades.contrato==0){
                                                    empreendimento.aVender += unidades.valorVgv;
                                                }else{
                                                    //unidades.diferencaVgv = (unidades.contrato-unidades.valorVgv);
                                                    unidades.diferencaVgv = 0;
                                                }
                                                if (unitsResult.commercialStock.equals("V")){
                                                    empreendimento.vendido += unidades.valorVgv;
                                                }


                                                empreendimento.diferencaVgv += unidades.diferencaVgv;
                                                empreendimento.lUnidades.add(unidades);
                                            }
                                        }
                                    }


                                    empreendimento.recFinanciadoOutros = empreendimento.aReceber-empreendimento.recebido;
                                    empreendimento.valorSaldo = 0.0;
                                    empreendimento.lSaldoConta = new ArrayList<>();
                                    if (accountsBalances!=null){
                                        for (AccountsBalancesResult accountsBalancesResult: accountsBalances.accountsBalancesResultList) {
                                            SaldoConta saldoConta = new SaldoConta();
                                            saldoConta.conta = accountsBalancesResult.accountNumber;
                                            saldoConta.saldo = accountsBalancesResult.amount;


                                            if (accountsBalancesResult.accountNumber.equals("123456-7") || accountsBalancesResult.accountNumber.equals("CAIXA")){
                                                empreendimento.valorSaldo += accountsBalancesResult.amount;

                                                empreendimento.lSaldoConta.add(saldoConta);
                                            }
                                        }
                                    }
                                    Collections.sort(empreendimento.lSaldoConta, (s1, s2) -> s1.conta.compareTo(s2.conta));
                                    Collections.sort(empreendimento.lUnidades, (o1, o2) -> o1.unidade.compareTo(o2.unidade));
                                    if (empreendimento.areaTotalTerreno>0.0){
                                        empreendimento.valorMProjetado = empreendimento.vgv/empreendimento.areaTotalTerreno;
                                    }
                                    empreendimento.unidades = empreendimento.lUnidades.size();


                                    empreendimento.valorFluxoProjetado = empreendimento.valorAReceber-empreendimento.valorAPagar;
                                    empreendimento.valorFluxoProjetado0A30 = empreendimento.valorAReceber0A30-empreendimento.valorAPagar0A30;
                                    empreendimento.valorFluxoProjetado31A60 = empreendimento.valorAReceber31A60-empreendimento.valorAPagar31A60;
                                    empreendimento.valorFluxoProjetado61A120 = empreendimento.valorAReceber61A120-empreendimento.valorAPagar61A120;
                                    empreendimento.valorFluxoProjetado121 = empreendimento.valorAReceber121-empreendimento.valorAPagar121;


                                    db.collection("Empreendimento")
                                            .add(empreendimento)
                                            .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                                @Override
                                                public void onSuccess(DocumentReference documentReference) {
                                                    Log.d("SeeEngeService", "DocumentSnapshot added with ID: " + documentReference.getId());
                                                }
                                            })
                                            .addOnFailureListener(new OnFailureListener() {
                                                @Override
                                                public void onFailure(@NonNull Exception e) {
                                                    Log.w("SeeEngeService", "Error adding document", e);
                                                }
                                            });

                                }
                            } else {
                                Log.e("SeeEngeService", "Error getting documents."+ task.getException());
                            }
                        }
                    });
                    //lEnterprisesResult.add(result);
                }
            }




            //return lEnterprisesResult;


        }
    }

}
