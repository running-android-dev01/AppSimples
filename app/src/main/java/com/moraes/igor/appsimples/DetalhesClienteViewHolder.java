package com.moraes.igor.appsimples;

import android.view.View;

import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

public class DetalhesClienteViewHolder extends RecyclerView.ViewHolder {
    final AppCompatTextView txtApto;
    final AppCompatTextView txtContrato;
    final AppCompatTextView txtReceber;
    final AppCompatTextView txtRecebido;
    final AppCompatTextView txtPercentRecebido;
    final AppCompatTextView txtSituacao;

    DetalhesClienteViewHolder(View itemView) {
        super(itemView);

        txtApto =  itemView.findViewById(R.id.txtApto);
        txtContrato =  itemView.findViewById(R.id.txtContrato);
        txtReceber =  itemView.findViewById(R.id.txtReceber);
        txtRecebido =  itemView.findViewById(R.id.txtRecebido);
        txtPercentRecebido =  itemView.findViewById(R.id.txtPercentRecebido);
        txtSituacao =  itemView.findViewById(R.id.txtSituacao);
    }
}
