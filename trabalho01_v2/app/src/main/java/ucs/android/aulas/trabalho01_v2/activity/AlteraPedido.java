package ucs.android.aulas.trabalho01_v2.activity;

import androidx.appcompat.app.AppCompatActivity;
import ucs.android.aulas.trabalho01_v2.R;
import ucs.android.aulas.trabalho01_v2.adapter.BebidasAdapter;
import ucs.android.aulas.trabalho01_v2.adapter.PedidosAdapter;
import ucs.android.aulas.trabalho01_v2.adapter.ProdutosAdapter;
import ucs.android.aulas.trabalho01_v2.banco.BancoDados;
import ucs.android.aulas.trabalho01_v2.model.Mesa;
import ucs.android.aulas.trabalho01_v2.model.Pedido;
import ucs.android.aulas.trabalho01_v2.model.PedidoItem;
import ucs.android.aulas.trabalho01_v2.model.Produto;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class AlteraPedido extends AppCompatActivity {
    ArrayList<Pedido> listaPedidos;
    private int idaux;
    private float fvalor, fvalorunitario, fvalorpago ;
    ArrayList<Produto> listaProdutos;
    ArrayList<Produto> listaPedidos1 = new ArrayList<>();

    private AlteraPedido binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_altera_pedido);
        Intent intent = getIntent();
        TextView valortaxa = (TextView) findViewById(R.id.Tvtaxaservico);
        ListView lista = findViewById(R.id.lvItens);
        final int id = intent.getIntExtra("ID", 0);
        idaux = id;

        fvalor = 0;
        fvalorpago = 0;
        fvalorunitario = 0;
        listaPedidos =   BancoDados.getInstancia().getAllPedidos();

        listaPedidos1.clear();

        for (Pedido p : listaPedidos) {
            if (p.getId() == id) {
                p.getPedidoProdutos(id);
                listaPedidos1.add(p.getPedidoProdutos(id));
                fvalorunitario = p.getPedidoProdutos(id).getpedidoitem().getId() * p.getPedidoProdutos(id).getPrecoProduto();
                fvalorpago += fvalorunitario;
                fvalor = fvalor +  (fvalorunitario * 10) / 100;
            }
        }
        valortaxa.setText("TAXA DE SERVIÇO(10%): "  + String.format("%.2f", fvalor));

        ProdutosAdapter adapter = new ProdutosAdapter(getBaseContext(), listaPedidos1);
        lista.setAdapter(adapter);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                android.app.AlertDialog.Builder ConfirmaItem = new AlertDialog.Builder(AlteraPedido.this);
                ConfirmaItem.setTitle("Atenção!");
                ConfirmaItem.setMessage("Confirma exclusão do item?");
                ConfirmaItem.setCancelable(false);
                ConfirmaItem.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which ) {
                        Toast.makeText(AlteraPedido.this, "Produto removido", Toast.LENGTH_SHORT).show();

                        adapter.removeProduto(position);
                        recalculatotais();
                    }
                });
                ConfirmaItem.setNegativeButton("Não",null);
                ConfirmaItem.create().show();
            }
        });
    }

    public void recalculatotais(){
        TextView valortaxa = (TextView) findViewById(R.id.Tvtaxaservico);
        fvalor = 0;
        fvalorunitario = 0;
        fvalorpago = 0;
        for(Produto p : listaPedidos1)
        {
            fvalorunitario = p.getPrecoProduto() * p.getpedidoitem().getId();
            fvalorpago += fvalorunitario;
            fvalor = fvalor +  (fvalorunitario * 10) / 100;
            valortaxa.setText("TAXA DE SERVIÇO(10%): "  + String.format("%.2f", fvalor));
        }

    }

    public void finaliza(View view) {
        switch (view.getId()) {
            case (R.id.btnpgtosg):
                fvalorpago -= fvalor;
                break;
            case (R.id.btnpgtocg):
                fvalorpago  +=  fvalor;
                break;
        }
        mostraAlerta();
    }

    private void mostraAlerta() {
        AlertDialog.Builder ConfirmaItem = new AlertDialog.Builder(AlteraPedido.this);
        ConfirmaItem.setTitle("Atenção!");
        ConfirmaItem.setMessage("Confirma pagamento de R$ " + String.format("%.2f", fvalorpago)  + "?");
        ConfirmaItem.setCancelable(false);
        ConfirmaItem.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which ) {
                Toast.makeText(AlteraPedido.this, "Pagamento efetuado", Toast.LENGTH_SHORT).show();

                listaPedidos =  BancoDados.getInstancia().getRemovePedido(idaux);
                finish();
            }
        });
        ConfirmaItem.setNegativeButton("Não",null);
        ConfirmaItem.create().show();
    }

}