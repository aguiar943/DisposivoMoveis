package ucs.android.aulas.trabalho03_v2.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import ucs.android.aulas.trabalho03_v2.DAO.BDSQLiteHelper;
import ucs.android.aulas.trabalho03_v2.DAO.Database;
import ucs.android.aulas.trabalho03_v2.R;
import ucs.android.aulas.trabalho03_v2.adapter.adapterMSG;

public class MainActivity_chat extends AppCompatActivity {
    private EditText texto;
    private Database bd;
    private BDSQLiteHelper bdlocal;
    private String Stexto , sUsuario, sNomeUsuarioDestino;
    private RecyclerView recyclerView;
    private int iNomeUsuarioDestino, iNomeUsuarioRemetente;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_chat);



        texto = (EditText) findViewById(R.id.edittxtmsg);
        TextView conexao = (TextView) findViewById(R.id.tvusuario);
        sUsuario = texto.getText().toString();
        recyclerView = findViewById(R.id.posts_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        CarregaUsuario();
        CarregaInformacoes();
    }

    public void AcaoBotao(View view) throws ParseException {
        switch (view.getId()) {
            case (R.id.btnenviar):
                Date dataHoraAtual = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                String data = new SimpleDateFormat("dd/MM/yyyy").format(dataHoraAtual);
                Date d = sdf.parse(data);
                Database db = new Database();
                try {
                    Stexto = texto.getText().toString();
                    if (Stexto != "") {
                        db.AddConversa(data,1,iNomeUsuarioDestino,"Caxias do sul", Stexto);
                    }
                    CarregaInformacoes();
                    Stexto = "";
                    texto.setText(new String(Stexto).toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case (R.id.btnimg):
                finish();
                break;

        }
    }

    public void CarregaInformacoes(){
        Database bd = new Database();
        try {
            recyclerView.setAdapter(new adapterMSG(bd.getMostraConversas(1,2), R.layout.activity_chats, getApplicationContext()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void CarregaUsuario()  {
        iNomeUsuarioRemetente = 0;
        String nome;
        Database bd = new Database();
        bdlocal = new BDSQLiteHelper(this);

        nome = bdlocal.pegarUsuario();
        try {
            iNomeUsuarioRemetente = bd.VerificaUsuario(nome);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
