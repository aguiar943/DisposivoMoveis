package ucs.android.aulas.trabalho01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onclickpedidos(View view){
        Intent intent = new Intent(MainActivity.this, Pedido.class);
        intent.putExtra("PegaInicio", true);
        startActivity(intent);
    }
}