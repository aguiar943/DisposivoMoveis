package ucs.android.aulas.trabalho03_v1.activity;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.TextView;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.sql.SQLException;

import ucs.android.aulas.trabalho03_v1.DAO.Database;
import ucs.android.aulas.trabalho03_v1.R;
import ucs.android.aulas.trabalho03_v1.adapter.adapterUsuarios;
import ucs.android.aulas.trabalho03_v1.databinding.ActivityMainConversasBinding;

public class MainActivity_conversas extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainConversasBinding binding;
    private Database bd;
    private RecyclerView recyclerView;
    private String sUsuario;
    final FragmentManager fgm = getSupportFragmentManager();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        FragmentTransaction ft = fgm.beginTransaction();

        //Instanciando os fragments
        First2Fragment frag1 = new First2Fragment();
        ft.add(R.id.tvusuario, frag1);
        ft.commit();

        binding = ActivityMainConversasBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main_activity_conversas);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main_activity_conversas);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }

}