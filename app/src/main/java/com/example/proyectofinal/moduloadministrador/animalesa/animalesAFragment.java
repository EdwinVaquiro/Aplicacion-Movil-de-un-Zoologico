package com.example.proyectofinal.moduloadministrador.animalesa;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.proyectofinal.databinding.FragmentAnimalesaBinding;

public class animalesAFragment extends Fragment {

    private FragmentAnimalesaBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        animalesAViewModel animalesAViewModel =
                new ViewModelProvider(this).get(animalesAViewModel.class);

        binding = FragmentAnimalesaBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        return root;
    }

    @Override
    public void onDestroyView()
    {
        super.onDestroyView();
        binding = null;
    }
}