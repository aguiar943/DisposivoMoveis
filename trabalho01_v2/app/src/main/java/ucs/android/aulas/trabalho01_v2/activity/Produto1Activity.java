package ucs.android.aulas.trabalho01_v2.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.appbar.AppBarLayout;

import java.util.ArrayList;

import ucs.android.aulas.trabalho01_v2.R;
import ucs.android.aulas.trabalho01_v2.adapter.BebidasAdapter;
import ucs.android.aulas.trabalho01_v2.adapter.PedidosAdapter;
import ucs.android.aulas.trabalho01_v2.banco.BancoDados;
import ucs.android.aulas.trabalho01_v2.model.Pedido;
import ucs.android.aulas.trabalho01_v2.model.Produto;

public class Produto1Activity extends AppCompatActivity {
    ArrayList<Produto> listaProdutos;
    ArrayList<Pedido> listaPedidos;
    private int idMesa, iIDPedido;
    private boolean binicio;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produto1);

        TextView tvResp = findViewById(R.id.idproduto);
        TextView tvResp1 = findViewById(R.id.tvpedido);
        Intent intent = getIntent();
        binicio = intent.getBooleanExtra("PegaInicio",true);
        listaPedidos = BancoDados.getInstancia().getAllPedidosCT();

        if (listaPedidos.size() > 0 ){
            for (Pedido p : listaPedidos) {
                iIDPedido = p.getId();
            }
        } else
        {
            iIDPedido = 0;
        }

        iIDPedido++;
        tvResp.setText("MESA " + intent.getStringExtra("IDMesa"));
        tvResp1.setText("PEDIDO " + intent.getStringExtra("IDPedido"));
        idMesa = intent.getIntExtra("IDMesa",0);
    }

    public void botaoapertado (View view) {
        ListView lista = findViewById(R.id.lvItens);

        switch (view.getId()) {
            case (R.id.BtnBebidas):
                listaProdutos =   BancoDados.getInstancia().getAllProdutosBebidas("B");

                lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view,
                                            int position, long id) {
                        Intent intent = new Intent(Produto1Activity.this, LancaItemActivity2.class);
                        intent.putExtra("NomeProduto", listaProdutos.get(position).getNomeProduto());
                        intent.putExtra("IdImagem", listaProdutos.get(position).getid_imagem());
                        intent.putExtra("QtdServem",listaProdutos.get(position).getBebida().getembalagem());
                        intent.putExtra("ValorProduto",listaProdutos.get(position).getPrecoProduto());
                        intent.putExtra("IDMesa", idMesa);
                        intent.putExtra("IDPedido", iIDPedido);
                        startActivity(intent);
                    }
                });
                break;
            case (R.id.BtnComida):
                listaProdutos =   BancoDados.getInstancia().getAllProdutosBebidas("C");

                lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view,
                                            int position, long id) {
                        Intent intent = new Intent(Produto1Activity.this, LancaItemActivity2.class);
                        intent.putExtra("ID", listaProdutos.get(position).getId());
                        intent.putExtra("NomeProduto", listaProdutos.get(position).getNomeProduto());
                        intent.putExtra("IdImagem", listaProdutos.get(position).getid_imagem());
                        intent.putExtra("QtdServem","SERVE: " + listaProdutos.get(position).getPrato().getQtdPessoas() + " PESSOA(S)");
                        intent.putExtra("ValorProduto",listaProdutos.get(position).getPrecoProduto());
                        intent.putExtra("IDMesa", idMesa);
                        intent.putExtra("IDPedido", iIDPedido);
                        startActivity(intent);
                    }
                });
                break;
        }
        BebidasAdapter adapter = new BebidasAdapter(getBaseContext(), listaProdutos);
        lista.setAdapter(adapter);
    }

    public void btnclickfinaliza(View view){

        Intent intent = new Intent(Produto1Activity.this, MainActivity.class);
        intent.putExtra("PegaInicio", false);
        intent.putExtra("PegaIDPedido", (iIDPedido));
        startActivity(intent);
    }
}