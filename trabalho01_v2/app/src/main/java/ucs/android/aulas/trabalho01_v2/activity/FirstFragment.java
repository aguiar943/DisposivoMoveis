package ucs.android.aulas.trabalho01_v2.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

import ucs.android.aulas.trabalho01_v2.adapter.PedidosAdapter;
import ucs.android.aulas.trabalho01_v2.databinding.FragmentFirstBinding;
import ucs.android.aulas.trabalho01_v2.banco.BancoDados;
import ucs.android.aulas.trabalho01_v2.model.Pedido;

public class FirstFragment extends Fragment {

    private FragmentFirstBinding binding;
    ArrayList<Pedido> listaPedidos;


    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentFirstBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    @Override
    public void onStart() {
        super.onStart();

       listaPedidos = BancoDados.getInstancia().getPedidos();

        ListView lista = binding.lvPedidos;

        PedidosAdapter adapter = new PedidosAdapter(getContext(), listaPedidos);
        lista.setAdapter(adapter);
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Intent intent = new Intent(getContext(), AlteraPedido.class);
                intent.putExtra("ID", listaPedidos.get(position).getId());
                intent.putExtra("IDMesa", listaPedidos.get(position).getMesaid().getId());
                startActivity(intent);
            }
        });
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}