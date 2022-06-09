package ucs.android.aulas.trabalho02_v2.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ucs.android.aulas.trabalho02_v2.DAO.BDSQLiteHelper;
import ucs.android.aulas.trabalho02_v2.R;
import ucs.android.aulas.trabalho02_v2.REST.ApiClient;
import ucs.android.aulas.trabalho02_v2.REST.AppInterface;
import ucs.android.aulas.trabalho02_v2.adapter.adapterCEP;
import ucs.android.aulas.trabalho02_v2.model.Json;

public class MainActivity extends AppCompatActivity {
    private SQLiteDatabase conexao;
    private BDSQLiteHelper bd;
    private RecyclerView recyclerView;
    private  String CodigoCep, campopesquisa, BASE_URL;
    private  EditText pesquisa;
    private int iflat;
    private boolean bconectar, bconectado;
    private Spinner snopcaopesquisa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView conexao = (TextView) findViewById(R.id.TvConexao);
        Switch bconectar = (Switch) findViewById(R.id.swonline);
        BASE_URL = "https://viacep.com.br/ws/RS/Caxias do Sul/Domingos/";
        conexao.setTextColor(getResources().getColor(R.color.colorRedD));
        conexao.setText("DESCONECTADO - CONEXÃO LOCAL");

        campopesquisa = "";
        pesquisa  = (EditText) findViewById(R.id.TvEdtpesquisa);
        snopcaopesquisa = (Spinner) findViewById(R.id.spopcaopesquisa);

        snopcaopesquisa.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                campopesquisa = (snopcaopesquisa.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        bconectar.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(bconectar.isChecked()){
                    if (isOnline()) {
                        bconectado = true;
                        conexao.setTextColor(getResources().getColor(R.color.colorGreenC));
                        conexao.setText("CONECTADO");
                    } else {
                        bconectado = false;
                        bconectar.setChecked(false);
                        conexao.setTextColor(getResources().getColor(R.color.colorRedD));
                        conexao.setText("DESCONECTADO - CONEXÃO LOCAL");
                        Toast.makeText(MainActivity.this, "Verifique sua conexão com a Internet", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    bconectado = false;
                    conexao.setTextColor(getResources().getColor(R.color.colorRedD));
                    conexao.setText("DESCONECTADO - CONEXÃO LOCAL");
                }
            }
        });

        conexao.setTextColor(getResources().getColor(R.color.colorRedD));
        conexao.setText("DESCONECTADO - CONEXÃO LOCAL");

        bd = new BDSQLiteHelper(this);

        criarConexao();

        Intent intent = getIntent();
        iflat = intent.getIntExtra("CodFlat",0);

        recyclerView = (RecyclerView) findViewById(R.id.posts_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        if ((isOnline()) && (bconectar.isChecked())) {
            bconectado = true;
            bconectar.setChecked(true);
            BuscaDados();
        } else {
            bconectado = false;
            bconectar.setChecked(false);
            if (iflat != 3){
                recyclerView.setAdapter(new adapterCEP(bd.getAllCeps(), R.layout.activity_cep, getApplicationContext()));
            }
            if (iflat == 3){
                CodigoCep = intent.getStringExtra("CODIGOCEP");
                campopesquisa = intent.getStringExtra("COLUNAPESQUISA");
                recyclerView.setAdapter(new adapterCEP(bd.getPesquisaCEP(CodigoCep,campopesquisa), R.layout.activity_cep, getApplicationContext()));
            }
        }
    }
    private void criarConexao() {
        try {
            conexao = bd.getWritableDatabase();
        } catch (SQLException ex) {
            Snackbar.make(findViewById(R.id.LayoutInicial), "Conexão falhou!", Snackbar.LENGTH_SHORT).setAction("OK", null).show();
        }
    }
    private void mostraAlerta(String titulo, String mensagem) {
        AlertDialog alertDialog = new
                AlertDialog.Builder(MainActivity.this).create();
        alertDialog.setTitle(titulo);
        alertDialog.setMessage(mensagem);
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL,
                getString(R.string.ok),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }

    public boolean isOnline() {
        ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        return manager.getActiveNetworkInfo() != null &&
                manager.getActiveNetworkInfo().isConnectedOrConnecting();
    }

    public void AcaoBotao(View view){
        switch (view.getId()) {
                case (R.id.btnPesquisar):
                    CodigoCep = pesquisa.getText().toString();
                    Intent intent = new Intent(this , MainActivity.class);
                    intent.putExtra("CodFlat", 3);
                    intent.putExtra("CODIGOCEP", CodigoCep);
                    intent.putExtra("COLUNAPESQUISA", campopesquisa);
                    startActivity(intent);
                break;
                case (R.id.BtnBuscaAPI):
                    if (bconectado) {
                        mostraAlerta();

                    } else
                    {
                        Toast.makeText(MainActivity.this, "Verifique sua conexão com a Internet", Toast.LENGTH_SHORT).show();
                    }
                break;
        }
    }

    public void BuscaDados(){
        VerificaURL();

        AppInterface service = ApiClient.getClient(BASE_URL).create(AppInterface.class);
        Call<List<Json>> callUsers = service.json();
        callUsers.enqueue(new Callback<List<Json>>() {
            @Override
            public void onResponse(Call<List<Json>> call, Response<List<Json>> response) {
                int statusCode = response.code();
                List<Json> json = response.body();
                bd.LimpaCeps();
                for(int i = 0; i < json.size(); i++)
                {
                    Json jsons = new Json();
                    jsons.setCep(json.get(i).getCep());
                    jsons.setBairro(json.get(i).getBairro());
                    jsons.setComplemento(json.get(i).getComplemento());
                    jsons.setIbge(json.get(i).getIbge());
                    jsons.setLogradouro(json.get(i).getLogradouro());
                    jsons.setUf(json.get(i).getUf());
                    jsons.setLocalidade(json.get(i).getLocalidade());
                    bd.addCep(jsons);
                }
                recyclerView.setAdapter(new adapterCEP(json, R.layout.activity_cep, getApplicationContext()));
            }

            @Override
            public void onFailure(Call<List<Json>> call, Throwable t) {
                mostraAlerta("Erro", t.toString());
            }
        });
    }

    private void mostraAlerta() {
        AlertDialog.Builder ConfirmaItem = new AlertDialog.Builder(MainActivity.this);
        ConfirmaItem.setTitle("Atenção!");
        ConfirmaItem.setMessage("Confirma Exclusão dos CEPs e sincronização com o servidor?");
        ConfirmaItem.setCancelable(false);
        ConfirmaItem.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which ) {
                BuscaDados();
                SystemClock.sleep(1000);
                Toast.makeText(MainActivity.this, "Dados atualizados", Toast.LENGTH_SHORT).show();
            }
        });
        ConfirmaItem.setNegativeButton("Não",null);
        ConfirmaItem.create().show();
    }
    public void VerificaURL() {
        CodigoCep = pesquisa.getText().toString();
        if (CodigoCep.toString().isEmpty()) {
            BASE_URL = "https://viacep.com.br/ws/RS/Caxias do Sul/Domingos/";
        }else
        {
            BASE_URL = "https://viacep.com.br/ws/RS/Caxias do Sul/ "+ pesquisa.getText().toString()  + "/";
             if (campopesquisa.equals("CEP")){
                 BASE_URL = "https://viacep.com.br/ws/"+ pesquisa.getText().toString()  + "/";
             }
        }
    }
}