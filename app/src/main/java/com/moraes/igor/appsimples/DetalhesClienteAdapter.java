package com.moraes.igor.appsimples;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.moraes.igor.appsimples.mode.Cliente;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class DetalhesClienteAdapter extends RecyclerView.Adapter<DetalhesClienteViewHolder> {
    private final Context context;
    private List<Cliente> lCliente;

    DetalhesClienteAdapter(Context context) {
        this.context = context;
    }

    public void atualizarLista(List<Cliente> lCliente) {
        this.lCliente = lCliente;
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
        final Cliente cliente = lCliente.get(position);

        viewHolder.txtApto.setText(cliente.apto);
        viewHolder.txtContrato.setText(cliente.contrato);
        viewHolder.txtReceber.setText(cliente.receber);
        viewHolder.txtRecebido.setText(cliente.recebido);
        viewHolder.txtPercentRecebido.setText(cliente.percentRecebido);
        viewHolder.txtSituacao.setText(cliente.situacao);
    }

    @Override
    public int getItemCount() {
        return lCliente != null ? lCliente.size() : 0;
    }
}
