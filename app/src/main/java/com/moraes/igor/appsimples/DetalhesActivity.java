package com.moraes.igor.appsimples;

import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.moraes.igor.appsimples.mode.Empreendimento;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.MenuItem;
import android.widget.LinearLayout;

public class DetalhesActivity extends AppCompatActivity
        implements DetalhesEmpreendimentoFragment.OnFragmentInteractionListener
        , DetalhesClienteFragment.OnFragmentInteractionListener
        , DetalhesGraficoFragment.OnFragmentInteractionListener  {

    private final static String TAG = DetalhesActivity.class.getName();
    public final static String EMPREENDIMENTO = TAG + ".empreendimento" ;

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

        onFrag(DetalhesEmpreendimentoFragment.newInstance());

        empreendimento = (Empreendimento)getIntent().getSerializableExtra(EMPREENDIMENTO);
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
}
