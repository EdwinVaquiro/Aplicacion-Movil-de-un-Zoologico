package com.example.proyectofinal.modulousuario.zoou;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class zooUViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public zooUViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is gallery fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}