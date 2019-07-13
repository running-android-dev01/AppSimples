package com.moraes.igor.appsimples;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;

import com.moraes.igor.appsimples.Json.CostCenters;
import com.moraes.igor.appsimples.Json.CostCentersResult;
import com.moraes.igor.appsimples.Json.Enterprises;
import com.moraes.igor.appsimples.Json.EnterprisesResult;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView recycler_view;
    private MainAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recycler_view = findViewById(R.id.recycler_view);
        mSwipeRefreshLayout = findViewById(R.id.swipe_container);

        setUpToolbar();
        setupRecycler();

        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.primaryColor,
                android.R.color.holo_green_dark,
                android.R.color.holo_orange_dark,
                android.R.color.holo_blue_dark);

        mSwipeRefreshLayout.post(() -> {

            mSwipeRefreshLayout.setRefreshing(true);
            new carregarCentroCusto().execute();
        });
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
    }

    private void setupRecycler() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recycler_view.setLayoutManager(layoutManager);

        adapter = new MainAdapter(this);
        recycler_view.setAdapter(adapter);

        recycler_view.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
    }

    @Override
    public void onRefresh() {
        new carregarCentroCusto().execute();
    }

    class carregarCentroCusto extends AsyncTask<Void, String, List<EnterprisesResult>> {
        //private ProgressDialog progresso;


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //progresso = ProgressDialog.show(MainActivity.this, "", "Carregando dados");
            mSwipeRefreshLayout.setRefreshing(true);
        }

        @SuppressWarnings("unchecked")
        @Override
        protected List<EnterprisesResult> doInBackground(Void... params) {
            List<EnterprisesResult> lEnterprisesResult = new ArrayList<>();

            RecipesController recipesController = new RecipesController();
            Enterprises enterprises = recipesController.getEnterprises();
            for (EnterprisesResult result: enterprises.enterprisesResults) {
                result = recipesController.getEnterprises(result.id);
                if (result.salesDetails!=null && result.salesDetails.generalSalesValue!=null &&  Double.parseDouble(result.salesDetails.generalSalesValue)>0.0){
                    lEnterprisesResult.add(result);
                }

            }
            return lEnterprisesResult;
        }

        @SuppressWarnings("unchecked")
        @Override
        protected void onPostExecute(final List<EnterprisesResult> lEnterprisesResult) {
            mSwipeRefreshLayout.setRefreshing(false);
            adapter.atualizarLista(lEnterprisesResult);
        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
            //if (progresso != null) {
            //    progresso.setMessage(values[0]);
            //}
        }
    }
}
