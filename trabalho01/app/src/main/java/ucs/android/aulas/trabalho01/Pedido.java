package ucs.android.aulas.trabalho01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Pedido extends AppCompatActivity {
    private int id;
    private Mesa mesa;
    private Produto produtos;

    public Pedido() {}

    public Pedido(Mesa mesa, Produto produtos) {
        super();
        this.mesa = mesa;
        this.produtos = produtos;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedido);
        id = 0;
    }

    public void onclickpedidos(View view){
        Intent intent = new Intent(Pedido.this, Mesa.class);

        id++;

        intent.putExtra("PegaIDPedido", String.valueOf(id));

        startActivity(intent);
    }
}