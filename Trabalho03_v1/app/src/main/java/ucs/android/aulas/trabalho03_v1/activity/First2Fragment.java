package ucs.android.aulas.trabalho03_v1.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.sql.SQLException;

import ucs.android.aulas.trabalho03_v1.DAO.Database;
import ucs.android.aulas.trabalho03_v1.R;
import ucs.android.aulas.trabalho03_v1.adapter.adapterMSG;
import ucs.android.aulas.trabalho03_v1.adapter.adapterUsuarios;
import ucs.android.aulas.trabalho03_v1.databinding.FragmentFirst2Binding;

public class First2Fragment extends Fragment {

    private FragmentFirst2Binding binding;
    private RecyclerView recyclerView;
    private Database bd;
    private String sUsuario;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        View view = inflater.inflate(R.layout.fragment_first2, container, false);
        recyclerView = view.findViewById(R.id.recyclervmsg);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));



//        sUsuario = (String) getArguments().getSerializable("UsuarioLogado");






        try {
            Database bd = new Database();
            recyclerView.setAdapter(new adapterUsuarios(bd.getMostraOnlines(sUsuario), R.layout.activity_msg, getActivity()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
//        return binding.getRoot();
        return view;

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


}