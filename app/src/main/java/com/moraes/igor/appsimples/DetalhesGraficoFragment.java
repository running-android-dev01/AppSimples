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

import com.moraes.igor.appsimples.mode.Empreendimento;
import com.moraes.igor.appsimples.mode.Grafico;

import java.util.ArrayList;
import java.util.List;


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

        adapter = new DetalhesGraficoAdapter(getActivity());
        recycler_view.setAdapter(adapter);

        recycler_view.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
    }

    private void carregarLista(){
        List<Grafico> lGrafico = new ArrayList<>();

        Grafico gAReceber = new Grafico();
        gAReceber.descricao = "(+)Valores a Receber:";
        gAReceber.valor = "2.260.000,00";
        lGrafico.add(gAReceber);

        Grafico gAReceber0a30 = new Grafico();
        gAReceber0a30.descricao = "0 a 30 dd:";
        gAReceber0a30.valor = "120.000,00";
        lGrafico.add(gAReceber0a30);

        Grafico gAReceber31a60 = new Grafico();
        gAReceber31a60.descricao = "31 a 60 dd:";
        gAReceber31a60.valor = "150.000,00";
        lGrafico.add(gAReceber31a60);

        Grafico gAReceber61a120 = new Grafico();
        gAReceber61a120.descricao = "61 a 120 dd:";
        gAReceber61a120.valor = "380.000,00";
        lGrafico.add(gAReceber61a120);

        Grafico gAReceber121 = new Grafico();
        gAReceber121.descricao = "Acima  121 dd:";
        gAReceber121.valor = "1.610.000,00";
        lGrafico.add(gAReceber121);

        Grafico gAPagar = new Grafico();
        gAPagar.descricao = "(-)Valores a Pagar:";
        gAPagar.valor = "3.840.000,00";
        lGrafico.add(gAPagar);

        Grafico gAPagar0a30 = new Grafico();
        gAPagar0a30.descricao = "0 a 30 dd:";
        gAPagar0a30.valor = "80.000,00";
        lGrafico.add(gAPagar0a30);

        Grafico gAPagar31a60 = new Grafico();
        gAPagar31a60.descricao = "31 a 60 dd:";
        gAPagar31a60.valor = "110.000,00";
        lGrafico.add(gAPagar31a60);

        Grafico gAPagar61a120 = new Grafico();
        gAPagar61a120.descricao = "61 a 120 dd:";
        gAPagar61a120.valor = "150.000,00";
        lGrafico.add(gAPagar61a120);

        Grafico gAPagar121 = new Grafico();
        gAPagar121.descricao = "Acima  121 dd:";
        gAPagar121.valor = "3.500.000,00";
        lGrafico.add(gAPagar121);

        Grafico gProjetado = new Grafico();
        gProjetado.descricao = "(=)Fluxo Projetado:";
        gProjetado.valor = "-1.580.000,00";
        lGrafico.add(gProjetado);

        Grafico gProjetado0a30 = new Grafico();
        gProjetado0a30.descricao = "0 a 30 dd:";
        gProjetado0a30.valor = "40.000,00";
        lGrafico.add(gProjetado0a30);

        Grafico gProjetado31a60 = new Grafico();
        gProjetado31a60.descricao = "31 a 60 dd:";
        gProjetado31a60.valor = "40.000,00";
        lGrafico.add(gProjetado31a60);

        Grafico gProjetado61a120 = new Grafico();
        gProjetado61a120.descricao = "61 a 120 dd:";
        gProjetado61a120.valor = "230.000,00";
        lGrafico.add(gProjetado61a120);

        Grafico gProjetado121 = new Grafico();
        gProjetado121.descricao = "Acima  121 dd:";
        gProjetado121.valor = "-1.890.000,00";
        lGrafico.add(gProjetado121);

        Grafico gAtual = new Grafico();
        gAtual.descricao = "Saldo atual :";
        gAtual.valor = "66.626,17";
        lGrafico.add(gAtual);

        Grafico gBanco = new Grafico();
        gBanco.descricao = "BANCO ITAU:";
        gBanco.valor = "66.115,17";
        lGrafico.add(gBanco);

        Grafico gFixo = new Grafico();
        gFixo.descricao = "FUNDO FIXO:";
        gFixo.valor = "511,00";
        lGrafico.add(gFixo);

        adapter.atualizarLista(lGrafico);
    }
}
