package com.moraes.igor.appsimples;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.moraes.igor.appsimples.model.Empreendimento;
import java.util.Locale;

public class DetalhesEmpreendimentoFragment extends Fragment {
    private OnFragmentInteractionListener mListener;
    private final Locale locale = new Locale("pt-BR");

    static DetalhesEmpreendimentoFragment newInstance() {
        DetalhesEmpreendimentoFragment fragment = new DetalhesEmpreendimentoFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detalhes_empreendimento, container, false);


        AppCompatTextView txtEmpreendimento = view.findViewById(R.id.txtEmpreendimento);
        AppCompatTextView txtEndereco = view.findViewById(R.id.txtEndereco);
        AppCompatTextView txtValorProjetado = view.findViewById(R.id.txtValorProjetado);
        AppCompatTextView txtPavimentos = view.findViewById(R.id.txtPavimentos);
        AppCompatTextView txtUnidades = view.findViewById(R.id.txtUnidades);
        AppCompatTextView txtAreaTotalTerreno = view.findViewById(R.id.txtAreaTotalTerreno);
        AppCompatTextView txtInicioDasObras = view.findViewById(R.id.txtInicioDasObras);
        AppCompatTextView txtFimDasObras = view.findViewById(R.id.txtFimDasObras);
        AppCompatTextView txtEntregaDeChaves = view.findViewById(R.id.txtEntregaDeChaves);
        AppCompatTextView txtDuracaoAteMomento = view.findViewById(R.id.txtDuracaoAteMomento);
        AppCompatTextView txtFaltamATerminar = view.findViewById(R.id.txtFaltamATerminar);
        AppCompatTextView txtVendidas = view.findViewById(R.id.txtVendidas);
        AppCompatTextView txtDisponivel = view.findViewById(R.id.txtDisponivel);
        AppCompatTextView txtAlugadas = view.findViewById(R.id.txtAlugadas);
        AppCompatTextView txtOutras = view.findViewById(R.id.txtOutras);
        AppCompatTextView txtVgv = view.findViewById(R.id.txtVgv);
        AppCompatTextView txtVendido = view.findViewById(R.id.txtVendido);
        AppCompatTextView txtAVender = view.findViewById(R.id.txtAVender);
        AppCompatTextView txtRecebido = view.findViewById(R.id.txtRecebido);
        AppCompatTextView txtAReceber = view.findViewById(R.id.txtAReceber);


        Empreendimento empreendimento = mListener.getEmpreendimento();

        txtEmpreendimento.setText(empreendimento.empreendimento);
        txtEndereco.setText(empreendimento.endereco);
        txtValorProjetado.setText(String.format(locale, "%.2f", empreendimento.valorMProjetado));
        txtPavimentos.setText(String.format(locale, "%d", empreendimento.pavimentos));
        txtUnidades.setText(String.format(locale, "%d", empreendimento.unidades));
        txtAreaTotalTerreno.setText(String.format(locale, "%.2f", empreendimento.areaTotalTerreno));
        txtInicioDasObras.setText(empreendimento.inicioObras);
        txtFimDasObras.setText(empreendimento.fimDasObras);
        txtEntregaDeChaves.setText(empreendimento.entragaDasChaves);
        txtDuracaoAteMomento.setText(String.format(locale, "%d", empreendimento.duracaoAteMomento));
        txtFaltamATerminar.setText(String.format(locale, "%d", empreendimento.faltaParaTerminar));
        txtVendidas.setText(String.format(locale, "%d", empreendimento.vendidas));
        txtDisponivel.setText(String.format(locale, "%d", empreendimento.disponiveis));
        txtAlugadas.setText(String.format(locale, "%d", empreendimento.alugadas));
        txtOutras.setText(String.format(locale, "%d", empreendimento.outras));
        txtVgv.setText(String.format(locale, "%.2f", empreendimento.vgv));
        txtVendido.setText(String.format(locale, "%.2f", empreendimento.vendido));
        txtAVender.setText(String.format(locale, "%.2f", empreendimento.aVender));
        txtRecebido.setText(String.format(locale, "%.2f", empreendimento.recebido));
        txtAReceber.setText(String.format(locale, "%.2f", empreendimento.aReceber));

        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()  + " must implement OnFragmentInteractionListener");
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
