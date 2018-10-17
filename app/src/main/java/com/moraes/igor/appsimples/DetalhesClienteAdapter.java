package com.moraes.igor.appsimples;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.moraes.igor.appsimples.model.Unidades;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

class DetalhesClienteAdapter extends RecyclerView.Adapter<DetalhesClienteViewHolder> {
    private final Context context;
    private List<Unidades> lUnidades;
    private final Locale current;
    DecimalFormat dForat = new DecimalFormat(",##0.00");
    DecimalFormat iForat = new DecimalFormat(",##0");

    DetalhesClienteAdapter(Context context) {
        this.context = context;
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
        viewHolder.txtContrato.setTextColor(ContextCompat.getColor(context, unidades.contrato>=0.0?R.color.secondaryTextColor:R.color.thirdTextColor));
        viewHolder.txtContrato.setText(dForat.format(unidades.contrato));
        viewHolder.txtReceber.setTextColor(ContextCompat.getColor(context, unidades.aReceber>=0.0?R.color.secondaryTextColor:R.color.thirdTextColor));
        viewHolder.txtReceber.setText(dForat.format(unidades.aReceber));
        viewHolder.txtRecebido.setTextColor(ContextCompat.getColor(context, unidades.recebido>=0.0?R.color.secondaryTextColor:R.color.thirdTextColor));
        viewHolder.txtRecebido.setText(dForat.format(unidades.recebido));

        viewHolder.txtUltimaRecebido.setText(unidades.ultimaRecebido);

        viewHolder.txtAtraso.setTextColor(ContextCompat.getColor(context, unidades.atraso>=0.0?R.color.secondaryTextColor:R.color.thirdTextColor));
        viewHolder.txtAtraso.setText(dForat.format(unidades.atraso));

        if (unidades.qtdAtraso>0){
            viewHolder.txtQuantidade.setText(iForat.format(unidades.qtdAtraso));
        }else{
            viewHolder.txtQuantidade.setText("");
        }
        viewHolder.txtPercentRecebido.setText(dForat.format(unidades.percentualRecebido));


        viewHolder.txtSituacao.setText(unidades.situacao);
        viewHolder.itemView.setBackgroundColor(ContextCompat.getColor(context, R.color.white));
        if (unidades.contrato>0 && unidades.contrato==unidades.recebido){
            viewHolder.txtSituacao.setText("Quitado");
            viewHolder.itemView.setBackgroundColor(ContextCompat.getColor(context, R.color.lightSkyBlue));
        }else if (unidades.qtdAtraso>0){
            viewHolder.txtSituacao.setText("Atrasado");
            viewHolder.itemView.setBackgroundColor(ContextCompat.getColor(context, R.color.lightSalmon));
        }else if (unidades.contrato>0){
            viewHolder.txtSituacao.setText("OK");
        }else if (unidades.codSituacao.equals("D")){
            viewHolder.itemView.setBackgroundColor(ContextCompat.getColor(context, R.color.lightGreen));
        }

    }

    @Override
    public int getItemCount() {
        return lUnidades != null ? lUnidades.size() : 0;
    }
}
