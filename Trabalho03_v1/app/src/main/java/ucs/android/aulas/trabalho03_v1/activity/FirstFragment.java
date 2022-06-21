package ucs.android.aulas.trabalho03_v1.activity;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

import ucs.android.aulas.trabalho03_v1.DAO.Database;
import ucs.android.aulas.trabalho03_v1.R;
import ucs.android.aulas.trabalho03_v1.databinding.FragmentFirstBinding;

public class FirstFragment extends Fragment {

    private FragmentFirstBinding binding;
    private Database bd;
    private RecyclerView recyclerView;
    private EditText usuario;
    private String sUsuario;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentFirstBinding.inflate(inflater, container, false);
        return binding.getRoot();

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
                Snackbar.make(view, "teste", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                NavHostFragment.findNavController(FirstFragment.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}