package com.moraes.igor.appsimples;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.moraes.igor.appsimples.Json.CostCentersResult;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

class MainAdapter extends RecyclerView.Adapter<MainViewHolder> {
    private List<CostCentersResult> lCostCentersResult;
    private final Context context;

    MainAdapter(Context context){
        this.context = context;
    }

    void atualizarLista(List<CostCentersResult> lCostCentersResult){
        this.lCostCentersResult = lCostCentersResult;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MainViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_main, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MainViewHolder holder, int position) {
        final CostCentersResult costCentersResult = lCostCentersResult.get(position);


        holder.txtEmpreendimento.setText(costCentersResult.name);
        holder.txtEndereco.setText(costCentersResult.endereco);
        holder.txtResponsavelTecnico.setText(String.format("Responsavel tecnico: %s", costCentersResult.responsavel));


        holder.itemView.setOnClickListener(view -> {
            Intent i = new Intent(context, DetalhesActivity.class);
            i.putExtra(DetalhesActivity.EMPREENDIMENTO, costCentersResult);
            context.startActivity(i);
        });
    }

    @Override
    public int getItemCount() {
        return lCostCentersResult != null ? lCostCentersResult.size() : 0;
    }
}
