package com.moraes.igor.appsimples;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.moraes.igor.appsimples.Json.EnterprisesResult;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

class MainAdapter extends RecyclerView.Adapter<MainViewHolder> {
    private List<EnterprisesResult> lEnterprisesResult;
    private final Context context;

    MainAdapter(Context context){
        this.context = context;
    }

    void atualizarLista(List<EnterprisesResult> lEnterprisesResult){
        this.lEnterprisesResult = lEnterprisesResult;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MainViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_main, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MainViewHolder holder, int position) {
        final EnterprisesResult enterprisesResult = lEnterprisesResult.get(position);


        holder.txtEmpreendimento.setText(enterprisesResult.name);
        holder.txtEndereco.setText(enterprisesResult.adress);

        //holder.txtResponsavelTecnico.setText(String.format("Responsavel tecnico: %s", costCentersResult.responsavel));
        holder.txtResponsavelTecnico.setText("");
        if (enterprisesResult.constructionDetails!=null && !TextUtils.isEmpty(enterprisesResult.constructionDetails.technicalManager)){
            holder.txtResponsavelTecnico.setText(String.format("Responsavel tecnico: %s", enterprisesResult.constructionDetails.technicalManager));
        }


        holder.itemView.setOnClickListener(view -> {
            Intent i = new Intent(context, DetalhesActivity.class);
            i.putExtra(DetalhesActivity.EMPREENDIMENTO, enterprisesResult);
            context.startActivity(i);
        });
    }

    @Override
    public int getItemCount() {
        return lEnterprisesResult != null ? lEnterprisesResult.size() : 0;
    }
}
