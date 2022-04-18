package ucs.android.aulas.trabalho01_v2.activity;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import java.util.ArrayList;

import ucs.android.aulas.trabalho01_v2.R;
import ucs.android.aulas.trabalho01_v2.adapter.BebidasAdapter;
import ucs.android.aulas.trabalho01_v2.banco.BancoDados;
import ucs.android.aulas.trabalho01_v2.databinding.ActivityProdutoBinding;
import ucs.android.aulas.trabalho01_v2.model.Produto;

public class ProdutoActivity extends AppCompatActivity {
    ArrayList<Produto> listaProdutos;
    private AppBarConfiguration appBarConfiguration;
    private ActivityProdutoBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityProdutoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_produto);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
    }

    public void botaoapertado (View view) {
        ListView lista = findViewById(R.id.lvItens);
        BebidasAdapter adapter = new BebidasAdapter(getBaseContext(), listaProdutos);
        switch (view.getId()) {
            case (R.id.BtnBebidas):

                Toast.makeText(ProdutoActivity.this, "Produto lançado", Toast.LENGTH_SHORT).show();
                listaProdutos =   BancoDados.getInstancia().getAllProdutosBebidas("B");
                lista.setAdapter(adapter);

                lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view,
                                            int position, long id) {
                        Intent intent = new Intent(getBaseContext(), ProdutoActivity.class);
                        intent.putExtra("ID", listaProdutos.get(position).getId());
//                intent.putExtra("LivroCorrente", listaPedidos.get(position) );

                        startActivity(intent);
                    }
                });
                break;
            case (R.id.BtnComida):
                Toast.makeText(ProdutoActivity.this, "Produto lançado", Toast.LENGTH_SHORT).show();

                listaProdutos =   BancoDados.getInstancia().getAllProdutosBebidas("C");
                lista.setAdapter(adapter);

                lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view,
                                            int position, long id) {
                        Intent intent = new Intent(getBaseContext(), ProdutoActivity.class);
                        intent.putExtra("ID", listaProdutos.get(position).getId());
//                intent.putExtra("LivroCorrente", listaPedidos.get(position) );

                        startActivity(intent);
                    }
                });
                break;
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_produto);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }

}