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

import com.moraes.igor.appsimples.Json.CostCentersResult;
import com.moraes.igor.appsimples.Json.ReceivableBills;
import com.moraes.igor.appsimples.Json.ReceivableBillsInstallmentResult;
import com.moraes.igor.appsimples.Json.SalesContractsResult;
import com.moraes.igor.appsimples.Json.UnitsResult;
import com.moraes.igor.appsimples.mode.Empreendimento;

import java.util.List;
import java.util.Locale;

public class DetalhesEmpreendimentoFragment extends Fragment {
    private OnFragmentInteractionListener mListener;
    private Locale locale = new Locale("pt-BR");

    AppCompatTextView txtEmpreendimento;
    AppCompatTextView txtEndereco;
    AppCompatTextView txtValorProjetado;
    AppCompatTextView txtPavimentos;
    AppCompatTextView txtUnidades;
    AppCompatTextView txtAreaTotalTerreno;
    AppCompatTextView txtInicioDasObras;
    AppCompatTextView txtFimDasObras;
    AppCompatTextView txtEntregaDeChaves;
    AppCompatTextView txtDuracaoAteMomento;
    AppCompatTextView txtFaltamATerminar;
    AppCompatTextView txtVendidas;
    AppCompatTextView txtDisponivel;
    AppCompatTextView txtAlugadas;
    AppCompatTextView txtOutras;
    AppCompatTextView txtVgv;
    AppCompatTextView txtVendido;
    AppCompatTextView txtAVender;
    AppCompatTextView txtRecebido;
    AppCompatTextView txtAReceber;

    static DetalhesEmpreendimentoFragment newInstance() {
        DetalhesEmpreendimentoFragment fragment = new DetalhesEmpreendimentoFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detalhes_empreendimento, container, false);


        txtEmpreendimento = view.findViewById(R.id.txtEmpreendimento);
        txtEndereco = view.findViewById(R.id.txtEndereco);
        txtValorProjetado = view.findViewById(R.id.txtValorProjetado);
        txtPavimentos = view.findViewById(R.id.txtPavimentos);
        txtUnidades = view.findViewById(R.id.txtUnidades);
        txtAreaTotalTerreno = view.findViewById(R.id.txtAreaTotalTerreno);
        txtInicioDasObras = view.findViewById(R.id.txtInicioDasObras);
        txtFimDasObras = view.findViewById(R.id.txtFimDasObras);
        txtEntregaDeChaves = view.findViewById(R.id.txtEntregaDeChaves);
        txtDuracaoAteMomento = view.findViewById(R.id.txtDuracaoAteMomento);
        txtFaltamATerminar = view.findViewById(R.id.txtFaltamATerminar);
        txtVendidas = view.findViewById(R.id.txtVendidas);
        txtDisponivel = view.findViewById(R.id.txtDisponivel);
        txtAlugadas = view.findViewById(R.id.txtAlugadas);
        txtOutras = view.findViewById(R.id.txtOutras);
        txtVgv = view.findViewById(R.id.txtVgv);
        txtVendido = view.findViewById(R.id.txtVendido);
        txtAVender = view.findViewById(R.id.txtAVender);
        txtRecebido = view.findViewById(R.id.txtRecebido);
        txtAReceber = view.findViewById(R.id.txtAReceber);

        //Empreendimento empreendimento = mListener.getEmpreendimento();

        CostCentersResult costCentersResult = mListener.getCostCenters();
        SalesContractsResult salesContractsResult = mListener.getSalesContracts();
        List<UnitsResult> unitsResults = mListener.getUnits();
        ReceivableBills receivableBills = mListener.getReceivableBills();
        List<ReceivableBillsInstallmentResult> receivableBillsInstallmentResults = mListener.getReceivableBillsInstallments();


        txtEmpreendimento.setText(costCentersResult.name);
        txtEndereco.setText(costCentersResult.endereco);

        String empreendimento = costCentersResult.name;
        String endereco = costCentersResult.endereco;
        String valorProjetado = "-";
        String pavimentos = "-";
        String unidades = String.format(locale, "%d", unitsResults.size());
        String areaTotalTerreno = "-";
        String inicioDasObras = "-";
        String fimDasObras = "-";
        String entregaDeChaves = "-";
        String duracaoAteMomento = "-";
        String faltamATerminar = "-";
        String vendidas = "-";
        String disponivel = "-";
        String alugadas = "-";
        String outras = "-";
        String vgv = "-";

        String vendido = "-";
        String aVender = "-";
        String recebido = "-";
        String aReceber = "-";

        double valorTotal = 0.0;
        if (salesContractsResult!=null){
            valorTotal = salesContractsResult.totalSellingValue;
            //valorProjetado = String.format(locale, "%.2f", (salesContractsResult.totalSellingValue/unitsResults.size()));
            //vgv = String.format(locale, "%.2f", salesContractsResult.totalSellingValue);
        }

        double dAreaTotalTerreno = 0.0;
        int iVendidas = 0;
        int iDisponivel = 0;
        int iAlugadas = 0;
        int iOutras = 0;
        double dValorProjetado = 0.0;
        double dVgv = 0.0;
        int area = 0;

        for (UnitsResult u:unitsResults) {
            if (u.commercialStock.equals("V")){
                iVendidas += 1;
            }else if (u.commercialStock.equals("D")){
                iDisponivel += 1;
            }else if (u.commercialStock.equals("L")){
                iAlugadas += 1;
            }else{
                iOutras += 1;
            }
            dVgv += u.generalSaleValueFraction;
            area += u.privateArea;

            dAreaTotalTerreno += u.terrainArea;
        }

        valorProjetado = String.format(locale, "%.2f", (dVgv/area));
        areaTotalTerreno = String.format(locale, "%.2f", dAreaTotalTerreno);
        vgv = String.format(locale, "%.2f", dVgv);


        vendidas = String.format(locale, "%d", iVendidas);
        disponivel = String.format(locale, "%d", iDisponivel);
        alugadas = String.format(locale, "%d", iAlugadas);
        outras = String.format(locale, "%d", iOutras);


        txtEmpreendimento.setText(empreendimento);
        txtEndereco.setText(endereco);
        txtValorProjetado.setText(valorProjetado);
        txtPavimentos.setText(pavimentos);
        txtUnidades.setText(unidades);
        txtAreaTotalTerreno.setText(areaTotalTerreno);
        txtInicioDasObras.setText(inicioDasObras);
        txtFimDasObras.setText(fimDasObras);
        txtEntregaDeChaves.setText(entregaDeChaves);
        txtDuracaoAteMomento.setText(duracaoAteMomento);
        txtFaltamATerminar.setText(faltamATerminar);
        txtVendidas.setText(vendidas);
        txtDisponivel.setText(disponivel);
        txtAlugadas.setText(alugadas);
        txtOutras.setText(outras);
        txtVgv.setText(vgv);
        txtVendido.setText(vendido);
        txtAVender.setText(aVender);
        txtRecebido.setText(recebido);
        txtAReceber.setText(aReceber);

        //txtUnidades.setText(String.format(locale, "%d", empreendimento.unidades));
        //txtUnidadesVendidas.setText(String.format(locale, "%d", empreendimento.unidadesVendidas));
        //txtUnidadesDisponivel.setText(String.format(locale, "%d", empreendimento.unidadesDisponivel));
        //txtUnidadesOutras.setText(String.format(locale, "%d", empreendimento.unidadesOutras));
        //txtVgv.setText(String.format(locale, "%.2f", empreendimento.vgv));
        //txtVendas.setText(String.format(locale, "%.2f (51 VGV)", empreendimento.vendas));
        //txtRecebidos.setText(String.format(locale, "%.2f (29 VGV)", empreendimento.recebido));
        //txtVgvCusto.setText("+2,5%");
        //txtCustoOrcamento.setText(String.format(locale, "%.2f", empreendimento.custoOrcado));
        //txtCustoIncorrido.setText(String.format(locale, "%.2f  (24 ORC)", empreendimento.custoIncorrido));
        //txtResultadoAtual.setText(String.format(locale, "%.2f", empreendimento.resultadoAtual));
        //txtSaldoDevedor.setText(String.format(locale, "%.2f", empreendimento.saldoDevedor));

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
        CostCentersResult getCostCenters();
        List<UnitsResult> getUnits();
        SalesContractsResult getSalesContracts();
        ReceivableBills getReceivableBills();
        List<ReceivableBillsInstallmentResult> getReceivableBillsInstallments();
    }
}
