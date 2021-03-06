package com.moraes.igor.appsimples;

import android.view.View;

import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

class MainViewHolder extends RecyclerView.ViewHolder {

    final AppCompatTextView txtEmpreendimento;
    final AppCompatTextView txtEndereco;
    final AppCompatTextView txtResponsavelTecnico;

    MainViewHolder(View itemView) {
        super(itemView);

        txtEmpreendimento =  itemView.findViewById(R.id.txtEmpreendimento);
        txtEndereco =  itemView.findViewById(R.id.txtEndereco);
        txtResponsavelTecnico =  itemView.findViewById(R.id.txtResponsavelTecnico);
    }
}
