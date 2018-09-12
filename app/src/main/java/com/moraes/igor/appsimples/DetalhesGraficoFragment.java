package com.moraes.igor.appsimples;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.moraes.igor.appsimples.mode.Empreendimento;


public class DetalhesGraficoFragment extends Fragment {
    private OnFragmentInteractionListener mListener;

    static DetalhesGraficoFragment newInstance() {
        DetalhesGraficoFragment fragment = new DetalhesGraficoFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detalhes_grafico, container, false);
        Empreendimento empreendimento = mListener.getEmpreendimento();

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
}
