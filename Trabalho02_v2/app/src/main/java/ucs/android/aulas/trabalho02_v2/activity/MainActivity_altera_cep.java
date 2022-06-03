package ucs.android.aulas.trabalho02_v2.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import ucs.android.aulas.trabalho02_v2.DAO.BDSQLiteHelper;
import ucs.android.aulas.trabalho02_v2.R;
import ucs.android.aulas.trabalho02_v2.model.Json;

public class MainActivity_altera_cep extends AppCompatActivity {
    private BDSQLiteHelper bd;
    private  String CodigoCep;
    private  EditText cep, logradouro, complemento, bairro, uf, ibge;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_altera_cep);

        Intent intent = getIntent();
        CodigoCep = intent.getStringExtra("CODIGOCEP");
        bd = new BDSQLiteHelper(this);
        Json json = bd.getCeps(CodigoCep);

        cep         = (EditText) findViewById(R.id.TvEdtCep);
        logradouro  = (EditText) findViewById(R.id.TvEdtLogradouro);
        complemento = (EditText) findViewById(R.id.TvEdtComplemento);
        bairro      = (EditText) findViewById(R.id.TvEdtBairro);
        uf          = (EditText) findViewById(R.id.TvEdtUF);
        ibge        = (EditText) findViewById(R.id.TvEdtIBGE);

        cep.setText(json.getCep());
        logradouro.setText(json.getLogradouro());
        complemento.setText((json.getComplemento()));
        bairro.setText(String.valueOf(json.getBairro()));
        uf.setText(String.valueOf(json.getUf()));
        ibge.setText(String.valueOf(json.getIbge()));
    }


    public void AcaoBotao(View view){
        switch (view.getId()) {
            case (R.id.BtnSalvar):
                AlteraCep();
                break;
            case (R.id.BtnCancelar):
                finish();
                break;
            case (R.id.BtnRemover):
                mostraAlerta();
                break;
        }
    }

    private void mostraAlerta() {
        AlertDialog.Builder ConfirmaItem = new AlertDialog.Builder(MainActivity_altera_cep.this);
        ConfirmaItem.setTitle("Atenção!");
        ConfirmaItem.setMessage("Confirma Exclusão do CEP?");
        ConfirmaItem.setCancelable(false);
        ConfirmaItem.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which ) {
                Json json = new Json();
                json.setCep(CodigoCep);
                bd.deletaCep(json);
                Toast.makeText(MainActivity_altera_cep.this, "CEP removido", Toast.LENGTH_SHORT).show();

                Intent intent1 = new Intent(MainActivity_altera_cep.this, MainActivity.class);
                startActivity(intent1);
            }
        });
        ConfirmaItem.setNegativeButton("Não",null);
        ConfirmaItem.create().show();
    }

    private void AlteraCep(){
        Json json = new Json();
        json.setCep(CodigoCep);

        json.setCep(cep.getText().toString());
        json.setLogradouro(logradouro.getText().toString());
        json.setComplemento(complemento.getText().toString());
        json.setBairro(bairro.getText().toString());
        json.setUf(uf.getText().toString());
        json.setIbge(ibge.getText().toString());

        bd.updateCeps(json);

        Intent intent1 = new Intent(MainActivity_altera_cep.this, MainActivity.class);
        startActivity(intent1);
    }
}