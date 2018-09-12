package com.moraes.igor.appsimples;

import android.view.View;

import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

public class DetalhesClienteViewHolder extends RecyclerView.ViewHolder {

    AppCompatTextView txtApto;
    AppCompatTextView txtNome;
    AppCompatTextView txtStatus;
    AppCompatTextView txtContrato;
    AppCompatTextView txtDevido;
    AppCompatTextView txtRecebido;

    DetalhesClienteViewHolder(View itemView) {
        super(itemView);

        txtApto =  itemView.findViewById(R.id.txtApto);
        txtNome =  itemView.findViewById(R.id.txtNome);
        txtStatus =  itemView.findViewById(R.id.txtStatus);
        txtContrato =  itemView.findViewById(R.id.txtContrato);
        txtDevido =  itemView.findViewById(R.id.txtDevido);
        txtRecebido =  itemView.findViewById(R.id.txtRecebido);
    }
}
