package ucs.android.aulas.trabalho03_v2.adapter;

import android.annotation.SuppressLint;
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

import ucs.android.aulas.trabalho03_v2.R;
import ucs.android.aulas.trabalho03_v2.activity.MainActivity_chat;
import ucs.android.aulas.trabalho03_v2.model.Usuarios;

public class adapterUsuarios extends RecyclerView.Adapter<adapterUsuarios.PostViewHolder>{

    private List<Usuarios> usuarios;
    private int rowLayout;
    private Context context;

    public static class PostViewHolder extends RecyclerView.ViewHolder {
        LinearLayout postsLayout;
        TextView VarUsuario;
//        TextView VarOnline;


        public PostViewHolder(View v) {
            super(v);
            postsLayout = (LinearLayout) v.findViewById(R.id.IDONLINES);
            VarUsuario = (TextView) v.findViewById(R.id.tvnome);
//            VarOnline = (TextView) v.findViewById(R.id.tvlocalizacao);
//            VarLogradouro = (TextView) v.findViewById(R.id.tvlogradouro);
//            VarUF = (TextView) v.findViewById(R.id.tvuf);
//            VarIBGE = (TextView) v.findViewById(R.id.tvibge);
//            VarComplemento = (TextView) v.findViewById(R.id.tvcomplemento);
        }
    }

    public adapterUsuarios(List<Usuarios> posts, int rowLayout, Context context) {
        this.usuarios = posts;
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
        holder.VarUsuario.setText(usuarios.get(position).getUsuario());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Intent intent = new Intent(context , MainActivity_chat.class);
                intent.putExtra("NomeUsuarioDestino", usuarios.get(position).getUsuario());

                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return usuarios.size();
    }

}
