package com.example.proyectofinal.moduloadministrador.zooa;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class zooAViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public zooAViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is gallery fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}