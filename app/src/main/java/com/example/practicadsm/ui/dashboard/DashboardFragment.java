package com.example.practicadsm.ui.dashboard;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.example.practicadsm.R;
import com.example.practicadsm.databinding.FragmentDashboardBinding;
import com.example.practicadsm.ui.home.HomeFragment;

public class DashboardFragment extends Fragment {

    private FragmentDashboardBinding binding;

    private EditText txtrut , txtNombre, txtEdad;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        DashboardViewModel dashboardViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        txtrut= binding.txtRut;
        txtEdad= binding.txtEdad;
        txtNombre=binding.txtNombrer;
        binding.btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                guardarDatos();
            }
        });
        binding.btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                obtenerDatos();
            }
        });
        /*binding.btnAnterior.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment= HomeFragment;
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.container, fragment).commit();
            }
        });*/
        return root;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void guardarDatos() {
        SharedPreferences pref= getActivity().getSharedPreferences("txt", Context.MODE_PRIVATE);
        SharedPreferences.Editor edt= pref.edit();
        edt.putString(txtrut.getText().toString(), txtNombre.getText().toString());
        edt.putString(txtrut.getText().toString()+"1", txtEdad.getText().toString());
        edt.commit();
    }

    public void obtenerDatos(){
        SharedPreferences pref= getActivity().getSharedPreferences("txt", Context.MODE_PRIVATE);
        String edad=pref.getString(txtrut.getText().toString()+"1", "");
        String nombre=pref.getString(txtrut.getText().toString(),"");
        txtNombre.setText(nombre);
        txtEdad.setText(edad);
    }
}