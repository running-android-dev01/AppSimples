package com.moraes.igor.appsimples;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.moraes.igor.appsimples.model.Contas;
import com.moraes.igor.appsimples.model.Empreendimento;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class DetalhesGraficoFragment extends Fragment {
    private OnFragmentInteractionListener mListener;
    private RecyclerView recycler_view;
    private DetalhesGraficoAdapter adapter;

    static DetalhesGraficoFragment newInstance() {
        DetalhesGraficoFragment fragment = new DetalhesGraficoFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detalhes_grafico, container, false);
        recycler_view = view.findViewById(R.id.recycler_view);

        setupRecycler();
        carregarLista();

        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        Empreendimento getEmpreendimento();
    }

    private void setupRecycler() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recycler_view.setLayoutManager(layoutManager);

        adapter = new DetalhesGraficoAdapter(Objects.requireNonNull(getActivity()));
        recycler_view.setAdapter(adapter);

        recycler_view.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
    }

    private void carregarLista(){
        Empreendimento empreendimento = mListener.getEmpreendimento();
        List<Contas> lGrafico = new ArrayList<>();

        Contas gAReceber = new Contas();
        gAReceber.titulo = getString(R.string.title_grafico_valor_receber);
        gAReceber.valor = empreendimento.valorAReceber;
        lGrafico.add(gAReceber);

        Contas gAReceber0a30 = new Contas();
        gAReceber0a30.titulo = getString(R.string.title_grafico_valor_ate_30);
        gAReceber0a30.valor = empreendimento.valorAReceber0A30;
        lGrafico.add(gAReceber0a30);

        Contas gAReceber31a60 = new Contas();
        gAReceber31a60.titulo = getString(R.string.title_grafico_valor_ate_60);
        gAReceber31a60.valor = empreendimento.valorAReceber31A60;
        lGrafico.add(gAReceber31a60);

        Contas gAReceber61a120 = new Contas();
        gAReceber61a120.titulo = getString(R.string.title_grafico_valor_ate_120);
        gAReceber61a120.valor = empreendimento.valorAReceber61A120;
        lGrafico.add(gAReceber61a120);

        Contas gAReceber121 = new Contas();
        gAReceber121.titulo = getString(R.string.title_grafico_valor_acima_121);
        gAReceber121.valor = empreendimento.valorAReceber121;
        lGrafico.add(gAReceber121);

        Contas gAPagar = new Contas();
        gAPagar.titulo = getString(R.string.title_grafico_valor_pagar);
        gAPagar.valor = empreendimento.valorAPagar;
        lGrafico.add(gAPagar);

        Contas gAPagar0a30 = new Contas();
        gAPagar0a30.titulo = getString(R.string.title_grafico_valor_ate_30);
        gAPagar0a30.valor = empreendimento.valorAPagar0A30;
        lGrafico.add(gAPagar0a30);

        Contas gAPagar31a60 = new Contas();
        gAPagar31a60.titulo = getString(R.string.title_grafico_valor_ate_60);
        gAPagar31a60.valor = empreendimento.valorAPagar31A60;
        lGrafico.add(gAPagar31a60);

        Contas gAPagar61a120 = new Contas();
        gAPagar61a120.titulo = getString(R.string.title_grafico_valor_ate_120);
        gAPagar61a120.valor = empreendimento.valorAPagar61A120;
        lGrafico.add(gAPagar61a120);

        Contas gAPagar121 = new Contas();
        gAPagar121.titulo = getString(R.string.title_grafico_valor_acima_121);
        gAPagar121.valor = empreendimento.valorAPagar121;
        lGrafico.add(gAPagar121);

        Contas gProjetado = new Contas();
        gProjetado.titulo = getString(R.string.title_grafico_valor_projetado);
        gProjetado.valor = empreendimento.valorFluxoProjetado;
        lGrafico.add(gProjetado);

        Contas gProjetado0a30 = new Contas();
        gProjetado0a30.titulo = getString(R.string.title_grafico_valor_ate_30);
        gProjetado0a30.valor = empreendimento.valorFluxoProjetado0A30;
        lGrafico.add(gProjetado0a30);

        Contas gProjetado31a60 = new Contas();
        gProjetado31a60.titulo = getString(R.string.title_grafico_valor_ate_60);
        gProjetado31a60.valor = empreendimento.valorFluxoProjetado31A60;
        lGrafico.add(gProjetado31a60);

        Contas gProjetado61a120 = new Contas();
        gProjetado61a120.titulo = getString(R.string.title_grafico_valor_ate_120);
        gProjetado61a120.valor = empreendimento.valorFluxoProjetado61A120;
        lGrafico.add(gProjetado61a120);

        Contas gProjetado121 = new Contas();
        gProjetado121.titulo = getString(R.string.title_grafico_valor_acima_121);
        gProjetado121.valor = empreendimento.valorFluxoProjetado121;
        lGrafico.add(gProjetado121);

        /*Contas gAtual = new Contas();
        gAtual.descricao = "Saldo atual :";
        gAtual.valor = "66.626,17";
        lGrafico.add(gAtual);

        Contas gBanco = new Contas();
        gBanco.descricao = "BANCO ITAU:";
        gBanco.valor = "66.115,17";
        lGrafico.add(gBanco);

        Contas gFixo = new Contas();
        gFixo.descricao = "FUNDO FIXO:";
        gFixo.valor = "511,00";
        lGrafico.add(gFixo);*/

        adapter.atualizarLista(lGrafico);
    }
}
