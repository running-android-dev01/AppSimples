package com.moraes.igor.appsimples;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.moraes.igor.appsimples.model.Contas;

import java.text.DecimalFormat;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

class DetalhesGraficoAdapter extends RecyclerView.Adapter<DetalhesGraficoViewHolder> {
    private final Context context;
    private List<Contas> lContas;
    DecimalFormat dForat = new DecimalFormat(",##0.00");

    DetalhesGraficoAdapter(Context context) {
        this.context = context;
    }

    void atualizarLista(List<Contas> lContas) {
        this.lContas = lContas;
        notifyDataSetChanged();
    }

    @Override
    @NonNull
    public DetalhesGraficoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        DetalhesGraficoViewHolder viewHolder;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        View v1 = inflater.inflate(R.layout.adapter_detalhes_grafico, parent, false);
        viewHolder = new DetalhesGraficoViewHolder(v1);

        return viewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull DetalhesGraficoViewHolder viewHolder, int position) {
        final Contas grafico = lContas.get(position);

        viewHolder.txtDescricao.setText(grafico.titulo);

        viewHolder.txtValor.setTextColor(ContextCompat.getColor(context, grafico.valor>=0.0?R.color.secondaryTextColor:R.color.thirdTextColor));
        viewHolder.txtValor.setText(dForat.format(grafico.valor));
    }

    @Override
    public int getItemCount() {
        return lContas != null ? lContas.size() : 0;
    }
}
