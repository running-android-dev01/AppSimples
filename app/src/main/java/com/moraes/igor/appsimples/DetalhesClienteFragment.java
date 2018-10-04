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

import com.moraes.igor.appsimples.model.Empreendimento;

import java.util.Objects;


public class DetalhesClienteFragment extends Fragment {
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
        Empreendimento getEmpreendimento();
    }


    private void setupRecycler() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recycler_view.setLayoutManager(layoutManager);

        adapter = new DetalhesClienteAdapter(Objects.requireNonNull(getActivity()));
        recycler_view.setAdapter(adapter);

        recycler_view.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
    }

    private void carregarLista(){
        adapter.atualizarLista(mListener.getEmpreendimento().lUnidades);
    }
}
