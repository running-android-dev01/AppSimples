package com.moraes.igor.appsimples;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.moraes.igor.appsimples.model.Unidades;

import java.text.DecimalFormat;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

public class DetalhesUnidadesAdapter extends RecyclerView.Adapter<DetalhesUnidadesViewHolder> {
    private Context context;
    private List<Unidades> lUnidades;
    private DecimalFormat dForat = new DecimalFormat(",##0.00");

    DetalhesUnidadesAdapter(Context context){
        this.context = context;
    }

    void atualizarLista(List<Unidades> lUnidades) {
        this.lUnidades = lUnidades;
        notifyDataSetChanged();
    }

    @Override
    @NonNull
    public DetalhesUnidadesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        DetalhesUnidadesViewHolder viewHolder;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        View v1 = inflater.inflate(R.layout.adapter_detalhes_unidades, parent, false);
        viewHolder = new DetalhesUnidadesViewHolder(v1);

        return viewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull DetalhesUnidadesViewHolder viewHolder, int position) {
        final Unidades unidades = lUnidades.get(position);

        viewHolder.txtApto.setText(unidades.unidade);
        viewHolder.txtMetragem.setText(String.format("%s M2", dForat.format(unidades.area)));
        viewHolder.txtValor.setText(String.format("R$ %s", dForat.format(unidades.valorVgv)));

        if (unidades.codSituacao.equals("V")){
            viewHolder.cardUnidade.setCardBackgroundColor(ContextCompat.getColor(context, R.color.lightSalmon));
        }else if (unidades.codSituacao.equals("D")){
            viewHolder.cardUnidade.setCardBackgroundColor(ContextCompat.getColor(context, R.color.lightGreen));
        }else {
            viewHolder.cardUnidade.setCardBackgroundColor(ContextCompat.getColor(context, R.color.Khaki));
        }
    }

    @Override
    public int getItemCount() {
        return lUnidades != null ? lUnidades.size() : 0;
    }
}
