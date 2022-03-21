package com.example.proyectofinal.moduloadministrador.ticketsa;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.proyectofinal.databinding.FragmentTicketsaBinding;

public class ticketsaFragment extends Fragment {

    private FragmentTicketsaBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ticketsaViewModel ticketsaViewModel =
                new ViewModelProvider(this).get(ticketsaViewModel.class);

        binding = FragmentTicketsaBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}