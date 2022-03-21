package com.example.proyectofinal.moduloadministrador.ticketsa;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ticketsaViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public ticketsaViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}