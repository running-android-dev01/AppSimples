package com.moraes.igor.appsimples;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.moraes.igor.appsimples.Json.AccountsBalances;
import com.moraes.igor.appsimples.Json.AccountsBalancesResult;
import com.moraes.igor.appsimples.Json.BillsInstallment;
import com.moraes.igor.appsimples.Json.BillsInstallmentResult;
import com.moraes.igor.appsimples.Json.CostCenters;
import com.moraes.igor.appsimples.Json.CostCentersResult;
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

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;

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

public class DetalhesActivity extends AppCompatActivity
        implements DetalhesEmpreendimentoFragment.OnFragmentInteractionListener
        , DetalhesClienteFragment.OnFragmentInteractionListener
        , DetalhesGraficoFragment.OnFragmentInteractionListener
        , DetalhesUnidadesFragment.OnFragmentInteractionListener {

    private final static String TAG = DetalhesActivity.class.getName();
    public final static String EMPREENDIMENTO = TAG + ".empreendimento" ;

    private CostCentersResult costCentersResult;


    public Empreendimento empreendimento;

    private LinearLayout lnlDetalhes;
    private final BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = item -> {
        switch (item.getItemId()) {
            case R.id.navigation_empreendimento:
                onFrag(DetalhesEmpreendimentoFragment.newInstance());
                return true;
            case R.id.navigation_unidades:
                onFrag(DetalhesUnidadesFragment.newInstance());
                return true;
            case R.id.navigation_cliente:
                onFrag(DetalhesClienteFragment.newInstance());
                return true;
            case R.id.navigation_grafico:
                onFrag(DetalhesGraficoFragment.newInstance());
                return true;
        }
        return false;
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes);

        setUpToolbar();

        AppCompatTextView txtEmpreendimento = findViewById(R.id.txtEmpreendimento);
        AppCompatTextView txtEndereco = findViewById(R.id.txtEndereco);

        lnlDetalhes = findViewById(R.id.lnlDetalhes);
        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        costCentersResult = (CostCentersResult) getIntent().getSerializableExtra(EMPREENDIMENTO);

        txtEmpreendimento.setText(costCentersResult.name);
        txtEndereco.setText(costCentersResult.endereco);

        new carregarDados().execute();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.setting, menu);
        return true;
    }


    private void setUpToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(getString(R.string.app_name));

        setSupportActionBar(toolbar);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id == android.R.id.home){
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    private void onFrag(Fragment frag){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(lnlDetalhes.getId(), frag);
        fragmentTransaction.commit();
    }


    @Override
    public Empreendimento getEmpreendimento() {
        return empreendimento;
    }

    class carregarDados extends AsyncTask<Void, String, Object> {
        private ProgressDialog progresso;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progresso = ProgressDialog.show(DetalhesActivity.this, "", "Carregando dados");
        }

        @SuppressWarnings("unchecked")
        @Override
        protected CostCenters doInBackground(Void... params) {

            List<ReceivableBillsInstallmentResult> receivableBillsInstallmentResults = new ArrayList<>();
            List<BillsInstallmentResult> billsInstallmentResults = new ArrayList<>();

            RecipesController recipesController = new RecipesController();
            SalesContracts salesContracts = recipesController.getSalesContracts();
            Units units = recipesController.getUnits();
            AccountsBalances accountsBalances = recipesController.getAccountsBalances();

            if (salesContracts!=null){
                for (SalesContractsResult s: salesContracts.costCentersResults) {
                    if (s.enterpriseId==costCentersResult.id){
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


            //API TITULOS DO CONTAS A PAGAR
            //API CONTAS-CORRENTES
            //API SALDO DAS CONTAS


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


            cInicioObras.set(Calendar.YEAR, 2019);
            cInicioObras.set(Calendar.MONTH, 0);
            cInicioObras.set(Calendar.DAY_OF_MONTH, 1);

            cFimObras.set(Calendar.YEAR, 2019);
            cFimObras.set(Calendar.MONTH, 6);
            cFimObras.set(Calendar.DAY_OF_MONTH, 1);

            cEntregaChaves.set(Calendar.YEAR, 2019);
            cEntregaChaves.set(Calendar.MONTH, 5);
            cEntregaChaves.set(Calendar.DAY_OF_MONTH, 30);

            if (costCentersResult.id==12){
                cInicioObras.set(Calendar.YEAR, 2018);
                cInicioObras.set(Calendar.MONTH, 0);
                cInicioObras.set(Calendar.DAY_OF_MONTH, 1);

                cFimObras.set(Calendar.YEAR, 2019);
                cFimObras.set(Calendar.MONTH, 6);
                cFimObras.set(Calendar.DAY_OF_MONTH, 1);

                cEntregaChaves.set(Calendar.YEAR, 2019);
                cEntregaChaves.set(Calendar.MONTH, 6);
                cEntregaChaves.set(Calendar.DAY_OF_MONTH, 15);
            }else if (costCentersResult.id==22){
                cInicioObras.set(Calendar.YEAR, 2018);
                cInicioObras.set(Calendar.MONTH, 2);
                cInicioObras.set(Calendar.DAY_OF_MONTH, 1);

                cFimObras.set(Calendar.YEAR, 2020);
                cFimObras.set(Calendar.MONTH, 5);
                cFimObras.set(Calendar.DAY_OF_MONTH, 1);

                cEntregaChaves.set(Calendar.YEAR, 2020);
                cEntregaChaves.set(Calendar.MONTH, 0);
                cEntregaChaves.set(Calendar.DAY_OF_MONTH, 1);
            }


            empreendimento = new Empreendimento();
            empreendimento.empreendimento = costCentersResult.name;
            empreendimento.endereco = costCentersResult.endereco;

            empreendimento.valorMProjetado = 0.0;
            empreendimento.pavimentos = 0;
            empreendimento.unidades = 0;
            empreendimento.areaTotalTerreno = 0;
            empreendimento.areaTerreno = 0;

            empreendimento.inicioObras = df1.format(cInicioObras.getTime());
            empreendimento.fimDasObras = df1.format(cFimObras.getTime());
            empreendimento.entragaDasChaves = df1.format(cEntregaChaves.getTime());


            empreendimento.duracaoAteMomento = TimeUnit.MILLISECONDS.toDays(cFimObras.getTimeInMillis() - cInicioObras.getTimeInMillis());
            empreendimento.faltaParaTerminar = TimeUnit.MILLISECONDS.toDays(hoje.getTimeInMillis() - cInicioObras.getTimeInMillis());

            empreendimento.vendidas = 0;
            empreendimento.disponiveis = 0;
            empreendimento.indisponivel = 0;

            empreendimento.vgv = 4800000.00;
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


            if (costCentersResult.id==12){
                empreendimento.areaTotalTerreno = 1750;
                empreendimento.areaTerreno = 1750;
                empreendimento.vgv = 9581000.00;
                empreendimento.pavimentos = 4;
            }else if (costCentersResult.id==22){
                empreendimento.areaTotalTerreno = 3150;
                empreendimento.areaTerreno = 800;
                empreendimento.vgv = 14284016.91;
                empreendimento.pavimentos = 6;
            }

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
                    Log.e(TAG, e.getMessage(), e);
                }
            }


            double vgvDiferenca = 0.0;


            Calendar ultimaParcela = null;
            for (UnitsResult unitsResult: units.unitsResults) {
                if (unitsResult.enterpriseId==costCentersResult.id){
                    Unidades unidades = new Unidades();
                    unidades.unidade = unitsResult.name;
                    unidades.qtdAtraso = 0;
                    unidades.atraso = 0;
                    unidades.ultimaRecebido = "";

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
                        for (SalesContractUnits salesContractUnits: salesContractsResult.salesContractUnits) {
                            if (unitsResult.name.equals(salesContractUnits.name)){
                                unidades.contrato = salesContractsResult.totalSellingValue;
                                for (ReceivableBillsInstallmentResult receivableBillsInstallmentResult: receivableBillsInstallmentResults) {
                                    if (salesContractsResult.receivableBillId==receivableBillsInstallmentResult.receivableBillId){
                                        unidades.aReceber = receivableBillsInstallmentResult.balanceDue;
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

                                empreendimento.vendido += (unidades.recebido + unidades.aReceber);
                                empreendimento.recFinanciadoOutros += (unidades.recebido + unidades.aReceber);
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
                            unidades.situacao = "Locada";
                            break;
                        case "C":
                            //unidades.situacao = "Reservada";
                            unidades.situacao = "Indisponível";
                            break;
                        case "R":
                            //unidades.situacao = "Reserva Técnica";
                            unidades.situacao = "Indisponível";
                            break;
                        case "E":
                            //unidades.situacao = "Permuta";
                            unidades.situacao = "Indisponível";
                            break;
                        case "M":
                            //unidades.situacao = "Mutuo";
                            unidades.situacao = "Indisponível";
                            break;
                        case "P":
                            //unidades.situacao = "Proposta";
                            unidades.situacao = "Indisponível";
                            break;
                        case "T":
                            //unidades.situacao = "Transferido";
                            unidades.situacao = "Indisponível";
                            break;
                        case "G":
                            //unidades.situacao = "Vendido/Terceiros";
                            unidades.situacao = "Indisponível";
                            break;
                    }

                    //empreendimento.vgv += unitsResult.generalSaleValueFraction;
                    unidades.valorVgv = empreendimento.vgv*unitsResult.generalSaleValueFraction;
                    vgvDiferenca += unidades.valorVgv;
                    unidades.area = (unitsResult.privateArea+unitsResult.commonArea);
                    //empreendimento.areaTotalTerreno += unidades.area;

                    empreendimento.lUnidades.add(unidades);
                }
            }

            empreendimento.diferencaVgv = empreendimento.vgv-vgvDiferenca;
            empreendimento.valorSaldo = 0.0;
            empreendimento.lSaldoConta = new ArrayList<>();
            if (accountsBalances!=null){
                for (AccountsBalancesResult accountsBalancesResult: accountsBalances.accountsBalancesResultList) {
                    SaldoConta saldoConta = new SaldoConta();
                    saldoConta.conta = accountsBalancesResult.accountNumber;
                    saldoConta.saldo = accountsBalancesResult.amount;

                    empreendimento.valorSaldo += accountsBalancesResult.amount;

                    empreendimento.lSaldoConta.add(saldoConta);
                }
            }
            Collections.sort(empreendimento.lSaldoConta, (s1, s2) -> s1.conta.compareTo(s2.conta));
            Collections.sort(empreendimento.lUnidades, (o1, o2) -> o1.unidade.compareTo(o2.unidade));
            if (empreendimento.areaTotalTerreno>0.0){
                empreendimento.valorMProjetado = empreendimento.vgv/empreendimento.areaTotalTerreno;
            }
            empreendimento.aVender = empreendimento.vgv - empreendimento.vendido;
            empreendimento.unidades = empreendimento.lUnidades.size();


            empreendimento.valorFluxoProjetado = empreendimento.valorAReceber-empreendimento.valorAPagar;
            empreendimento.valorFluxoProjetado0A30 = empreendimento.valorAReceber0A30-empreendimento.valorAPagar0A30;
            empreendimento.valorFluxoProjetado31A60 = empreendimento.valorAReceber31A60-empreendimento.valorAPagar31A60;
            empreendimento.valorFluxoProjetado61A120 = empreendimento.valorAReceber61A120-empreendimento.valorAPagar61A120;
            empreendimento.valorFluxoProjetado121 = empreendimento.valorAReceber121-empreendimento.valorAPagar121;

            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            if (progresso != null) {
                progresso.dismiss();
            }
            onFrag(DetalhesEmpreendimentoFragment.newInstance());
        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
            if (progresso != null) {
                progresso.setMessage(values[0]);
            }
        }
    }
}
