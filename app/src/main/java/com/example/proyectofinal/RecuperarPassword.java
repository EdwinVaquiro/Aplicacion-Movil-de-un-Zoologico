package com.example.proyectofinal;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.biometrics.BiometricPrompt;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.proyectofinal.Modelo.CRUDusuario;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Random;

public class RecuperarPassword extends AppCompatActivity
{
    private Button btnRecuperar, btnRegresar, btnVerificar, btnRestaurar;
    private EditText txtcorreo, txtCodigo, txtPass;
    private TextView txt;
    private static int i;
    private static int Contador=0;
    private static  String id;
    RequestQueue requestQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recuperar_pass);
        btnRecuperar= (Button) findViewById(R.id.btnRecuperar);
        txtcorreo=(EditText) findViewById(R.id.edtCorreoRecuperar);
        btnRegresar=(Button) findViewById(R.id.btnRegresar);
        txtCodigo=(EditText) findViewById(R.id.edtCodigo);
        txtPass=(EditText) findViewById(R.id.edtNuevoPass);
        btnVerificar=(Button) findViewById(R.id.btnVerificar);
        btnRestaurar=(Button) findViewById((R.id.btnRestaurar));
        txt = (TextView) findViewById(R.id.txtTitle);

        btnRecuperar.setOnClickListener(this::Onclick);
        btnRegresar.setOnClickListener(this::Accion);
        btnVerificar.setOnClickListener(this::Verificacion);
        btnRestaurar.setOnClickListener(this::Recuperacion);



        txtCodigo.setVisibility(View.GONE);
        btnVerificar.setVisibility(View.GONE);
        btnRestaurar.setVisibility(View.GONE);
        txtPass.setVisibility(View.GONE);

        requestQueue = Volley.newRequestQueue(this);



        if(ActivityCompat.checkSelfPermission(RecuperarPassword.this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED&& ActivityCompat.checkSelfPermission(RecuperarPassword.this,Manifest
                        .permission.SEND_SMS)!= PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(RecuperarPassword.this,new String[]
                    {
                            Manifest.permission.SEND_SMS,
                    },1000);
        }else
        {
        };

    }

    private void Recuperacion(View view)
    {
        //LOGICA DE LA BASE DE DATOS PARA HACER EL UPDATE A LA CONTRASEÑA DEL USUARIO
        String Nuevapassword = txtPass.getText().toString();
        if(Nuevapassword.isEmpty())
        {
            Toast.makeText(this,"El campo no puede estar vacío",Toast.LENGTH_SHORT).show();
        }else
        {
            //objeto CRUD QUE HACE EL UPDATE EN LA TABLA DE USUARIO LA CONTRASEÑA

            cambiarContraseña(Nuevapassword);


            Toast.makeText(this,"Restauración de su cuenta completada",Toast.LENGTH_SHORT).show();
            Accion(view);
        }
    }

    private void cambiarContraseña(String nuevapassword)
    {
        // aqui se pone el link de la base de datos de la nube
        StringRequest request = new StringRequest(Request.Method.POST, "https://compbdzoo.000webhostapp.com/EditarUsuario.php",
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response)
                    {

                        if(response.equalsIgnoreCase("Cambio de contraseña éxitoso"))
                        {
                            Toast.makeText(RecuperarPassword.this, "El cambio de contraseña no fue exitoso, vuelva a intentar", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            Toast.makeText(RecuperarPassword.this, "Cambio de contraseña éxitoso", Toast.LENGTH_SHORT).show();
                            Intent intent1 = new Intent(RecuperarPassword.this,ActivityIngresar.class);
                            startActivity(intent1);
                        }

                    }
                }, new Response.ErrorListener()
        {
            @Override
            public void onErrorResponse(VolleyError error)
            {
                Toast.makeText(RecuperarPassword.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }

        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError
            {
                //con map se envian los parametros por el link con el methodo post
                Map<String,String> params = new HashMap<String,String>();
                params.put("id", id);
                params.put("password", nuevapassword);
                return params;
            }
        };

        //ejecutando la direccion ip dirigida a la base de datos
        RequestQueue requestQueue = Volley.newRequestQueue(RecuperarPassword.this);
        requestQueue.add(request);

    }

    //accion de regresar
    private void Accion(View view)
    {
        Intent a = new Intent(this,ActivityIngresar.class);
        startActivity(a);
    }

    //boton que verifica si el codigo enviado al telefono
    private void Verificacion(View view)
    {
        String Codigo = txtCodigo.getText().toString();
        //si el  campo  de codigo esta vacio
        if(Codigo.isEmpty())
        {
            Toast.makeText(this,"El campo no puede estar vacío",Toast.LENGTH_SHORT).show();
        }else
        {
            //verificando que el codigo coincida con el enviado al telefono movil
            if(i == Integer.parseInt(Codigo))
            {
                Toast.makeText(this,"Codigo ingresado con éxito!!",Toast.LENGTH_SHORT).show();
                txtCodigo.setVisibility(View.GONE);
                btnVerificar.setVisibility(View.GONE);
                txtPass.setVisibility(View.VISIBLE);
                btnRestaurar.setVisibility(View.VISIBLE);
                txt.setText("Por favor digite la nueva contraseña");
            }else
            {
                Toast.makeText(this,"Codigo Incorrecto por favor verifique",Toast.LENGTH_SHORT).show();
                //VERIFICANOD INTENTOS POR SEGURIDAD

                /*if(Contador>=5)
                {
                    Contador=1;
                    Toast.makeText(this,"Demasiados intentos fallidos",Toast.LENGTH_SHORT).show();
                    Intent inicio = new Intent(this,ActivityIngresar.class);
                    startActivity(inicio);
                }else
                {
                    Toast.makeText(this,"Codigo Incorrecto Le quedan "+ (5-Contador) +" Intentos",Toast.LENGTH_SHORT).show();
                    Contador++;
                }
            */
            }

        }
    }

    private void Onclick(View view)
    {
        String correo = txtcorreo.getText().toString().trim();
        int id = view.getId();


        //EN LA PRIMERA LINEA VALIDAR SI EL CORREO EXISTE EN LA BD

        if(correo.isEmpty() || correo.equalsIgnoreCase("") || !correo.contains("@") || !correo.contains("."))
        {
            Toast.makeText(this,"El campo no puede estar vacío y debe ser válido",Toast.LENGTH_SHORT).show();

        }else if(id==R.id.btnRecuperar)
        {
            txtcorreo.setText("");
            //funcion que envia el Mensaje para del código para la recuperación de la contraseña
            comprobar(correo);
        }

    }
    private void comprobar(String correo)
    {
        String URL ="https://compbdzoo.000webhostapp.com/Select.php?correo="+correo;
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET, URL, null,
                new Response.Listener<JSONObject>()
                {
                    @Override
                    public void onResponse(JSONObject response)
                    {
                        try
                        {
                            String tel = response.getString("Tel");
                            id = response.getString("id_usuario");
                            try {
                                //generando codigo de 6 digitos para recuperar contraseña
                                Random num = new Random();
                                i = num.nextInt(999999) + 100001;

                                if(ActivityCompat.checkSelfPermission(RecuperarPassword.this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED)
                                {
                                    ActivityCompat.requestPermissions(RecuperarPassword.this,new String[]{Manifest.permission.SEND_SMS},1);
                                }

                                String mensaje = "Sistema del zoologico:\nEl código para la recuperación de su contraseña es: " + i + "\n Por favor digite el código en la aplicación";
                                //ESTE ES EL NUMERO DE LA BASE DE DATOS DONDE SERÁ ENVIADO EL SMS
                                //enviando mensaje de texto desde la aplicacipon
                                SmsManager sms = SmsManager.getDefault();
                                sms.sendTextMessage(tel.trim(), null, mensaje, null, null);

                                Toast.makeText(RecuperarPassword.this, "Mensaje enviado al telefono: "+ tel, Toast.LENGTH_SHORT).show();

                                //desactivando los botones y el txt correo
                                txtcorreo.setEnabled(false);
                                //haciendo visible el txt codigo de verificación
                                txtCodigo.setVisibility(View.VISIBLE);
                                btnVerificar.setVisibility(View.VISIBLE);
                                txtcorreo.setVisibility(View.GONE);
                                btnRecuperar.setVisibility(View.GONE);
                                txt.setText("");
                                txt.setText("Por favor digite el código de verificación que recibió al tel." + i);

                            } catch (Exception or)
                            {
                                //si hay error enviar mensaje de error
                                Toast.makeText(RecuperarPassword.this, "El mensaje no pudo ser  error: " + or.toString(), Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e)
                        {
                            Toast.makeText(RecuperarPassword.this, "El usuario no existe"+ e.getMessage(),Toast.LENGTH_LONG).show();
                        }
                    }
                }, new Response.ErrorListener()
        {
            @Override
            public void onErrorResponse(VolleyError error)
            {

                Toast.makeText(RecuperarPassword.this, "El usuario no existe o el correo es inválido por favor verifique",Toast.LENGTH_SHORT).show();
            }
        }
        );
        requestQueue.add(jsonObjectRequest);
    }


}