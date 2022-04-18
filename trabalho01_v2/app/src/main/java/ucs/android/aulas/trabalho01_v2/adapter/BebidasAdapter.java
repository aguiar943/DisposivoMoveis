package ucs.android.aulas.trabalho01_v2.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import ucs.android.aulas.trabalho01_v2.R;
import ucs.android.aulas.trabalho01_v2.model.Bebida;
import ucs.android.aulas.trabalho01_v2.model.Mesa;
import ucs.android.aulas.trabalho01_v2.model.Produto;

public class BebidasAdapter extends ArrayAdapter<Produto> {
    private final Context context;
    private final ArrayList<Produto> elementos;

    public BebidasAdapter(Context context, ArrayList<Produto> elementos) {
        super(context, R.layout.linhaproduto, elementos);
        this.context = context;
        this.elementos = elementos;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.linhaproduto, parent, false);
        TextView nomebebidas = (TextView) rowView.findViewById(R.id.txtNomeItem);
        nomebebidas.setText((elementos.get(position).getNomeProduto()));

        return rowView;
    }
}
