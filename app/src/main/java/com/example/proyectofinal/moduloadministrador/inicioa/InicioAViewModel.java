package com.example.proyectofinal.moduloadministrador.inicioa;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class InicioAViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public InicioAViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}