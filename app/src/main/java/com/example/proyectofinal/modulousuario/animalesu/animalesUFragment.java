package com.example.proyectofinal.modulousuario.animalesu;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.proyectofinal.databinding.FragmentAnimalesuBinding;

public class animalesUFragment extends Fragment
{

    private FragmentAnimalesuBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        animalesUViewModel animalesUViewModel =
                new ViewModelProvider(this).get(animalesUViewModel.class);

        binding = FragmentAnimalesuBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}