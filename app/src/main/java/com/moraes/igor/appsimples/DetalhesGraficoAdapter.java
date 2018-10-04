package com.moraes.igor.appsimples;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.moraes.igor.appsimples.model.Contas;

import java.util.List;
import java.util.Locale;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

class DetalhesGraficoAdapter extends RecyclerView.Adapter<DetalhesGraficoViewHolder> {
    //private final Context context;
    private List<Contas> lContas;
    private final Locale current;

    DetalhesGraficoAdapter(Context context) {
        //this.context = context;
        this.current = context.getApplicationContext().getResources().getConfiguration().locale;
    }

    public void atualizarLista(List<Contas> lContas) {
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
        viewHolder.txtValor.setText(String.format(current, "%.2f", grafico.valor));
    }

    @Override
    public int getItemCount() {
        return lContas != null ? lContas.size() : 0;
    }
}
