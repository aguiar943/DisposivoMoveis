package ucs.android.aulas.trabalho02_v2.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

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
}