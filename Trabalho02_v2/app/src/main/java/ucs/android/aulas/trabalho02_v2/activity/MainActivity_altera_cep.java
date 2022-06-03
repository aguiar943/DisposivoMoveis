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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_altera_cep);

        Intent intent = getIntent();
        final String CodigoCep = intent.getStringExtra("CODIGOCEP");
        bd = new BDSQLiteHelper(this);
        Json json = bd.getCeps(CodigoCep);

        final EditText cep         = (EditText) findViewById(R.id.TvEdtCep);
        final EditText logradouro  = (EditText) findViewById(R.id.TvEdtLogradouro);
        final EditText complemento = (EditText) findViewById(R.id.TvEdtComplemento);
        final EditText bairro      = (EditText) findViewById(R.id.TvEdtBairro);
        final EditText uf          = (EditText) findViewById(R.id.TvEdtUF);
        final EditText ibge        = (EditText) findViewById(R.id.TvEdtIBGE);

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
                Toast.makeText(this, "Salvar", Toast.LENGTH_SHORT).show();
                break;
            case (R.id.BtnCancelar):
                finish();
                Toast.makeText(this, "Cancelar", Toast.LENGTH_SHORT).show();
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
                Toast.makeText(MainActivity_altera_cep.this, "CEP removido", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
        ConfirmaItem.setNegativeButton("Não",null);
        ConfirmaItem.create().show();
    }
}