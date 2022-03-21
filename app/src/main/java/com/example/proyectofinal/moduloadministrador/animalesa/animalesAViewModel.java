package com.example.proyectofinal.moduloadministrador.animalesa;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class animalesAViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public animalesAViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is slideshow fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}