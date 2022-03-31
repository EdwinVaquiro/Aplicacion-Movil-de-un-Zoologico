package com.example.proyectofinal;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
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

public class ActivityRegistrar extends AppCompatActivity {
private Button btnRegistrar, btnVolver;
private EditText edtNombre, edtCorreo,edtPassword, edtTel, edtId;
public static List<CRUDusuario> lista = new List<CRUDusuario>() {
    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean contains(@Nullable Object o) {
        return false;
    }

    @NonNull
    @Override
    public Iterator<CRUDusuario> iterator() {
        return null;
    }

    @NonNull
    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @NonNull
    @Override
    public <T> T[] toArray(@NonNull T[] a) {
        return null;
    }

    @Override
    public boolean add(CRUDusuario cruDusuario) {
        return false;
    }

    @Override
    public boolean remove(@Nullable Object o) {
        return false;
    }

    @Override
    public boolean containsAll(@NonNull Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(@NonNull Collection<? extends CRUDusuario> c) {
        return false;
    }

    @Override
    public boolean addAll(int index, @NonNull Collection<? extends CRUDusuario> c) {
        return false;
    }

    @Override
    public boolean removeAll(@NonNull Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(@NonNull Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public CRUDusuario get(int index) {
        return null;
    }

    @Override
    public CRUDusuario set(int index, CRUDusuario element) {
        return null;
    }

    @Override
    public void add(int index, CRUDusuario element) {

    }

    @Override
    public CRUDusuario remove(int index) {
        return null;
    }

    @Override
    public int indexOf(@Nullable Object o) {
        return 0;
    }

    @Override
    public int lastIndexOf(@Nullable Object o) {
        return 0;
    }

    @NonNull
    @Override
    public ListIterator<CRUDusuario> listIterator() {
        return null;
    }

    @NonNull
    @Override
    public ListIterator<CRUDusuario> listIterator(int index) {
        return null;
    }

    @NonNull
    @Override
    public List<CRUDusuario> subList(int fromIndex, int toIndex) {
        return null;
    }
};
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);
    btnRegistrar=(Button) findViewById(R.id.btnRegistrar);
    btnVolver=(Button)findViewById(R.id.btnVolverR);
    edtNombre=(EditText) findViewById(R.id.edtNombreR);
    edtCorreo=(EditText) findViewById(R.id.edtCorreoR);
    edtPassword=(EditText) findViewById(R.id.edtPasswordR);
    edtTel=(EditText) findViewById(R.id.edtTel);
    edtId =(EditText) findViewById(R.id.edtID);

    btnRegistrar.setOnClickListener(this::onClick);
    btnVolver.setOnClickListener(this::onClick);


    }

    private void onClick(View v)
    {
        //validacion para los startActivitis
        int id=v.getId();
        if(id==R.id.btnRegistrar)
        {
            //comprobacion de que cualquier campo no este vacio
            String[] a = {edtNombre.getText().toString(),edtNombre.getText().toString(), edtCorreo.getText().toString(), edtPassword.getText().toString(),edtTel.getText().toString()};
            boolean vacio=false;
            for (String aux:a)
            {
              if(aux.isEmpty())
              {
                  vacio=true;
                  break;
              }
            }
//controlando que los campos cumplan con los requisitos de la bd y no se pasen de los caracteres
            if(vacio)
            {
                Toast.makeText(this,"Ninguno de los campos puede estar vacío", Toast.LENGTH_SHORT).show();
            }else if(edtTel.getText().length()<9 || edtTel.getText().length()>10)
            {
                Toast.makeText(this,"El número de celular debe ser válido(10 digitos)", Toast.LENGTH_SHORT).show();
            }else if(!edtCorreo.getText().toString().contains(".")||!edtCorreo.getText().toString().contains("@") )
            {
                Toast.makeText(this,"El correo debe ser válido", Toast.LENGTH_SHORT).show();
            }else if(edtId.getText().length()>20 || edtCorreo.getText().length()>40 || edtPassword.getText().length()>40 || edtNombre.getText().length()>30)
            {
                Toast.makeText(this,"Demasiados caracteres en los campos", Toast.LENGTH_SHORT).show();
            }else
            {
                    //llenando el objeto de Usuario enviandolo a la funcion insertar

                    CRUDusuario ob = new CRUDusuario();
                    ob.setId(edtId.getText().toString().trim());
                    ob.setNombre(edtNombre.getText().toString().trim());
                    ob.setCorreo(edtCorreo.getText().toString().trim());
                    ob.setPassword(edtPassword.getText().toString().trim());
                    ob.setTel(edtTel.getText().toString().trim());
                    insertarUsuario(ob);


            }
            //direccionando el boton volver al login
        }else if(id==R.id.btnVolverR)
        {
            Intent intent= new Intent(this,MainActivity.class);
            startActivity(intent);
        }

    }

//funcion que inserta datos en la base de datos de la nube
    private void insertarUsuario(CRUDusuario ob)
    {
        // aqui se pone el link de la base de datos de la nube
        StringRequest request = new StringRequest(Request.Method.POST, "https://compbdzoo.000webhostapp.com/insertarUsuario.php",
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response)
                    {

                        if(response.equalsIgnoreCase("Registro con éxito"))
                        {
                            Toast.makeText(ActivityRegistrar.this, "Registro no fue éxitoso", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            Toast.makeText(ActivityRegistrar.this, "El registro fue exitoso por favor ingrese", Toast.LENGTH_SHORT).show();
                            Intent intent1 = new Intent(ActivityRegistrar.this,ActivityIngresar.class);
                            startActivity(intent1);
                        }

                    }
                }, new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        Toast.makeText(ActivityRegistrar.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }

        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError
            {
                //con map se envian los parametros por el link con el methodo post
                Map<String,String> params = new HashMap<String,String>();
                params.put("id_usuario",ob.getId());
                params.put("nombre",ob.getNombre());
                params.put("correo",ob.getCorreo());
                params.put("password",ob.getPassword());
                params.put("Tel",ob.getTel());

                return params;
            }
        };

        //ejecutando la direccion ip dirigida a la base de datos
        RequestQueue requestQueue = Volley.newRequestQueue(ActivityRegistrar.this);
        requestQueue.add(request);
    }

    private void ListaUsuarios()
    {
        String url="https://compbdzoo.000webhostapp.com/SelectUsuario.php";
        StringRequest request = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response)
                    {
                        try{
                            JSONObject jsonObject = new JSONObject(response);
                            String exito = jsonObject.getString("exito");
                            JSONArray jsonArray = jsonObject.getJSONArray("datos");

                            if(exito.equals("1"))
                            {
                                lista.clear();
                                CRUDusuario ob;
                                for(int i=0;i<jsonArray.length();i++)
                                {
                                    JSONObject object = jsonArray.getJSONObject(i);
                                    String id = object.getString("id_usuario");
                                    String nombre = object.getString("nombre");
                                    String correo = object.getString("correo");
                                    String password = object.getString("password");
                                    String Tel = object.getString("Tel");
                                    ob = new CRUDusuario(id,nombre,correo,password,Tel);
                                    lista.add(ob);
                                }
                            }else
                            {
                                Toast.makeText(ActivityRegistrar.this,"ERROR EN IF", Toast.LENGTH_LONG).show();
                            }

                        }
                        catch (JSONException e)
                        {
                            Toast.makeText(ActivityRegistrar.this,"Error al obtener la lista: " + e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                }, new Response.ErrorListener()
        {
            @Override
            public void onErrorResponse(VolleyError error)
            {
                Toast.makeText(ActivityRegistrar.this, "ERROR EN RESPONSE: "+error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
    }

    private boolean comprobarUsuario(String correo)
    {
        boolean estado=false;
            for (CRUDusuario ob: lista)
            {
                if (ob.getCorreo().toString().equals(correo))
                {
                    estado=true;
                    break;
                }
            }
       return estado;
    }
}