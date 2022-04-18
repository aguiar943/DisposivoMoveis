package ucs.android.aulas.trabalho01_v2.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import ucs.android.aulas.trabalho01_v2.R;
import ucs.android.aulas.trabalho01_v2.adapter.BebidasAdapter;
import ucs.android.aulas.trabalho01_v2.adapter.PedidosAdapter;
import ucs.android.aulas.trabalho01_v2.adapter.ProdutosAdapter;
import ucs.android.aulas.trabalho01_v2.banco.BancoDados;
import ucs.android.aulas.trabalho01_v2.databinding.FragmentFirst2Binding;
import ucs.android.aulas.trabalho01_v2.model.Pedido;
import ucs.android.aulas.trabalho01_v2.model.Produto;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class AlteraPedido extends AppCompatActivity {
    ArrayList<Pedido> listaPedidos;
    ArrayList<Produto> listaProdutos;
    private AlteraPedido binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_altera_pedido);
        Intent intent = getIntent();
        ListView lista = findViewById(R.id.lvItens);
        final int id = intent.getIntExtra("ID", 0);
//        BancoDados bd = ;

        listaPedidos =   BancoDados.getInstancia().MostraItens(id);


        PedidosAdapter adapter = new PedidosAdapter(getBaseContext(), listaPedidos);
        lista.setAdapter(adapter);


        listaPedidos = BancoDados.getInstancia().getAllPedidos();
//        listaPedidos = BancoDados.getInstancia().getPedido();
        //ListView lista = (ListView) findViewById(R.id.lvLivros);

//        ListView lista = binding.lvPedidos;
//
//        PedidosAdapter adapter = new PedidosAdapter(getContext(), listaPedidos);
//        lista.setAdapter(adapter);


////        final EditText autor = (EditText) findViewById(R.id.etAutor);
////        final EditText ano = (EditText) findViewById(R.id.etAno);
////        nome.setText(livro.getTitulo());
////        autor.setText(livro.getAutor());
////        ano.setText(String.valueOf(livro.getAno()));
//
//        final Button alterar = (Button) findViewById(R.id.btnAlterar);
//        alterar.setOnClickListener(v -> {
//            Livro livro1 = new Livro();
//            livro1.setId(id);
//            livro1.setTitulo(nome.getText().toString());
//            livro1.setAutor(autor.getText().toString());
//            livro1.setAno(Integer.parseInt(ano.getText().toString()));
//            bd.updateLivro(livro1);
//            Intent intent1 = new Intent(EditarLivroActivity.this, MainActivity.class);
//            startActivity(intent1);
//        });
//
//        final Button remover = (Button) findViewById(R.id.btnRemover);
//        remover.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                new AlertDialog.Builder(EditarLivroActivity.this)
//                        .setTitle(R.string.confirmar_exclusao)
//                        .setMessage(R.string.quer_mesmo_apagar)
//                        .setIcon(android.R.drawable.ic_dialog_alert)
//                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
//
//                            public void onClick(DialogInterface dialog, int whichButton) {
//                                Livro livro = new Livro();
//                                livro.setId(id);
//                                bd.deleteLivro(livro);
//                                Intent intent = new Intent(EditarLivroActivity.this, MainActivity.class);
//                                startActivity(intent);
//                            }
//                        })
//                        .setNegativeButton(android.R.string.no, null).show();
//            }
//        });
    }
}