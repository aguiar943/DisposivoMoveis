package ucs.android.aulas.trabalho01_v2.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import ucs.android.aulas.trabalho01_v2.R;
import ucs.android.aulas.trabalho01_v2.model.Pedido;

public class PedidosAdapter extends ArrayAdapter<Pedido> {

    private final Context context;
    private final ArrayList<Pedido> elementos;

    public PedidosAdapter(Context context, ArrayList<Pedido> elementos) {
        super(context, R.layout.linhapedido, elementos);
        this.context = context;
        this.elementos = elementos;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        notifyDataSetChanged();
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.linhapedido, parent, false);
        TextView nomeitem = (TextView) rowView.findViewById(R.id.txtNomePedido);
        nomeitem.setText(Integer.toString(elementos.get(position).getId()));

        return rowView;
    }
}

