package com.example.proyectofinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button btnIngresar,btnRegistrar,btnAdmin;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnIngresar=(Button) findViewById(R.id.btnIngresar);
        btnRegistrar=(Button) findViewById(R.id.btnReg);
        btnAdmin=(Button) findViewById(R.id.btnAdmin);
        //Listener para los botones
        btnIngresar.setOnClickListener(this::onClick);
        btnRegistrar.setOnClickListener(this::onClick);
        btnAdmin.setOnClickListener(this::onClick);

    }

    private void onClick(View v) {
    int id=v.getId();
    //Casos para los botones
    if(id==R.id.btnReg){
        Intent intent= new Intent(this,ActivityRegistrar.class);
        startActivity(intent);
    }else if(id==R.id.btnIngresar)
    {
        Intent intent= new Intent(this,ActivityIngresar.class);
        startActivity(intent);
    }else if(id==R.id.btnAdmin)
    {
        Intent intent= new Intent(this,ActivityAdmin.class);
        startActivity(intent);
        }

    }
}