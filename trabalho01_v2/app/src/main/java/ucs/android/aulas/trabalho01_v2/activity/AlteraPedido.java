package ucs.android.aulas.trabalho01_v2.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import ucs.android.aulas.trabalho01_v2.R;
import ucs.android.aulas.trabalho01_v2.adapter.BebidasAdapter;
import ucs.android.aulas.trabalho01_v2.adapter.PedidosAdapter;
import ucs.android.aulas.trabalho01_v2.adapter.ProdutosAdapter;
import ucs.android.aulas.trabalho01_v2.banco.BancoDados;
import ucs.android.aulas.trabalho01_v2.databinding.FragmentFirst2Binding;
import ucs.android.aulas.trabalho01_v2.model.Pedido;
import ucs.android.aulas.trabalho01_v2.model.Produto;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class AlteraPedido extends AppCompatActivity {
    ArrayList<Pedido> listaPedidos;

    ArrayList<Produto> listaProdutos;
    private AlteraPedido binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_altera_pedido);
        Intent intent = getIntent();
        ListView lista = findViewById(R.id.lvItens);
        final int id = intent.getIntExtra("ID", 0);

        listaPedidos =   BancoDados.getInstancia().getAllPedidos();

        ArrayList<Produto> listaPedidos1 = new ArrayList<>();

        listaPedidos1.clear();

        for (Pedido p : listaPedidos) {
            if (p.getId() == id) {
                p.getPedidoProdutos(id);
                listaPedidos1.add(p.getPedidoProdutos(id));
            }
        }
        ProdutosAdapter adapter = new ProdutosAdapter(getBaseContext(), listaPedidos1);
        lista.setAdapter(adapter);
    }
}