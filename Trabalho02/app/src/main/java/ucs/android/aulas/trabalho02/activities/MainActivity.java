package ucs.android.aulas.trabalho02.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ucs.android.aulas.trabalho02.R;
import ucs.android.aulas.trabalho02.adapter.adapterPost;
import ucs.android.aulas.trabalho02.model.Example;
import ucs.android.aulas.trabalho02.rest.ApiClient;
import ucs.android.aulas.trabalho02.rest.ApiInterface;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.posts_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ApiInterface service = ApiClient.getClient().create(ApiInterface.class);

        Call<List<Example>> callUsers = service.superherois();
        callUsers.enqueue(new Callback<List<Example>>() {
            @Override
            public void onResponse(Call<List<Example>> call, Response<List<Example>> response) {
                int statusCode = response.code();
                List<Example> examples = response.body();
                recyclerView.setAdapter(new adapterPost(examples, R.layout.lista_posts, getApplicationContext()));
            }

            @Override
            public void onFailure(Call<List<Example>> call, Throwable t) {
                mostraAlerta("Erro", t.toString());
                // Log error here since request faile;
            }
        });
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
}