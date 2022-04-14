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
        for (int i=0;i<45;i++) {
            if (bd.getMesa(i) != null){
                if (i == 1)
                    findViewById(R.id.btnmesa01).setVisibility(View.INVISIBLE);
                if (i == 2)
                    findViewById(R.id.btnmesa02).setVisibility(View.INVISIBLE);
                if (i == 4)
                    findViewById(R.id.btnmesa04).setVisibility(View.INVISIBLE);
                if (i == 5)
                    findViewById(R.id.btnmesa05).setVisibility(View.INVISIBLE);
                if (i == 6)
                    findViewById(R.id.btnmesa06).setVisibility(View.INVISIBLE);
                if (i == 7)
                    findViewById(R.id.btnmesa07).setVisibility(View.INVISIBLE);
                if (i == 8)
                    findViewById(R.id.btnmesa08).setVisibility(View.INVISIBLE);
                if (i == 9)
                    findViewById(R.id.btnmesa09).setVisibility(View.INVISIBLE);
                if (i == 10)
                    findViewById(R.id.btnmesa10).setVisibility(View.INVISIBLE);
                if (i == 11)
                    findViewById(R.id.btnmesa11).setVisibility(View.INVISIBLE);
                if (i == 12)
                    findViewById(R.id.btnmesa12).setVisibility(View.INVISIBLE);
                if (i == 13)
                    findViewById(R.id.btnmesa13).setVisibility(View.INVISIBLE);
                if (i == 14)
                    findViewById(R.id.btnmesa14).setVisibility(View.INVISIBLE);
                if (i == 15)
                    findViewById(R.id.btnmesa15).setVisibility(View.INVISIBLE);
                if (i == 16)
                    findViewById(R.id.btnmesa16).setVisibility(View.INVISIBLE);
                if (i == 17)
                    findViewById(R.id.btnmesa17).setVisibility(View.INVISIBLE);
                if (i == 18)
                    findViewById(R.id.btnmesa18).setVisibility(View.INVISIBLE);
                if (i == 19)
                    findViewById(R.id.btnmesa19).setVisibility(View.INVISIBLE);
                if (i == 20)
                    findViewById(R.id.btnmesa20).setVisibility(View.INVISIBLE);
                if (i == 21)
                    findViewById(R.id.btnmesa21).setVisibility(View.INVISIBLE);
                if (i == 22)
                    findViewById(R.id.btnmesa22).setVisibility(View.INVISIBLE);
                if (i == 23)
                    findViewById(R.id.btnmesa23).setVisibility(View.INVISIBLE);
            }
        }
    }

}