package ucs.android.aulas.trabalho02_v2.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ucs.android.aulas.trabalho02_v2.R;
import ucs.android.aulas.trabalho02_v2.model.Json;

public class adapterCEP  extends RecyclerView.Adapter<adapterCEP.PostViewHolder>{
    private List<Json> json;
    private int rowLayout;
    private Context context;


    public static class PostViewHolder extends RecyclerView.ViewHolder {
        LinearLayout postsLayout;
        TextView id;
        TextView userId;
        TextView title;
        TextView body;


        public PostViewHolder(View v) {
            super(v);
            postsLayout = (LinearLayout) v.findViewById(R.id.idcep);
            userId = (TextView) v.findViewById(R.id.userId);
            id = (TextView) v.findViewById(R.id.id);
            title = (TextView) v.findViewById(R.id.title);
            body = (TextView) v.findViewById(R.id.body);
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
    public void onBindViewHolder(PostViewHolder holder, final int position) {
        holder.userId.setText(json.get(position).getCep());
        holder.id.setText(json.get(position).getBairro());
        holder.title.setText(json.get(position).getLogradouro());
        holder.body.setText(json.get(position).getUf());
    }

    @Override
    public int getItemCount() {
        return json.size();
    }
}
