package com.example.proyectofinal;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
import com.example.proyectofinal.Modelo.CRUDusuario;
import com.example.proyectofinal.modulousuario.iniciou.InicioUViewModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class ActivityIngresar extends AppCompatActivity {
private Button btnVolver,btnIngresar,btnRecordar;
private EditText edtCorreo,edtContraseña;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingresar);
        edtCorreo=(EditText) findViewById(R.id.edtCorreoI);
        edtContraseña=(EditText) findViewById(R.id.edtPasswordI);
        btnRecordar=(Button) findViewById(R.id.btnOlvidarCon);
        btnVolver=(Button) findViewById(R.id.btnRegresar);
        btnIngresar=(Button) findViewById(R.id.btnIngresarU);
        btnVolver.setOnClickListener(this::onCLick);
        btnRecordar.setOnClickListener(this::onCLick);
        btnIngresar.setOnClickListener(this::onCLick);
    }

    private void onCLick(View v)
    {
        int id=v.getId();
        int a;
        if(id==R.id.btnIngresarU)
        {
            //validación para ver si los campos de correo o el password está vacío
            if(edtCorreo.getText().toString().equals("") || edtCorreo.getText().toString() ==null )
            {
                Toast.makeText(this, "Por favor digite su correo", Toast.LENGTH_SHORT).show();
            }else if(edtContraseña.getText().toString().equals("") || edtContraseña.getText().toString() ==null )
            {
                Toast.makeText(this, "porfavor digite su contraseña", Toast.LENGTH_SHORT).show();
            }else
            {
                //codigo para el login.................

                String pass= edtContraseña.getText().toString().trim();
                String email = edtCorreo.getText().toString().trim();

                //funcion que realiza el login
                Login(email,pass);

            }
        }else if(id==R.id.btnRegresar)
        {
            Intent intent2 = new Intent(this,MainActivity.class);
            startActivity(intent2);
        }else if(id==R.id.btnOlvidarCon)
        {
            //direccionando al activity de recuperar password
            Intent intent3 = new Intent(this,RecuperarPassword.class);
            startActivity(intent3);
        }
    }
    //funcion para cuando digitan la tecla de la casita del telefono
    public void onBackPressed()
    {
        super.onBackPressed();

        Intent intent2 = new Intent(this,MainActivity.class);
        startActivity(intent2);

    }
// funcion que realiza el login recibe el correo y la contraseña digitada en el login
    private void Login(String mail, String pass)
    {
            //url de la base de datos en la nube
            String url="https://compbdzoo.000webhostapp.com/SelectUsuario.php";

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
                                String exito = jsonObject.getString("exito");
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
                                        String id = object.getString("id_usuario");
                                        String nombre = object.getString("nombre");
                                        String correo = object.getString("correo");
                                        String password = object.getString("password");
                                        String Tel = object.getString("Tel");

                                        //comparando si coinciden con lo que esta en la base de datos
                                        if(correo.equalsIgnoreCase(mail))
                                        {
                                            //si la el correo coincide con alguno de la base de datos entra a ver si la contraseña es igual
                                            if(password.equals(pass))
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
                                        Toast.makeText(ActivityIngresar.this,"Usted ha ingresado con éxito, BIENVENIDO", Toast.LENGTH_SHORT).show();

                                        //AQUI VA LA REDIRECCION AL FORMULARIO PRINCIPAL QUE VAN A VER LOS USUARIOS CUANDO EL LOGIN SEA EXITOSO
                                        Intent io = new Intent(ActivityIngresar.this, InicioUsuario.class);
                                        startActivity(io);
                                    }else
                                    {
                                        //si no coincidieron decirle al usuario que lo intente de nuevo.
                                        Toast.makeText(ActivityIngresar.this,"Las credenciales son invalidas, intente de nuevo", Toast.LENGTH_SHORT).show();
                                        edtContraseña.setText("");
                                    }

                                }else
                                {
                                    Toast.makeText(ActivityIngresar.this,"Error al comprobar los usuarios", Toast.LENGTH_LONG).show();
                                }

                            }
                            catch (JSONException e)
                            {
                                Toast.makeText(ActivityIngresar.this,"Error en response la lista: " + e.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        }
                    }, new Response.ErrorListener()
            {
                @Override
                public void onErrorResponse(VolleyError error)
                {
                    Toast.makeText(ActivityIngresar.this, "ERROR EN RESPONSE: "+error.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(request);
        }




}