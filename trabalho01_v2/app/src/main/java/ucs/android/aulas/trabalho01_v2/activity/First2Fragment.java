package ucs.android.aulas.trabalho01_v2.activity;
// TELA MESAS
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

import ucs.android.aulas.trabalho01_v2.adapter.MesasAdapter;
import ucs.android.aulas.trabalho01_v2.adapter.ProdutosAdapter;
import ucs.android.aulas.trabalho01_v2.databinding.FragmentFirst2Binding;
import ucs.android.aulas.trabalho01_v2.banco.BancoDados;
import ucs.android.aulas.trabalho01_v2.model.Mesa;
import ucs.android.aulas.trabalho01_v2.model.Pedido;
import ucs.android.aulas.trabalho01_v2.model.Produto;

public class First2Fragment extends Fragment {

    private FragmentFirst2Binding binding;
    ArrayList<Mesa> listaMesas;
    ArrayList<Pedido> listaPedidos;
    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentFirst2Binding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onStart() {
        super.onStart();

        listaPedidos =   BancoDados.getInstancia().getAllPedidos();

//        listaMesas.clear();
//        for (Pedido p : listaPedidos) {
//            if (p.getId() == id) {
//                p.getPedidoProdutos(id);
//                listaPedidos1.add(p.getPedidoProdutos(id));
//            }
//        }
//




//        if (listaPedidos.size() > 0){
//            for (Pedido p : listaPedidos) {
//                for (int i = 1; i <= 50; i++) {
//                    if (p.getMesaid(i).getId() != i ) {
//                        listaMesas.add(p.getMesaid(i));
//                    }
//
//
//
//
////                    if (p.getId() != i) {
////                        p.getMesaid(i);
////                        listaMesas.add(p.getMesaid(i));
////                    }
//                }
//            }
//        } else {
            listaMesas =   BancoDados.getInstancia().getAllMesas();
//        }
        ListView lista = binding.lvMesas;

        MesasAdapter adapter = new MesasAdapter(getContext(), listaMesas);
        lista.setAdapter(adapter);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                Intent intent = new Intent(getContext(), Produto1Activity.class);
                intent.putExtra("IDMesa", listaMesas.get(position).getId());
                startActivity(intent);
            }
        });
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

//        binding.buttonFirst.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                NavHostFragment.findNavController(First2Fragment.this)
//                        .navigate(R.id.action_First2Fragment_to_SecondFragment);
//            }
//        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}