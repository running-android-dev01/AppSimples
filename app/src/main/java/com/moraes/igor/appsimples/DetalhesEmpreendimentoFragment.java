package com.moraes.igor.appsimples;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.moraes.igor.appsimples.mode.Empreendimento;

import java.util.Locale;

public class DetalhesEmpreendimentoFragment extends Fragment {
    private OnFragmentInteractionListener mListener;
    private Locale locale = new Locale("pt-BR");

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
        AppCompatTextView txtLancamento = view.findViewById(R.id.txtLancamento);
        AppCompatTextView txtInicioObras = view.findViewById(R.id.txtInicioObras);
        AppCompatTextView txtFinalObras = view.findViewById(R.id.txtFinalObras);

        AppCompatTextView txtUnidades = view.findViewById(R.id.txtUnidades);
        AppCompatTextView txtUnidadesVendidas = view.findViewById(R.id.txtUnidadesVendidas);
        AppCompatTextView txtUnidadesDisponivel = view.findViewById(R.id.txtUnidadesDisponivel);
        AppCompatTextView txtUnidadesOutras = view.findViewById(R.id.txtUnidadesOutras);
        AppCompatTextView txtVgv = view.findViewById(R.id.txtVgv);
        AppCompatTextView txtVendas = view.findViewById(R.id.txtVendas);
        AppCompatTextView txtRecebidos = view.findViewById(R.id.txtRecebidos);
        AppCompatTextView txtVgvCusto = view.findViewById(R.id.txtVgvCusto);
        AppCompatTextView txtCustoOrcamento = view.findViewById(R.id.txtCustoOrcamento);
        AppCompatTextView txtCustoIncorrido = view.findViewById(R.id.txtCustoIncorrido);
        AppCompatTextView txtResultadoAtual = view.findViewById(R.id.txtResultadoAtual);
        AppCompatTextView txtSaldoDevedor = view.findViewById(R.id.txtSaldoDevedor);

        Empreendimento empreendimento = mListener.getEmpreendimento();

        txtEmpreendimento.setText(empreendimento.empreendimento);
        txtEndereco.setText(empreendimento.endereco);
        txtValorProjetado.setText(String.format(locale, "%.2f", empreendimento.valorProjeto));
        txtLancamento.setText(empreendimento.lancamento);
        txtInicioObras.setText(empreendimento.inicioObra);
        txtFinalObras.setText(empreendimento.finalObra);

        txtUnidades.setText(String.format(locale, "%d", empreendimento.unidades));
        txtUnidadesVendidas.setText(String.format(locale, "%d", empreendimento.unidadesVendidas));
        txtUnidadesDisponivel.setText(String.format(locale, "%d", empreendimento.unidadesDisponivel));
        txtUnidadesOutras.setText(String.format(locale, "%d", empreendimento.unidadesOutras));
        txtVgv.setText(String.format(locale, "%.2f", empreendimento.vgv));
        txtVendas.setText(String.format(locale, "%.2f (51 VGV)", empreendimento.vendas));
        txtRecebidos.setText(String.format(locale, "%.2f (29 VGV)", empreendimento.recebido));
        txtVgvCusto.setText("+2,5%");
        txtCustoOrcamento.setText(String.format(locale, "%.2f", empreendimento.custoOrcado));
        txtCustoIncorrido.setText(String.format(locale, "%.2f  (24 ORC)", empreendimento.custoIncorrido));
        txtResultadoAtual.setText(String.format(locale, "%.2f", empreendimento.resultadoAtual));
        txtSaldoDevedor.setText(String.format(locale, "%.2f", empreendimento.saldoDevedor));

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
