package com.example.proyectofinal.modulousuario.iniciou;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class InicioUViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public InicioUViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}