package com.example.proyectofinal.Modelo;

import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.proyectofinal.ActivityRegistrar;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

public class CRUDusuario extends ActivityRegistrar
{
    private String id,nombre,correo, password,tel;

    public CRUDusuario(String id, String nombre, String correo, String password, String tel)
    {
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
        this.password = password;
        this.tel = tel;
    }
    public CRUDusuario(){ }

    public boolean insertar(CRUDusuario ob)
    {
        boolean[] estado = {false};

        StringRequest request = new StringRequest(Request.Method.POST, "https://compbdzoo.000webhostapp.com/insertarUsuario.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        if(response.equalsIgnoreCase("Registro con éxito")){
                           // Toast.makeText(ActivityRegistrar.this, "Registro no fue éxitoso", Toast.LENGTH_SHORT).show();
                            estado[0]=false;
                        }
                        else{
                            //Toast.makeText(ActivityRegistrar.this, "El registro fue exitoso por favor ingrese", Toast.LENGTH_SHORT).show();
                            estado[0]=true;
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {


            }
        }

        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String,String> params = new HashMap<String,String>();
                params.put("id_usuario",ob.getId());
                params.put("nombre",ob.getNombre());
                params.put("correo",ob.getCorreo());
                params.put("password",ob.getPassword());
                params.put("Tel",ob.getTel());

                return params;
            }
        };


        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);


        return estado[0];
    }

    public  void Editar(CRUDusuario ob)
    {

    }

    public boolean ComprobarExistencia()
    {
        return true;
    }

    public List<CRUDusuario> ListaUsuarios()
    {
        List<CRUDusuario> lista = new List<CRUDusuario>() {
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

        return lista;
    }

    public boolean Login(String correo, String password)
    {


        return false;
    }

    public String TOSTRING()
    {
        return "Id:"+this.getId()+"\n" +
                "nombre: "+this.getNombre()+"\n" +
                "correo: "+this.getPassword()+"\n" +
                "Tel: "+this.getTel()+"\n";
    }






    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }
}
