package com.moraes.igor.appsimples;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;

import com.moraes.igor.appsimples.Json.CostCenters;
import com.moraes.igor.appsimples.Json.CostCentersResult;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recycler_view;
    private MainAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recycler_view = findViewById(R.id.recycler_view);

        setUpToolbar();
        setupRecycler();
        carregarLista();
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

    private void carregarLista(){
        new carregarCentroCusto().execute();



        /*
        List<Empreendimento> lEmpreendimento = new ArrayList<>();

        Empreendimento empreendimento1 = new Empreendimento();
        empreendimento1.empreendimento = "Empreendimento Blue Life";
        empreendimento1.endereco = "Barra da Tijuca/Rio de Janeiro-RJ";

        empreendimento1.valorProjeto =  5500.00;
        empreendimento1.lancamento = "20/08/2017";
        empreendimento1.inicioObra = "30/11/2017";
        empreendimento1.finalObra = "31/08/2019";

        empreendimento1.unidades = 20;
        empreendimento1.unidadesVendidas = 10;
        empreendimento1.unidadesDisponivel = 9;
        empreendimento1.unidadesOutras = 1;

        empreendimento1.vgv = 10000000.00;
        empreendimento1.vendas =  5125000.00;

        empreendimento1.recebido = 2925000.00;
        empreendimento1.custoOrcado = 8000000.00;
        empreendimento1.custoIncorrido = 1900000.00;
        empreendimento1.resultadoAtual = 100000.00;
        empreendimento1.saldoDevedor =  2200000.00;

        Empreendimento empreendimento2 = new Empreendimento();
        empreendimento2.empreendimento = "Empreendimento Versatto";
        empreendimento2.endereco = "Meier/Rio de Janeiro-RJ";

        empreendimento2.valorProjeto =  5500.00;
        empreendimento2.lancamento = "20/08/2017";
        empreendimento2.inicioObra = "30/11/2017";
        empreendimento2.finalObra = "31/08/2019";

        empreendimento2.unidades = 20;
        empreendimento2.unidadesVendidas = 10;
        empreendimento2.unidadesDisponivel = 9;
        empreendimento2.unidadesOutras = 1;

        empreendimento2.vgv = 10000000.00;
        empreendimento2.vendas =  5125000.00;

        empreendimento2.recebido = 2925000.00;
        empreendimento2.custoOrcado = 8000000.00;
        empreendimento2.custoIncorrido = 1900000.00;
        empreendimento2.resultadoAtual = 100000.00;
        empreendimento2.saldoDevedor =  2200000.00;

        lEmpreendimento.add(empreendimento1);
        lEmpreendimento.add(empreendimento2);

        adapter.atualizarLista(lEmpreendimento);*/
    }

    class carregarCentroCusto extends AsyncTask<Void, String, CostCenters> {
        private ProgressDialog progresso;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progresso = ProgressDialog.show(MainActivity.this, "", "Carregando dados");
        }

        @SuppressWarnings("unchecked")
        @Override
        protected CostCenters doInBackground(Void... params) {

            RecipesController recipesController = new RecipesController(MainActivity.this);
            return recipesController.getCostCenters();
        }

        @SuppressWarnings("unchecked")
        @Override
        protected void onPostExecute(final CostCenters costCenters) {
            if (progresso != null) {
                progresso.dismiss();
            }
            if (costCenters!=null){
                adapter.atualizarLista(costCenters.costCentersResults);
            }

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
