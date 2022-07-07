package ucs.android.aulas.trabalho03_v2.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.RecyclerView;

import java.io.FileOutputStream;
import java.sql.SQLException;

import ucs.android.aulas.trabalho03_v2.DAO.BDSQLiteHelper;
import ucs.android.aulas.trabalho03_v2.DAO.Database;
import ucs.android.aulas.trabalho03_v2.R;
import ucs.android.aulas.trabalho03_v2.activity.MainActivity;
import ucs.android.aulas.trabalho03_v2.activity.MainActivity_conversas;
import ucs.android.aulas.trabalho03_v2.databinding.FragmentHomeBinding;
import ucs.android.aulas.trabalho03_v2.ui.gallery.GalleryFragment;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private Database bd;
    private BDSQLiteHelper bdlocal;
    private RecyclerView recyclerView;
    private EditText usuario;
    private String sUsuario;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        return root;
    }


    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        usuario = view.findViewById(R.id.tvusuario);
        binding.buttonFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sUsuario = (usuario.getText().toString());
                Database db = new Database();
                try {
                    db.getConnection();
                    db.AddUsuario(sUsuario ,"S");
                } catch (Exception e) {
                    e.printStackTrace();
                }

                bdlocal = new BDSQLiteHelper(getActivity());
                bdlocal.LimpaUsuarios();
                bdlocal.addUsuario(sUsuario);

                Intent abrirOutraActivity = new Intent(getActivity(), MainActivity_conversas.class);
                abrirOutraActivity.putExtra("sUsuario", sUsuario);
                startActivity(abrirOutraActivity);


            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void onDestroy() {
        super.onDestroy();
        try {
            Database bd = new Database();
            bd.DesconectadaUsuario(sUsuario ,"N");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}