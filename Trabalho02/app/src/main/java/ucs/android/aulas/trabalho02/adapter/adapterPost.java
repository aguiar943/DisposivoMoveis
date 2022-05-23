package ucs.android.aulas.trabalho02.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ucs.android.aulas.trabalho02.R;
import ucs.android.aulas.trabalho02.model.Example;

public class adapterPost extends RecyclerView.Adapter<adapterPost.PostViewHolder> {
    private List<Example> examples;
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
            postsLayout = (LinearLayout) v.findViewById(R.id.idposts);
            userId = (TextView) v.findViewById(R.id.userId);
            id = (TextView) v.findViewById(R.id.id);
            title = (TextView) v.findViewById(R.id.title);
            body = (TextView) v.findViewById(R.id.body);
        }
    }

    public adapterPost(List<Example> examples, int rowLayout, Context context) {
        this.examples = examples;
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
        holder.userId.setText(examples.get(position).getId());
        holder.id.setText(examples.get(position).getName());
        holder.title.setText(examples.get(position).getName());
        holder.body.setText(examples.get(position).getPowerstats().getCombat());
    }

    @Override
    public int getItemCount() {
        return examples.size();
    }
}
