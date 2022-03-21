package com.example.proyectofinal.modulousuario.animalesu;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class animalesUViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public animalesUViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is slideshow fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}