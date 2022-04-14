package ucs.android.aulas.trabalho01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Pedido extends AppCompatActivity {
    private int id;
    private Mesa mesa;
    private Produto produtos;
    private boolean binicio;

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
        Intent intent = getIntent();

        binicio = intent.getBooleanExtra("PegaInicio",true);

        if (binicio) {
            id = 0;
        } else {
            ListView listview = (ListView) findViewById(R.id.lvlistapedidos);
            id = intent.getIntExtra("PegaIDPedido",0);
            Banco bd = Banco.getInstancia();
            List<String> dados = new ArrayList<String>();
            for (int i=0;i<45;i++) {
                if (bd.getMesa(i) != null){
                    dados.add("Mesa: " + String.valueOf(i) + " Pedido: " + String.valueOf(id) );
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, dados);
                listview.setAdapter(adapter);
            }
        }
    }

    public void onclickpedidos(View view){
        Intent intent = new Intent(Pedido.this, Mesa.class);

        id++;

        intent.putExtra("PegaIDPedido", String.valueOf(id));

        startActivity(intent);
    }
}