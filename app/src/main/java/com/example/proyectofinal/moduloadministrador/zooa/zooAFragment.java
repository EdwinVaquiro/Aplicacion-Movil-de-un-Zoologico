package com.example.proyectofinal.moduloadministrador.zooa;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.proyectofinal.databinding.FragmentZooaBinding;

public class zooAFragment extends Fragment {

    private FragmentZooaBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        zooAViewModel galleryViewModel =
                new ViewModelProvider(this).get(zooAViewModel.class);

        binding = FragmentZooaBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}