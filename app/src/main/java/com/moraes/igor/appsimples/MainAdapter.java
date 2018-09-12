package com.moraes.igor.appsimples;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.moraes.igor.appsimples.mode.Empreendimento;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MainAdapter extends RecyclerView.Adapter<MainViewHolder> {
    private List<Empreendimento> lEmpreendimento;
    private final Context context;


    MainAdapter(Context context){
        this.context = context;
    }

    void atualizarLista(List<Empreendimento> lEmpreendimento){
        this.lEmpreendimento = lEmpreendimento;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MainViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_main, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MainViewHolder holder, int position) {
        final Empreendimento empreendimento = lEmpreendimento.get(position);


        holder.txtEmpreendimento.setText(empreendimento.empreendimento);
        holder.txtEndereco.setText(empreendimento.endereco);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, DetalhesActivity.class);
                i.putExtra(DetalhesActivity.EMPREENDIMENTO, empreendimento);
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return lEmpreendimento != null ? lEmpreendimento.size() : 0;
    }
}
