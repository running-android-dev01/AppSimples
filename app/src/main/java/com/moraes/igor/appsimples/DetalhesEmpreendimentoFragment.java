package com.moraes.igor.appsimples;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.moraes.igor.appsimples.model.Empreendimento;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

public class DetalhesEmpreendimentoFragment extends Fragment {
    private OnFragmentInteractionListener mListener;
    private final Locale locale = new Locale("pt-BR");
    DecimalFormat iForat = new DecimalFormat("0.##");
    DecimalFormat dForat = new DecimalFormat(",##0.00");

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

        AppCompatTextView txtAreaTerreno = view.findViewById(R.id.txtAreaTerreno);
        AppCompatTextView txtAreaTotalTerreno = view.findViewById(R.id.txtAreaTotalTerreno);
        AppCompatTextView txtInicioDasObras = view.findViewById(R.id.txtInicioDasObras);
        AppCompatTextView txtFimDasObras = view.findViewById(R.id.txtFimDasObras);
        AppCompatTextView txtEntregaDeChaves = view.findViewById(R.id.txtEntregaDeChaves);
        AppCompatTextView txtDuracaoAteMomento = view.findViewById(R.id.txtDuracaoAteMomento);
        AppCompatTextView txtFaltamATerminar = view.findViewById(R.id.txtFaltamATerminar);

        AppCompatTextView txtVendidas = view.findViewById(R.id.txtVendidas);
        AppCompatTextView txtDisponivel = view.findViewById(R.id.txtDisponivel);
        AppCompatTextView txtIndisponivel = view.findViewById(R.id.txtIndisponivel);


        AppCompatTextView txtVgv = view.findViewById(R.id.txtVgv);
        AppCompatTextView txtVendido = view.findViewById(R.id.txtVendido);
        AppCompatTextView txtAVender = view.findViewById(R.id.txtAVender);
        AppCompatTextView txtDiferencaVgv = view.findViewById(R.id.txtDiferencaVgv);
        AppCompatTextView txtRecebido = view.findViewById(R.id.txtRecebido);
        AppCompatTextView txtAReceber = view.findViewById(R.id.txtAReceber);
        AppCompatTextView txtRecFinanciado = view.findViewById(R.id.txtRecFinanciado);

        Empreendimento empreendimento = mListener.getEmpreendimento();

        txtEmpreendimento.setText(empreendimento.empreendimento);
        txtEndereco.setText(empreendimento.endereco);

        txtValorProjetado.setTextColor(ContextCompat.getColor(getActivity(), empreendimento.valorMProjetado>=0.0?R.color.secondaryTextColor:R.color.thirdTextColor));
        txtValorProjetado.setText(dForat.format(empreendimento.valorMProjetado));

        txtPavimentos.setTextColor(ContextCompat.getColor(getActivity(), empreendimento.pavimentos>=0.0?R.color.secondaryTextColor:R.color.thirdTextColor));
        txtPavimentos.setText(iForat.format(empreendimento.pavimentos));

        txtUnidades.setTextColor(ContextCompat.getColor(getActivity(), empreendimento.unidades>=0.0?R.color.secondaryTextColor:R.color.thirdTextColor));
        txtUnidades.setText(iForat.format(empreendimento.unidades));

        txtAreaTotalTerreno.setTextColor(ContextCompat.getColor(getActivity(), empreendimento.areaTotalTerreno>=0.0?R.color.secondaryTextColor:R.color.thirdTextColor));
        txtAreaTotalTerreno.setText(dForat.format(empreendimento.areaTotalTerreno));


        txtAreaTerreno.setTextColor(ContextCompat.getColor(getActivity(), empreendimento.areaTerreno>=0.0?R.color.secondaryTextColor:R.color.thirdTextColor));
        txtAreaTerreno.setText(dForat.format(empreendimento.areaTerreno));

        txtInicioDasObras.setText(empreendimento.inicioObras);

        txtFimDasObras.setText(empreendimento.fimDasObras);

        txtEntregaDeChaves.setText(empreendimento.entragaDasChaves);

        txtDuracaoAteMomento.setTextColor(ContextCompat.getColor(getActivity(), empreendimento.duracaoAteMomento>=0.0?R.color.secondaryTextColor:R.color.thirdTextColor));
        txtDuracaoAteMomento.setText(iForat.format(empreendimento.duracaoAteMomento));

        txtFaltamATerminar.setTextColor(ContextCompat.getColor(getActivity(), empreendimento.faltaParaTerminar>=0.0?R.color.secondaryTextColor:R.color.thirdTextColor));
        txtFaltamATerminar.setText(iForat.format(empreendimento.faltaParaTerminar));

        txtVendidas.setTextColor(ContextCompat.getColor(getActivity(), empreendimento.vendidas>=0.0?R.color.secondaryTextColor:R.color.thirdTextColor));
        txtVendidas.setText(iForat.format(empreendimento.vendidas));

        txtDisponivel.setTextColor(ContextCompat.getColor(getActivity(), empreendimento.disponiveis>=0.0?R.color.secondaryTextColor:R.color.thirdTextColor));
        txtDisponivel.setText(iForat.format(empreendimento.disponiveis));

        txtIndisponivel.setTextColor(ContextCompat.getColor(getActivity(), empreendimento.indisponivel>=0.0?R.color.secondaryTextColor:R.color.thirdTextColor));
        txtIndisponivel.setText(iForat.format(empreendimento.indisponivel));

        txtVgv.setTextColor(ContextCompat.getColor(getActivity(), empreendimento.vgv>=0.0?R.color.secondaryTextColor:R.color.thirdTextColor));
        txtVgv.setText(dForat.format(empreendimento.vgv));

        txtVendido.setTextColor(ContextCompat.getColor(getActivity(), empreendimento.vendido>=0.0?R.color.secondaryTextColor:R.color.thirdTextColor));
        txtVendido.setText(dForat.format(empreendimento.vendido));

        txtAVender.setTextColor(ContextCompat.getColor(getActivity(), empreendimento.aVender>=0.0?R.color.secondaryTextColor:R.color.thirdTextColor));
        txtAVender.setText(dForat.format(empreendimento.aVender));

        txtRecebido.setTextColor(ContextCompat.getColor(getActivity(), empreendimento.recebido>=0.0?R.color.secondaryTextColor:R.color.thirdTextColor));
        txtRecebido.setText(dForat.format(empreendimento.recebido));

        txtDiferencaVgv.setTextColor(ContextCompat.getColor(getActivity(), empreendimento.diferencaVgv>=0.0?R.color.secondaryTextColor:R.color.thirdTextColor));
        txtDiferencaVgv.setText(dForat.format(empreendimento.diferencaVgv));

        txtAReceber.setTextColor(ContextCompat.getColor(getActivity(), empreendimento.aReceber>=0.0?R.color.secondaryTextColor:R.color.thirdTextColor));
        txtAReceber.setText(dForat.format(empreendimento.aReceber));

        txtRecFinanciado.setTextColor(ContextCompat.getColor(getActivity(), empreendimento.recFinanciadoOutros>=0.0?R.color.secondaryTextColor:R.color.thirdTextColor));
        txtRecFinanciado.setText(dForat.format(empreendimento.recFinanciadoOutros));

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
