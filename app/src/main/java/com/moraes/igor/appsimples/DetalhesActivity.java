package com.moraes.igor.appsimples;

import android.app.ProgressDialog;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.moraes.igor.appsimples.Json.CostCenters;
import com.moraes.igor.appsimples.Json.CostCentersResult;
import com.moraes.igor.appsimples.Json.ReceivableBills;
import com.moraes.igor.appsimples.Json.ReceivableBillsInstallment;
import com.moraes.igor.appsimples.Json.ReceivableBillsInstallmentResult;
import com.moraes.igor.appsimples.Json.SalesContracts;
import com.moraes.igor.appsimples.Json.SalesContractsResult;
import com.moraes.igor.appsimples.Json.Units;
import com.moraes.igor.appsimples.Json.UnitsResult;
import com.moraes.igor.appsimples.mode.Empreendimento;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.MenuItem;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class DetalhesActivity extends AppCompatActivity
        implements DetalhesEmpreendimentoFragment.OnFragmentInteractionListener
        , DetalhesClienteFragment.OnFragmentInteractionListener
        , DetalhesGraficoFragment.OnFragmentInteractionListener  {

    private final static String TAG = DetalhesActivity.class.getName();
    public final static String EMPREENDIMENTO = TAG + ".empreendimento" ;

    public CostCentersResult costCentersResult;
    public SalesContractsResult salesContractsResult;
    public List<UnitsResult> unitsResults;
    public ReceivableBills receivableBills;
    public List<ReceivableBillsInstallmentResult> receivableBillsInstallmentResults;

    private Empreendimento empreendimento;

    private LinearLayout lnlDetalhes;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_empreendimento:
                    onFrag(DetalhesEmpreendimentoFragment.newInstance());
                    return true;
                case R.id.navigation_cliente:
                    onFrag(DetalhesClienteFragment.newInstance());
                    return true;
                case R.id.navigation_grafico:
                    onFrag(DetalhesGraficoFragment.newInstance());
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes);

        setUpToolbar();

        lnlDetalhes = findViewById(R.id.lnlDetalhes);
        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        costCentersResult = (CostCentersResult) getIntent().getSerializableExtra(EMPREENDIMENTO);

        new carregarDados().execute();
    }

    private void setUpToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(getString(R.string.app_name));

        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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

    @Override
    public CostCentersResult getCostCenters(){
        return costCentersResult;
    }

    @Override
    public List<UnitsResult> getUnits(){
        return unitsResults;
    }

    @Override
    public SalesContractsResult getSalesContracts(){
        return salesContractsResult;
    }

    @Override
    public ReceivableBills getReceivableBills(){
        return receivableBills;
    }

    @Override
    public List<ReceivableBillsInstallmentResult> getReceivableBillsInstallments(){
        return receivableBillsInstallmentResults;
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

            unitsResults = new ArrayList<>();
            receivableBillsInstallmentResults = new ArrayList<>();

            RecipesController recipesController = new RecipesController(DetalhesActivity.this);
            SalesContracts salesContracts = recipesController.getSalesContracts();
            Units units = recipesController.getUnits();

            if (salesContracts!=null){
                for (SalesContractsResult s: salesContracts.costCentersResults) {
                    if (s.enterpriseId==costCentersResult.id){
                        salesContractsResult = s;

                        receivableBills = recipesController.getReceivableBills(s.receivableBillId);

                        ReceivableBillsInstallment receivableBillsInstallment = recipesController.getReceivableBillsInstallment(s.receivableBillId);
                        if (receivableBillsInstallment!=null){
                            receivableBillsInstallmentResults.addAll(receivableBillsInstallment.costCentersResults);
                        }
                    }
                }
            }

            if (units!=null){
                for (UnitsResult u: units.unitsResults) {
                    if (u.enterpriseId==costCentersResult.id){
                        unitsResults.add(u);
                    }
                }
            }

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
