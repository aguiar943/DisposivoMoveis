package ucs.android.aulas.trabalho02_v2.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ucs.android.aulas.trabalho02_v2.R;
import ucs.android.aulas.trabalho02_v2.activity.MainActivity_altera_cep;
import ucs.android.aulas.trabalho02_v2.model.Json;

public class adapterCEP  extends RecyclerView.Adapter<adapterCEP.PostViewHolder>{
    private List<Json> json;
    private int rowLayout;
    private Context context;


    public static class PostViewHolder extends RecyclerView.ViewHolder {
        LinearLayout postsLayout;
        TextView VarCep;
        TextView VarBairro;
        TextView VarLogradouro;
        TextView VarUF;
//        TextView VarIBGE;


        public PostViewHolder(View v) {
            super(v);
            postsLayout = (LinearLayout) v.findViewById(R.id.idcep);
            VarCep = (TextView) v.findViewById(R.id.tvcep);
            VarBairro = (TextView) v.findViewById(R.id.tvbairro);
            VarLogradouro = (TextView) v.findViewById(R.id.tvlogradouro);
            VarUF = (TextView) v.findViewById(R.id.tvuf);
//            VarIBGE = (TextView) v.findViewById(R.id.tvibge);
        }
    }

    public adapterCEP(List<Json> posts, int rowLayout, Context context) {
        this.json = posts;
        this.rowLayout = rowLayout;
        this.context = context;
    }

    @Override
    public PostViewHolder onCreateViewHolder(ViewGroup parent,
                                             int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new PostViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull final PostViewHolder holder, int position) {
        holder.VarCep.setText(json.get(position).getCep());
        holder.VarBairro.setText(json.get(position).getBairro());
        holder.VarLogradouro.setText(json.get(position).getLogradouro());
        holder.VarUF.setText(json.get(position).getUf());
//        holder.VarIBGE.setText(json.get(position).getIbge());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Intent intent = new Intent(context , MainActivity_altera_cep.class);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return json.size();
    }
}
