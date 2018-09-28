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

import com.moraes.igor.appsimples.Json.CostCenters;
import com.moraes.igor.appsimples.Json.CostCentersResult;
import com.moraes.igor.appsimples.Json.ReceivableBills;
import com.moraes.igor.appsimples.Json.ReceivableBillsInstallmentResult;
import com.moraes.igor.appsimples.Json.SalesContractsResult;
import com.moraes.igor.appsimples.Json.UnitsResult;
import com.moraes.igor.appsimples.mode.Cliente;
import com.moraes.igor.appsimples.mode.Empreendimento;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;


public class DetalhesClienteFragment extends Fragment {
    private Locale locale = new Locale("pt-BR");
    private OnFragmentInteractionListener mListener;
    private RecyclerView recycler_view;
    private DetalhesClienteAdapter adapter;

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
        CostCentersResult getCostCenters();
        List<UnitsResult> getUnits();
        SalesContractsResult getSalesContracts();
        ReceivableBills getReceivableBills();
        List<ReceivableBillsInstallmentResult> getReceivableBillsInstallments();
    }


    private void setupRecycler() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recycler_view.setLayoutManager(layoutManager);

        adapter = new DetalhesClienteAdapter(getActivity());
        recycler_view.setAdapter(adapter);

        recycler_view.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
    }

    private void carregarLista(){
        List<Cliente> lCliente = new ArrayList<>();
        List<UnitsResult> lUnitsResult = mListener.getUnits();
        for (UnitsResult unitsResult: lUnitsResult) {
            Cliente cliente = new Cliente();
            cliente.apto = unitsResult.name;
            cliente.contrato = String.format(locale, "%.2f", unitsResult.terrainValue);
            cliente.receber = String.format(locale, "%.2f", (unitsResult.terrainValue-unitsResult.generalSaleValueFraction));
            cliente.recebido = String.format(locale, "%.2f", unitsResult.generalSaleValueFraction);
            cliente.percentRecebido = "-";
            if (unitsResult.terrainValue>0){
                cliente.percentRecebido = String.format(locale, "%.2f", (unitsResult.generalSaleValueFraction/unitsResult.terrainValue)*100);
            }

            if (unitsResult.commercialStock.equals("D")){
                cliente.situacao = "Disponível";
            }else if (unitsResult.commercialStock.equals("V")){
                cliente.situacao = "Vendida";
            }else if (unitsResult.commercialStock.equals("L")){
                cliente.situacao = "Locada";
            }else if (unitsResult.commercialStock.equals("C")){
                cliente.situacao = "Reservada";
            }else if (unitsResult.commercialStock.equals("R")){
                cliente.situacao = "Reserva Técnica";
            }else if (unitsResult.commercialStock.equals("E")){
                cliente.situacao = "Permuta";
            }else if (unitsResult.commercialStock.equals("M")){
                cliente.situacao = "Mutuo";
            }else if (unitsResult.commercialStock.equals("P")){
                cliente.situacao = "Proposta";
            }else if (unitsResult.commercialStock.equals("T")){
                cliente.situacao = "Transferido";
            }else if (unitsResult.commercialStock.equals("G")){
                cliente.situacao = "Vendido/Terceiros";
            }

            lCliente.add(cliente);
        }

        Collections.sort(lCliente, new Comparator<Cliente>() {
            @Override
            public int compare(Cliente o1, Cliente o2) {
                return o1.apto.compareTo(o2.apto);
            }
        });

        adapter.atualizarLista(lCliente);
    }
}
