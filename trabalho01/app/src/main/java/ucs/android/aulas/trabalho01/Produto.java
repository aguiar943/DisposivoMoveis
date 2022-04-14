package ucs.android.aulas.trabalho01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Produto extends AppCompatActivity {
    private int id;
    private String nome, snomemesa, sIDPedido, snomeproduto;
    private double valor;
    private int qtd;
    private Mesa mesa;

    public Produto() {}

    public void setProduto(String nome, double valor, int qtd) {
        //super();
        this.nome = nome;
        this.valor = valor;
        this.qtd = qtd;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public int getQtd() {
        return qtd;
    }

    public void setQtd(int qtd) {
        this.qtd = qtd;
    }

    public void setMesa(Mesa mesa) {
        this.mesa = mesa;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produtos);

        TextView tvResp = findViewById(R.id.idproduto);
        TextView tvResp1 = findViewById(R.id.tvpedido);
        Intent intent = getIntent();
        snomemesa = intent.getStringExtra("PegaNomeMesa");
        tvResp.setText("MESA " + snomemesa);

        sIDPedido = intent.getStringExtra("PegaIDPedido");
        tvResp1.setText("PEDIDO " + sIDPedido);
        findViewById(R.id.linearbebidas).setVisibility(View.INVISIBLE);
        findViewById(R.id.linearcomidas).setVisibility(View.INVISIBLE);
    }

    public void botaoapertado (View view) {
        switch (view.getId()) {
            case (R.id.BtnBebidas):
                findViewById(R.id.linearbebidas).setVisibility(View.VISIBLE);
                findViewById(R.id.linearcomidas).setVisibility(View.INVISIBLE);
                findViewById(R.id.linearbebidas).setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                findViewById(R.id.linearcomidas).setLayoutParams(new LinearLayout.LayoutParams(0, 0));
                break;
            case (R.id.BtnComida):
                findViewById(R.id.linearbebidas).setVisibility(View.INVISIBLE);
                findViewById(R.id.linearcomidas).setVisibility(View.VISIBLE);
                findViewById(R.id.linearcomidas).setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                findViewById(R.id.linearbebidas).setLayoutParams(new LinearLayout.LayoutParams(0, 0));
                break;
        }
    }

    public void clickteste(View view){
        Intent intent = new Intent(Produto.this,SelecionaProduto.class);
        TextView b = (TextView) view;
        snomeproduto = b.getText().toString();

        Banco bd = Banco.getInstancia();

        intent.putExtra("PegaNomeProduto", snomeproduto);
        intent.putExtra("PegaNomeMesa",snomemesa);
        intent.putExtra("PegaIDPedido",sIDPedido);
//        bd.getMesa(Integer.parseInt(sIDPedido));
        startActivity(intent);
    }
    public void btnclickfinaliza(View view){

        Intent intent = new Intent(Produto.this,Pedido.class);
        intent.putExtra("PegaInicio", false);
        intent.putExtra("PegaIDPedido",Integer.parseInt(sIDPedido));
        startActivity(intent);
    }
}