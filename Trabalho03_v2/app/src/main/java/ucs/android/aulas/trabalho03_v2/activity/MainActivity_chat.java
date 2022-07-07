package ucs.android.aulas.trabalho03_v2.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

import ucs.android.aulas.trabalho03_v2.DAO.BDSQLiteHelper;
import ucs.android.aulas.trabalho03_v2.DAO.Database;
import ucs.android.aulas.trabalho03_v2.R;
import ucs.android.aulas.trabalho03_v2.adapter.adapterMSG;

public class MainActivity_chat extends AppCompatActivity {
    TextView cidadeAtual;
    EditText Cidade;
    FusedLocationProviderClient fusedLocationProviderClient;

    EditText texto;
    private Database bd;
    private BDSQLiteHelper bdlocal;
    private String Stexto , sUsuario, sNomeUsuarioDestino , slocalizao;
    private RecyclerView recyclerView;
    private int iNomeUsuarioDestino, iNomeUsuarioRemetente;
    private Address endereco;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_chat);
        TextView tvResp1 = findViewById(R.id.tvusuariomsg);
        Intent intent = getIntent();
        cidadeAtual = findViewById(R.id.cidadeAtual);
        Cidade = findViewById(R.id.Cidade);
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity_chat.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);


        } else {
            getLocation();

        }

        sNomeUsuarioDestino = intent.getStringExtra("NomeUsuarioDestino");

        tvResp1.setText(sNomeUsuarioDestino);

        recyclerView = findViewById(R.id.posts_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        CarregaUsuario();
        CarregaInformacoes();

    }

    public void AcaoBotao(View view) throws ParseException {
        texto = (EditText) findViewById(R.id.edittxtmsg);
        switch (view.getId()) {
            case (R.id.btnenviar):
                setAtual();
                Date dataHoraAtual = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                String data = new SimpleDateFormat("dd/MM/yyyy").format(dataHoraAtual);
                Date d = sdf.parse(data);
                Database db = new Database();
                try {
                    Stexto = texto.getText().toString();
                    if (Stexto != "") {
                        db.AddConversa(data,iNomeUsuarioRemetente,iNomeUsuarioDestino,slocalizao, Stexto);
                    }
                    Stexto = "";
                    texto.setText(new String(Stexto).toString());
                    CarregaInformacoes();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case (R.id.btnimg):
                setAtual();
                break;

        }

    }


    public void setAtual(){
        if(endereco != null ) {
            if(endereco.getCountryName().equals("Brasil")){
                Cidade.setText(endereco.getSubAdminArea());
            }else{
                Cidade.setText(endereco.getLocality());

            }

        }
        else {
            getLocation();
//            Toast.makeText(getApplicationContext(), "buscando localização", Toast.LENGTH_SHORT).show();
        }

        slocalizao = Cidade.getText().toString();


    }

    public void CarregaInformacoes(){
        Database bd = new Database();
        try {
            recyclerView.setAdapter(new adapterMSG(bd.getMostraConversas(iNomeUsuarioDestino,iNomeUsuarioRemetente), R.layout.activity_chats, getApplicationContext()));
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
            iNomeUsuarioDestino = bd.VerificaUsuario(sNomeUsuarioDestino);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void timer(){
        int delay = 5000;
        int interval = 1000;
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                CarregaInformacoes();
            }
        }, delay, interval);
    }


    private void getLocation() {

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity_chat.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            ActivityCompat.requestPermissions(MainActivity_chat.this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
            return;
        }
        fusedLocationProviderClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
            @Override
            public void onComplete(@NonNull Task<Location> task) {
                Location location = task.getResult();
                if (location != null) {
                    Geocoder geocoder = new Geocoder(MainActivity_chat.this, Locale.getDefault());
                    try {

                        List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);

                        cidadeAtual.setText("Você está em " + addresses.get(0).getAddressLine(0));
                        endereco = addresses.get(0);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
}
