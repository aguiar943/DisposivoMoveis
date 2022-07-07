package ucs.android.aulas.trabalho03_v2.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ucs.android.aulas.trabalho03_v2.R;
import ucs.android.aulas.trabalho03_v2.model.Conversas;

public class adapterMSG extends RecyclerView.Adapter<adapterMSG.PostViewHolder>{

    private List<Conversas> conversa;
    private int rowLayout;
    private Context context;

    public static class PostViewHolder extends RecyclerView.ViewHolder {
        LinearLayout postsLayout;
        TextView VarNome;
        TextView VarMSG;


        public PostViewHolder(View v) {
            super(v);
            postsLayout = (LinearLayout) v.findViewById(R.id.IDCONVERSAS);
            VarNome = (TextView) v.findViewById(R.id.tvnomechat);
            VarMSG = (TextView) v.findViewById(R.id.tvmensagens);

        }
    }

    public adapterMSG(List<Conversas> posts, int rowLayout, Context context) {
        this.conversa = posts;
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
    public void onBindViewHolder(@NonNull final PostViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.VarNome.setText(conversa.get(position).getMsg());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return conversa.size();
    }
}
