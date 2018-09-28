package com.moraes.igor.appsimples;

import android.view.View;

import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

public class DetalhesGraficoViewHolder extends RecyclerView.ViewHolder {
    final AppCompatTextView txtDescricao;
    final AppCompatTextView txtValor;

    DetalhesGraficoViewHolder(View itemView) {
        super(itemView);

        txtDescricao =  itemView.findViewById(R.id.txtDescricao);
        txtValor =  itemView.findViewById(R.id.txtValor);
    }

}
