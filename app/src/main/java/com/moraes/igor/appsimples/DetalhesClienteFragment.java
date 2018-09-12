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

import com.moraes.igor.appsimples.mode.Cliente;
import com.moraes.igor.appsimples.mode.Empreendimento;



public class DetalhesClienteFragment extends Fragment {
    private OnFragmentInteractionListener mListener;
    private RecyclerView recycler_view;

    static DetalhesClienteFragment newInstance() {
        DetalhesClienteFragment fragment = new DetalhesClienteFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detalhes_cliente, container, false);
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
        /*LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recycler_view.setLayoutManager(layoutManager);

        adapter = new MainAdapter(this);
        recycler_view.setAdapter(adapter);

        recycler_view.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));*/
    }

    private void carregarLista(){

        for (int i = 0; i<20; i++){


            Cliente cliente101 = new Cliente();
            cliente101.apartamento = ((i+1)*100)+1;
            cliente101.nome = "Jesiel Lima";
            cliente101.status = "I";
            cliente101.contrato = 625000.00;
            cliente101.devido = 450000.00;
            cliente101.recebido = 175000.00;


            Cliente cliente102 = new Cliente();
            cliente102.apartamento = ((i+1)*100)+2;
            cliente102.nome = "DISPONIVEL";
            cliente102.status = "OK";
            cliente102.contrato = 0.0;
            cliente102.devido = 0.0;
            cliente102.recebido = 0.0;

            Cliente cliente103 = new Cliente();
            cliente103.apartamento = ((i+1)*100)+3;
            cliente103.nome = "Luciano Paradizzo";
            cliente103.status = "A";
            cliente103.contrato = 635000.00;
            cliente103.devido = 190000.00;
            cliente103.recebido = 455000.00;


            Cliente cliente104 = new Cliente();
            cliente104.apartamento = ((i+1)*100)+4;
            cliente104.nome = "Marcelo Ferreira";
            cliente104.status = "Q";
            cliente104.contrato = 650000.00;
            cliente104.devido = 250000.00;
            cliente104.recebido = 400000.00;
        }


        /*List<Empreendimento> lEmpreendimento = new ArrayList<>();

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
}
