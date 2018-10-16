package com.moraes.igor.appsimples;

import android.view.View;

import androidx.appcompat.widget.AppCompatTextView;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class DetalhesUnidadesViewHolder extends RecyclerView.ViewHolder {
    final CardView cardUnidade;
    final AppCompatTextView txtApto;
    final AppCompatTextView txtMetragem;
    final AppCompatTextView txtValor;

    DetalhesUnidadesViewHolder(View itemView) {
        super(itemView);

        cardUnidade =  itemView.findViewById(R.id.cardUnidade);
        txtApto =  itemView.findViewById(R.id.txtApto);
        txtMetragem =  itemView.findViewById(R.id.txtMetragem);
        txtValor =  itemView.findViewById(R.id.txtValor);
    }
}