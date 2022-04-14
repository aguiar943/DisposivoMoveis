package ucs.android.aulas.trabalho01;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class teste extends AppCompatActivity {
    private TextView teste;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teste);
        Banco bd = Banco.getInstancia();


    }
}