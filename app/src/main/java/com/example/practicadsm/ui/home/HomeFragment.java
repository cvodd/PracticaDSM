package com.example.practicadsm.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import java.util.ArrayList;

import com.example.practicadsm.R;
import com.example.practicadsm.databinding.FragmentHomeBinding;
import com.example.practicadsm.ui.dashboard.DashboardFragment;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private EditText txtNombre, txtDescripcion;

    private ArrayList<String> consolas, descripcion;

    private ListView listView;
    private ArrayAdapter<String> adaptador;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        listView= (ListView) binding.listView;

        consolas= new ArrayList<String>();
        descripcion= new ArrayList<String>();
        adaptador=new ArrayAdapter<>(this.getContext(), android.R.layout.simple_list_item_1,consolas);
        listView.setAdapter(adaptador);
        txtDescripcion = binding.txtDescripcion;
        txtNombre = binding.txtNombre;



        binding.agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                agregarLista();
            }
        });
        binding.siguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.container, new DashboardFragment()).commit();
            }
        });

        listView.setClickable(true);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                   Toast.makeText(getActivity(), descripcion.get(i),Toast.LENGTH_LONG).show();
            }
        });
        return root;
    }


    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


    public void agregarLista(){
       int duration = Toast.LENGTH_SHORT;
            if(txtNombre.getText().toString().isEmpty()){
                Toast.makeText(getActivity(),"No se ingreso nombre", duration).show();
            }

            if(txtDescripcion.getText().toString().isEmpty()){
               Toast.makeText(getActivity(),"No se ingreso descripcion", duration).show();
            }
            else {
                Toast.makeText(getActivity(),"Consola agregada correctamente", duration).show();
                consolas.add(txtNombre.getText().toString());
                descripcion.add((txtDescripcion.getText().toString()));
                adaptador.notifyDataSetChanged();
            }
       /* if(!txtNombre.getText().toString().isEmpty()) {

            consolas.add(txtNombre.getText().toString());
            adaptador.notifyDataSetChanged();
        }*/


    }


}