package com.example.proyectofinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ActivityAdmin extends AppCompatActivity {
Button btnIngresarA,btnVolverA;
EditText edtCorreoA, edtPasswordA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        btnIngresarA=(Button) findViewById(R.id.btnIngresarA);
        btnVolverA=(Button)findViewById(R.id.btnVolverA);
        edtCorreoA=(EditText) findViewById(R.id.edtCorreoA);
        edtPasswordA=(EditText) findViewById(R.id.edtPasswordA);
        btnIngresarA.setOnClickListener(this::onClick);
        btnVolverA.setOnClickListener(this::onClick);



    }

    private void onClick(View v)
    {
        int id=v.getId();
        if(id==R.id.btnIngresarA){
            Intent intent1=new Intent(this, ActivityAdministrador.class);
            startActivity(intent1);
        }else if(id==R.id.btnVolverA){
            Intent intent= new Intent(this,MainActivity.class);
            startActivity(intent);
        }
    }
}