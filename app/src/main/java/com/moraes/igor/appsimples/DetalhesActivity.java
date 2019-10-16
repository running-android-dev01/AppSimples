package com.moraes.igor.appsimples;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.moraes.igor.appsimples.Json.AccountsBalances;
import com.moraes.igor.appsimples.Json.AccountsBalancesResult;
import com.moraes.igor.appsimples.Json.BillsInstallment;
import com.moraes.igor.appsimples.Json.BillsInstallmentResult;
import com.moraes.igor.appsimples.Json.CostCenters;
import com.moraes.igor.appsimples.Json.CostCentersResult;
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

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.text.TextUtils;
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

    private EnterprisesResult enterprisesResult;


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

        enterprisesResult = (EnterprisesResult) getIntent().getSerializableExtra(EMPREENDIMENTO);

        txtEmpreendimento.setText(enterprisesResult.name);
        txtEndereco.setText(enterprisesResult.adress);

        //carregarDados();
    }

    @Override
    protected void onResume() {
        super.onResume();
        carregarDados();
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

    private void carregarDados(){
        final ProgressDialog progresso = ProgressDialog.show(DetalhesActivity.this, "", "Carregando dados");

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        CollectionReference enterprisesRef = db.collection("Empreendimento");
        Query query = enterprisesRef.whereEqualTo("id", enterprisesResult.id);
        query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                Log.d("SeeEngeService", "onComplete");
                if (task.isSuccessful()) {
                    QuerySnapshot querySnapshot = task.getResult();
                    if (querySnapshot.size()>0) {
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            empreendimento = document.toObject(Empreendimento.class);
                            Log.d("SeeEngeService", document.getId() + " => " + document.getData());
                        }

                    }
                } else {
                    Log.e("SeeEngeService", "Error getting documents."+ task.getException());
                }

                if (progresso != null) {
                    progresso.dismiss();
                }
                onFrag(DetalhesEmpreendimentoFragment.newInstance());

            }
        });
    }
}
