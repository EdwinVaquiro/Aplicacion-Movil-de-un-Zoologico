package com.example.proyectofinal.modulousuario.zoou;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;


import com.example.proyectofinal.databinding.FragmentZoouBinding;

public class zooUFragment extends Fragment {

    private FragmentZoouBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        zooUViewModel zooUViewModel =
                new ViewModelProvider(this).get(zooUViewModel.class);

        binding = FragmentZoouBinding.inflate(inflater, container, false);
        View root = binding.getRoot();



        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}