package ucs.android.aulas.trabalho01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Mesa extends AppCompatActivity {
    private int num;
    private String sIDPedido, snomemesa;

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mesas);

        TextView tvResp = findViewById(R.id.tvpedido);
        Intent intent = getIntent();
        sIDPedido = intent.getStringExtra("PegaIDPedido");
        tvResp.setText("PEDIDO " + sIDPedido);

        ocultamesas();
    }

    public void btnmesaapertada(View view){
        Intent intent = new Intent(Mesa.this, Produto.class);
        Button b = (Button) view;

        snomemesa = b.getText().toString();
        int nro = Integer.parseInt(snomemesa);
        intent.putExtra("PegaNomeMesa",snomemesa);
        intent.putExtra("PegaIDPedido",sIDPedido);

        Banco bd = Banco.getInstancia();
//        Mesa mesasTemp = bd.getMesa(nro);
//        if (mesasTemp.equals(null)) {
//            Mesa mesas = new Mesa();
//            mesas.setNum(nro);
//            bd.setMesa(mesas);
//        }
        Mesa mesas = new Mesa();
        mesas.setNum(nro);
        bd.setMesa(mesas);

        startActivity(intent);
    }

    public void ocultamesas(){
        Banco bd = Banco.getInstancia();
        for (int i=1;i<45;i++) {
            if (bd.getMesa(i) != null){
                String buttonID = "btnmesa0" + i;
                int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
                Button c = ((Button) findViewById(resID));
                c.setVisibility(View.INVISIBLE);
            }
        }
    }

}