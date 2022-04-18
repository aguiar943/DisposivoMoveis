package ucs.android.aulas.trabalho01_v2.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;


import java.util.ArrayList;

import ucs.android.aulas.trabalho01_v2.R;
import ucs.android.aulas.trabalho01_v2.adapter.BebidasAdapter;
import ucs.android.aulas.trabalho01_v2.adapter.MesasAdapter;
import ucs.android.aulas.trabalho01_v2.banco.BancoDados;
import ucs.android.aulas.trabalho01_v2.databinding.FragmentFirst3Binding;
import ucs.android.aulas.trabalho01_v2.model.Bebida;
import ucs.android.aulas.trabalho01_v2.model.Mesa;
import ucs.android.aulas.trabalho01_v2.model.Produto;

public class First3Fragment extends Fragment {

    private FragmentFirst3Binding binding;
    ArrayList<Produto> listaProdutos;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = FragmentFirst3Binding.inflate(inflater, container, false);
        listaProdutos =   BancoDados.getInstancia().getAllProdutosBebidas("");

        return binding.getRoot();

    }

    public void onStart() {
        super.onStart();



        ListView lista = binding.lvItens;

        BebidasAdapter adapter = new BebidasAdapter(getContext(), listaProdutos);
        lista.setAdapter(adapter);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Intent intent = new Intent(getContext(), ProdutoActivity.class);
                intent.putExtra("ID", listaProdutos.get(position).getId());
//                intent.putExtra("LivroCorrente", listaPedidos.get(position) );

                startActivity(intent);
            }
        });
    }
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

       
    }
    public void onClick(final View v) { //check for what button is pressed
        switch (v.getId()) {
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}