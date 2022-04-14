package ucs.android.aulas.trabalho01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.app.AlertDialog;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import com.google.android.material.snackbar.Snackbar;

public class SelecionaProduto extends AppCompatActivity {
    private TextView qtd;
    private int contador;
    private String lanche, snomemesa, sIDPedido, snomeproduto;
    private float fvalor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seleciona_produto);

        TextView tvResp = findViewById(R.id.idproduto);
        TextView tvResp1 = findViewById(R.id.tvpedido);

        Intent intent = getIntent();

        snomeproduto = intent.getStringExtra("PegaNomeProduto");
        snomemesa = intent.getStringExtra("PegaNomeMesa");
        sIDPedido = intent.getStringExtra("PegaIDPedido");

        tvResp.setText(snomeproduto);
        tvResp1.setText("PEDIDO " + sIDPedido + " - MESA " + snomemesa);

        contador =  0;
        qtd = (TextView) findViewById(R.id.edqtd);
        mostraResultado(contador);

        lanche = snomeproduto;

        preencheedits();
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
            Toast.makeText(SelecionaProduto.this, "Falta informar quantidade", Toast.LENGTH_SHORT).show();
        } else {
            mostraAlerta();
        }
    }

    private void mostraAlerta() {
        AlertDialog.Builder ConfirmaItem = new AlertDialog.Builder(SelecionaProduto.this);
        ConfirmaItem.setTitle("Atenção!");
        ConfirmaItem.setMessage("Confirma lançamento do item?");
        ConfirmaItem.setCancelable(false);
        ConfirmaItem.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which ) {
                Toast.makeText(SelecionaProduto.this, "Produto lançado", Toast.LENGTH_SHORT).show();

                Banco bd = Banco.getInstancia();
                Produto produto = new Produto();
                Mesa mesa = new Mesa();
                produto.setProduto(snomeproduto,fvalor,contador);
                bd.setProduto(produto);
                mesa.setNum(Integer.parseInt(snomemesa));

                finish();
            }
        });
        ConfirmaItem.setNegativeButton("Não",null);
        ConfirmaItem.create().show();
    }

    public void preencheedits(){
        TextView tvResp1 = findViewById(R.id.tvtexto);
        if (lanche.contains("X-")) {
            findViewById(R.id.Imgitem).setBackgroundResource(R.drawable.xis);
            tvResp1.setText("SERVE 1 PESSOA" + '\n' + "R$ 20,00" );
            fvalor = 20.00f;
        } else {
            if (lanche.contains("ALA")) {
                findViewById(R.id.Imgitem).setBackgroundResource(R.drawable.ala);
                tvResp1.setText("SERVE 1 PESSOA" + '\n' + "R$ 25,00" );
                fvalor = 25.00f;
            } else {
                if (lanche.contains("TORR")) {
                    findViewById(R.id.Imgitem).setBackgroundResource(R.drawable.torrada1);
                    tvResp1.setText("SERVE 1 PESSOA" + '\n' + "R$ 18,00" );
                    fvalor = 18.00f;
                } else {
                    if (lanche.contains("HOT")) {
                        findViewById(R.id.Imgitem).setBackgroundResource(R.drawable.hotdog);
                        tvResp1.setText("SERVE 1 PESSOA" + '\n' + "R$ 25,00" );
                        fvalor = 25.00f;
                    } else {
                        if (lanche.equals("BAURU")) {
                            findViewById(R.id.Imgitem).setBackgroundResource(R.drawable.bauru);
                            tvResp1.setText("SERVE 2 PESSOA" + '\n' + "R$ 35,00" );
                            fvalor = 35.00f;
                        } else {
                            if (lanche.contains("REFRI")) {
                                findViewById(R.id.Imgitem).setBackgroundResource(R.drawable.refrig);
                                tvResp1.setText("R$ 5,00");
                                fvalor = 5.00f;
                            } else {
                                if (lanche.contains("SUC")) {
                                    findViewById(R.id.Imgitem).setBackgroundResource(R.drawable.suco);
                                    tvResp1.setText("R$ 10,00");
                                    fvalor = 10.00f;
                                } else {
                                    if (lanche.equals("CHA")) {
                                        findViewById(R.id.Imgitem).setBackgroundResource(R.drawable.cha);
                                        tvResp1.setText("R$ 8,00");
                                        fvalor = 8.00f;
                                    } else {
                                        if (lanche.contains("ENERG")) {
                                            findViewById(R.id.Imgitem).setBackgroundResource(R.drawable.energeticog);
                                            tvResp1.setText("R$ 15,00");
                                            fvalor = 15.00f;
                                        } else {
                                            if (lanche.contains("AGUA")) {
                                                findViewById(R.id.Imgitem).setBackgroundResource(R.drawable.agua);
                                                tvResp1.setText("R$ 5,00");
                                                fvalor = 5.00f;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}