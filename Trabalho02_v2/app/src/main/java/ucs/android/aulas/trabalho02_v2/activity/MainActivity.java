package ucs.android.aulas.trabalho02_v2.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ucs.android.aulas.trabalho02_v2.R;
import ucs.android.aulas.trabalho02_v2.REST.ApiClient;
import ucs.android.aulas.trabalho02_v2.REST.AppInterface;
import ucs.android.aulas.trabalho02_v2.adapter.adapterCEP;
import ucs.android.aulas.trabalho02_v2.model.Json;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = getIntent();
        TextView conexao = (TextView) findViewById(R.id.TvConexao);

        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.posts_recycler_view);
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
                    recyclerView.setAdapter(new adapterCEP(json, R.layout.activity_cep, getApplicationContext()));
                }

                @Override
                public void onFailure(Call<List<Json>> call, Throwable t) {
                    mostraAlerta("Erro", t.toString());
                    // Log error here since request faile;
                }
            });
    } else
        {
            Toast.makeText(MainActivity.this, "Não foi possível conectar..", Toast.LENGTH_SHORT).show();
            conexao.setTextColor(getResources().getColor(R.color.colorRedD));
            conexao.setText("DESCONECTADO - CONEXÃO LOCAL");
        }
    }

    private void mostraAlerta(String titulo, String mensagem) {
        AlertDialog alertDialog = new
                AlertDialog.Builder(MainActivity.this).create();
        alertDialog.setTitle(titulo);
        alertDialog.setMessage(mensagem);
        alertDialog.setButton(AlertDialog. BUTTON_NEUTRAL ,
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
}