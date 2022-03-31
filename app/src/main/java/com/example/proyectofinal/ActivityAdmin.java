package com.example.proyectofinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ActivityAdmin extends AppCompatActivity {
Button btnIngresarA,btnVolverA;
EditText edtCorreoA, edtPasswordA;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
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
        if(id==R.id.btnIngresarA)
        {
            String correo = edtCorreoA.getText().toString().trim();
            String password = edtPasswordA.getText().toString().trim();

            if(correo.isEmpty() || password.isEmpty())
            {
                Toast.makeText(this,"Los campos deben ser validos",Toast.LENGTH_SHORT).show();
            }else
            {
                Login(correo, password);
            }

            /*Intent intent1=new Intent(this, ActivityAdministrador.class);
            startActivity(intent1);*/
        }else if(id==R.id.btnVolverA)
        {

            Intent intent= new Intent(this,MainActivity.class);
            startActivity(intent);
        }
    }

    private void Login(String correoo, String passwordd)
    {


        //url de la base de datos en la nube
        String url="https://compbdzoo.000webhostapp.com/SelectAdmin.php";

        //objeto request que trae los datos de la base datos
        StringRequest request = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response)
                    {
                        try{
                            //objeto jason que por medio del link trae los datos
                            JSONObject jsonObject = new JSONObject(response);
                            String exito = jsonObject.getString("ready");
                            JSONArray jsonArray = jsonObject.getJSONArray("datos");

                            //if que revisa el estado del php
                            if(exito.equals("1"))
                            {
                                //booleano que realiza la comprobacion de igualdad del correo y contraseña
                                boolean estado=false;
                                for(int i=0;i<jsonArray.length();i++)
                                {

                                    //trayendo los objetos de la tabla usuarios
                                    JSONObject object = jsonArray.getJSONObject(i);
                                    String id = object.getString("id_admin");
                                    String nombre = object.getString("nombre");
                                    String correo = object.getString("correo");
                                    String password = object.getString("password");

                                    //comparando si coinciden con lo que esta en la base de datos
                                    if(correo.equalsIgnoreCase(correoo))
                                    {
                                        //si la el correo coincide con alguno de la base de datos entra a ver si la contraseña es igual
                                        if(password.equals(passwordd))
                                        {

                                            //si es igual el correo y la contraseña rompe el bucle y cambia el booleano a verdadero
                                            estado=true;
                                            break;
                                        }else
                                        {
                                            //si la contraseña es incorrecta rompe el ciclo
                                            break;
                                        }
                                    }
                                }

                                //comprobando el booleano
                                if(estado)
                                {
                                    //si la contraseña y el correo coinciden con la de la base de datos redireccionar al activity
                                    Toast.makeText(ActivityAdmin.this,"Usted ha ingresado con éxito, BIENVENIDO ADMINISTRADOR", Toast.LENGTH_SHORT).show();

                                    //AQUI VA LA REDIRECCION AL FORMULARIO PRINCIPAL QUE VAN A VER LOS USUARIOS CUANDO EL LOGIN SEA EXITOSO
                                    Intent io = new Intent(ActivityAdmin.this, ActivityAdministrador.class);
                                    startActivity(io);
                                }else
                                {
                                    //si no coincidieron decirle al usuario que lo intente de nuevo.
                                    Toast.makeText(ActivityAdmin.this,"Las credenciales son invalidas, intente de nuevo", Toast.LENGTH_SHORT).show();
                                    edtPasswordA.setText("");
                                }

                            }else
                            {
                                Toast.makeText(ActivityAdmin.this,"Error al comprobar los usuarios", Toast.LENGTH_LONG).show();
                            }

                        }
                        catch (JSONException e)
                        {
                            Toast.makeText(ActivityAdmin.this,"Error en response la lista: " + e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                }, new Response.ErrorListener()
        {
            @Override
            public void onErrorResponse(VolleyError error)
            {
                Toast.makeText(ActivityAdmin.this, "ERROR EN RESPONSE: "+error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);


    }
}