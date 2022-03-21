package com.example.proyectofinal.modulousuario.ticketsu;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.proyectofinal.databinding.FragmentTicketsuBinding;

public class ticketsUFragment extends Fragment {

    private FragmentTicketsuBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ticketsUViewModel ticketsUViewModel =
                new ViewModelProvider(this).get(ticketsUViewModel.class);

        binding = FragmentTicketsuBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}