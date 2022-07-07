package ucs.android.aulas.trabalho03_v2.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.io.FileOutputStream;
import java.sql.SQLException;

import ucs.android.aulas.trabalho03_v2.DAO.BDSQLiteHelper;
import ucs.android.aulas.trabalho03_v2.DAO.Database;
import ucs.android.aulas.trabalho03_v2.R;
import ucs.android.aulas.trabalho03_v2.adapter.adapterUsuarios;
import ucs.android.aulas.trabalho03_v2.model.Usuarios;

public class MainActivity_conversas extends AppCompatActivity {

    private RecyclerView recyclerView;
    private Database bd;
    private BDSQLiteHelper bdlocal;
    private String sUsuario;
    private int iUsuario;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_conversas);
        Intent intent = getIntent();

        recyclerView = (RecyclerView) findViewById(R.id.recyclervmsg);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        sUsuario = intent.getStringExtra("sUsuario");
        TextView nome = findViewById(R.id.tvusuariologado);
        sUsuario = (nome.getText().toString());

        try {
            Database bd = new Database();
            recyclerView.setAdapter(new adapterUsuarios(bd.getMostraOnlines(sUsuario), R.layout.activity_msg, getApplicationContext()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}