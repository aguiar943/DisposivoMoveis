package ucs.android.aulas.trabalho01_v2.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import ucs.android.aulas.trabalho01_v2.R;
import ucs.android.aulas.trabalho01_v2.banco.BancoDados;
import ucs.android.aulas.trabalho01_v2.model.Bebida;
import ucs.android.aulas.trabalho01_v2.model.Mesa;
import ucs.android.aulas.trabalho01_v2.model.Pedido;
import ucs.android.aulas.trabalho01_v2.model.PedidoItem;
import ucs.android.aulas.trabalho01_v2.model.Produto;

public class LancaItemActivity2 extends AppCompatActivity {
    private TextView qtd;
    private int contador, idMesa, iIDPedido;
    private String lanche, snomemesa, sIDPedido, snomeproduto;
    private String nro;
    private float fvalor;
    ArrayList<Produto> listaProdutos;
    ArrayList<Pedido> listaPedidos;
    ArrayList<Mesa> listaMesas;
    ArrayList<PedidoItem> listaPedidoItem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lanca_item2);

        TextView tvResp = findViewById(R.id.idproduto);
        TextView tvResp1 = findViewById(R.id.tvpedido);
        ImageView img    = findViewById(R.id.Imgitem);
        TextView tvResp2 = findViewById(R.id.tvtexto);
        TextView tvResp3 = findViewById(R.id.tvpreco);
        contador =  0;
        qtd = (TextView) findViewById(R.id.edqtd);
        mostraResultado(contador);

        Intent intent = getIntent();
        final int id = intent.getIntExtra("ID", 0);
        fvalor = intent.getFloatExtra("ValorProduto",0);
        final int img1 = intent.getIntExtra("IdImagem",0);
        img.setBackgroundResource(img1);

        iIDPedido = intent.getIntExtra("IDPedido", 0);
        snomeproduto = intent.getStringExtra("NomeProduto");
        idMesa = intent.getIntExtra("IDMesa",0);

        tvResp.setText(snomeproduto);
        tvResp2.setText(intent.getStringExtra("QtdServem"));
        tvResp3.setText("R$ " + String.format("%.2f", fvalor));
        tvResp1.setText("PEDIDO " + iIDPedido + " - MESA " + idMesa);
    }

    public void acaoIncrementar(View view){

        mostraResultado(++contador);
    }
    public void mostraResultado(int nro){

        qtd.setText(new Integer(contador).toString());
    }
    public void acaodiminui(View view){
        if (contador>0){
            mostraResultado(--contador);
        }
    }

    public void confirmaitem(View view){
        if (contador == 0) {
            Toast.makeText(LancaItemActivity2.this, "Falta informar quantidade", Toast.LENGTH_SHORT).show();
        } else {
            mostraAlerta();
        }
    }

    private void mostraAlerta() {
        AlertDialog.Builder ConfirmaItem = new AlertDialog.Builder(LancaItemActivity2.this);
        ConfirmaItem.setTitle("Atenção!");
        ConfirmaItem.setMessage("Confirma lançamento do item?");
        ConfirmaItem.setCancelable(false);
        ConfirmaItem.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which ) {
                Toast.makeText(LancaItemActivity2.this, "Produto lançado", Toast.LENGTH_SHORT).show();

                BancoDados bd = BancoDados.getInstancia();
                ArrayList<Pedido> listaPedidos = new ArrayList<>();
                ArrayList<Mesa> listaMesa = new ArrayList<>();

                PedidoItem pedidoitem = new PedidoItem(contador);

                Produto produto = new Produto(1, snomeproduto, "", fvalor, null, null, 0,pedidoitem);
                Mesa mesa = new Mesa(idMesa,true);
                Pedido pedido = new Pedido(iIDPedido, false, produto, mesa);

                listaPedidos.add(new Pedido(iIDPedido, false, produto, mesa));

                bd.setPedido(pedido);

                listaMesa.add(new Mesa(iIDPedido, true));
                bd.setMesa(mesa);
                finish();
            }
        });
        ConfirmaItem.setNegativeButton("Não",null);
        ConfirmaItem.create().show();
    }
}