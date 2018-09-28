package com.moraes.igor.appsimples;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.moraes.igor.appsimples.mode.Cliente;
import com.moraes.igor.appsimples.mode.Grafico;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class DetalhesGraficoAdapter extends RecyclerView.Adapter<DetalhesGraficoViewHolder> {
    private final Context context;
    private List<Grafico> lGrafico;

    DetalhesGraficoAdapter(Context context) {
        this.context = context;
    }

    public void atualizarLista(List<Grafico> lGrafico) {
        this.lGrafico = lGrafico;
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
        final Grafico grafico = lGrafico.get(position);

        viewHolder.txtDescricao.setText(grafico.descricao);
        viewHolder.txtValor.setText(grafico.valor);
    }

    @Override
    public int getItemCount() {
        return lGrafico != null ? lGrafico.size() : 0;
    }
}
