package ucs.android.aulas.trabalho01_v2.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import ucs.android.aulas.trabalho01_v2.R;
import ucs.android.aulas.trabalho01_v2.model.Mesa;
import ucs.android.aulas.trabalho01_v2.model.Pedido;

public class MesasAdapter extends ArrayAdapter<Mesa> {
    private final Context context;
    private final ArrayList<Mesa> elementos;

    public MesasAdapter(Context context, ArrayList<Mesa> elementos) {
        super(context, R.layout.linhamesa, elementos);
        this.context = context;
        this.elementos = elementos;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.linhamesa, parent, false);
        TextView nomemesa = (TextView) rowView.findViewById(R.id.txtNomeMesa);
        nomemesa.setText(Integer.toString(elementos.get(position).getId()));


        return rowView;
    }
}
