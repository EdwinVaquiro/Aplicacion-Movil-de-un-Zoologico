package com.example.proyectofinal.modulousuario.ticketsu;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ticketsUViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public ticketsUViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}