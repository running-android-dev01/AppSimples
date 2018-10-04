package com.moraes.igor.appsimples;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.moraes.igor.appsimples.model.Unidades;

import java.util.List;
import java.util.Locale;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

class DetalhesClienteAdapter extends RecyclerView.Adapter<DetalhesClienteViewHolder> {
    //private final Context context;
    private List<Unidades> lUnidades;
    private final Locale current;

    DetalhesClienteAdapter(Context context) {
        //this.context = context;
        this.current = context.getApplicationContext().getResources().getConfiguration().locale;
    }

    void atualizarLista(List<Unidades> lUnidades) {
        this.lUnidades = lUnidades;
        notifyDataSetChanged();
    }

    @Override
    @NonNull
    public DetalhesClienteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        DetalhesClienteViewHolder viewHolder;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        View v1 = inflater.inflate(R.layout.adapter_detalhes_cliente, parent, false);
        viewHolder = new DetalhesClienteViewHolder(v1);

        return viewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull DetalhesClienteViewHolder viewHolder, int position) {
        final Unidades unidades = lUnidades.get(position);

        viewHolder.txtApto.setText(unidades.unidade);
        viewHolder.txtContrato.setText(String.format(current, "%.2f", unidades.contrato));
        viewHolder.txtReceber.setText(String.format(current, "%.2f", unidades.recebido));
        viewHolder.txtRecebido.setText(String.format(current, "%.2f", unidades.aReceber));
        viewHolder.txtPercentRecebido.setText(String.format(current, "%.2f", unidades.percentualRecebido));
        viewHolder.txtSituacao.setText(unidades.situacao);
    }

    @Override
    public int getItemCount() {
        return lUnidades != null ? lUnidades.size() : 0;
    }
}
