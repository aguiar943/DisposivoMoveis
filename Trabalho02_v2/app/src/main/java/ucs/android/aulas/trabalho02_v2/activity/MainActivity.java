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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
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
    private  String CodigoCep;
    ArrayList<Json> listaCeps;
    private  EditText cep;
    private int iflat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView conexao = (TextView) findViewById(R.id.TvConexao);

        bd = new BDSQLiteHelper(this);

        criarConexao();

        Intent intent = getIntent();
        iflat = intent.getIntExtra("CodFlat",0);

        recyclerView = (RecyclerView) findViewById(R.id.posts_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        if (isOnline()) {
            Toast.makeText(MainActivity.this, "Conectado com sucesso..", Toast.LENGTH_SHORT).show();
            conexao.setTextColor(getResources().getColor(R.color.colorGreenC));
            conexao.setText("CONECTADO");

            AppInterface service = ApiClient.getClient().create(AppInterface.class);

            Call<List<Json>> callUsers = service.json();
            callUsers.enqueue(new Callback<List<Json>>() {
                @Override
                public void onResponse(Call<List<Json>> call, Response<List<Json>> response) {
                    int statusCode = response.code();
                    List<Json> json = response.body();
                    Json jsons1 = new Json();
                    bd.LimpaCeps(jsons1);

                    for(int i = 1; i < json.size(); i++)
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
        } else {
            Toast.makeText(MainActivity.this, "Não foi possível conectar..", Toast.LENGTH_SHORT).show();
            conexao.setTextColor(getResources().getColor(R.color.colorRedD));
            conexao.setText("DESCONECTADO - CONEXÃO LOCAL");

            if (iflat != 3){
                recyclerView.setAdapter(new adapterCEP(bd.getAllCeps(), R.layout.activity_cep, getApplicationContext()));
            }

            if (iflat == 3){
                CodigoCep = intent.getStringExtra("CODIGOCEP");
                recyclerView.setAdapter(new adapterCEP(bd.getPesquisaCEP(CodigoCep), R.layout.activity_cep, getApplicationContext()));
            }

        }

    }
    private void criarConexao() {
        try {
            bd = new BDSQLiteHelper(this);

            conexao = bd.getWritableDatabase();
            Snackbar.make(findViewById(R.id.LayoutInicial), "Conexão criada com sucesso!", Snackbar.LENGTH_SHORT).setAction("OK", null).show();
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
            case (R.id.btnBuscarDados):
                cep         = (EditText) findViewById(R.id.TvEdtpesquisa);
                CodigoCep = cep.getText().toString();
//                Json json = bd.getCeps(CodigoCep);

                Intent intent = new Intent(this , MainActivity.class);
                intent.putExtra("CodFlat", 3);
                intent.putExtra("CODIGOCEP", CodigoCep);
                startActivity(intent);
                break;
        }
    }
}