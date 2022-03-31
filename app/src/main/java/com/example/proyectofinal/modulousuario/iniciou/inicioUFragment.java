package com.example.proyectofinal.modulousuario.iniciou;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.proyectofinal.databinding.FragmentInicioaBinding;

public class inicioUFragment extends Fragment
{

    private FragmentInicioaBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState)
    {
        InicioUViewModel inicioAViewModel =
                new ViewModelProvider(this).get(InicioUViewModel.class);

        binding = FragmentInicioaBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}