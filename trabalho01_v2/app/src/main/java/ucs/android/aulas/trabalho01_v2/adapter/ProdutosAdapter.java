package ucs.android.aulas.trabalho01_v2.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import ucs.android.aulas.trabalho01_v2.R;

import ucs.android.aulas.trabalho01_v2.model.Produto;

public class ProdutosAdapter extends ArrayAdapter<Produto> {

    private final Context context;
    private final ArrayList<Produto> elementos;
    private float rvalor;


    public ProdutosAdapter(Context context, ArrayList<Produto> elementos) {
        super(context, R.layout.linhaproduto, elementos);
        this.context = context;
        this.elementos = elementos;
    }
    public void removeProduto(int posicao){
        this.elementos.remove(posicao);
        notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.linhaproduto, parent, false);
        TextView nomeitem = (TextView) rowView.findViewById(R.id.txtNomeItem);
        TextView precoproduto = (TextView) rowView.findViewById(R.id.txtPrecoProduto);
        TextView excluir = (TextView) rowView.findViewById(R.id.tvexcluir);
        rvalor = elementos.get(position).getpedidoitem().getId() * elementos.get(position).getPrecoProduto() ;
        nomeitem.setText(elementos.get(position).getNomeProduto());
        excluir.setText("Excluir");
        precoproduto.setText( (Integer.toString(elementos.get(position).getpedidoitem().getId()) + " UN - ") +
                "R$ " + String.format("%.2f", rvalor));
        return rowView;
    }


}

