package com.example.proyectofinal;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class pruebas extends AppCompatActivity
{
private Button btn;
private TextView txtMostrar;
private EditText txtCorreo;
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
    RequestQueue requestQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pruebas);

        txtCorreo = (EditText) findViewById(R.id.txtCorreooo);
        txtMostrar = (TextView) findViewById(R.id.txtMOstrar);
        btn = (Button) findViewById(R.id.button);

        btn.setOnClickListener(this::Onclick);
        requestQueue = Volley.newRequestQueue(this);
    }

    private void Onclick(View view)
    {
       String co= txtCorreo.getText().toString().trim();
        ListaUsuarios();
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
                        String tel;
                        try
                        {
                            tel = response.getString("Tel");
                            Toast.makeText(pruebas.this, "Telefono: "+ tel,Toast.LENGTH_SHORT).show();
                        } catch (JSONException e)
                        {
                            Toast.makeText(pruebas.this, "El usuario no existe"+ e.getMessage(),Toast.LENGTH_LONG).show();
                        }
                    }
                }, new Response.ErrorListener()
                    {
                        @Override
                        public void onErrorResponse(VolleyError error)
                        {
                            Toast.makeText(pruebas.this, "El usuario no existe o el correo es inválido por favor verifique",Toast.LENGTH_SHORT).show();
                        }
                    }
        );

        requestQueue.add(jsonObjectRequest);
    }

    private void ListaUsuarios()
    {
        String url="https://compbdzoo.000webhostapp.com/SelectUsuario.php";
        StringRequest request = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response)
                    {
                        try{
                            JSONObject jsonObject = new JSONObject(response);
                            String exito = jsonObject.getString("exito");
                            JSONArray jsonArray = jsonObject.getJSONArray("datos");

                            if(exito.equals("1"))
                            {

                                List<String> lis = new List<String>() {
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
                                    public Iterator<String> iterator() {
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
                                    public boolean add(String s) {
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
                                    public boolean addAll(@NonNull Collection<? extends String> c) {
                                        return false;
                                    }

                                    @Override
                                    public boolean addAll(int index, @NonNull Collection<? extends String> c) {
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
                                    public String get(int index) {
                                        return null;
                                    }

                                    @Override
                                    public String set(int index, String element) {
                                        return null;
                                    }

                                    @Override
                                    public void add(int index, String element) {

                                    }

                                    @Override
                                    public String remove(int index) {
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
                                    public ListIterator<String> listIterator() {
                                        return null;
                                    }

                                    @NonNull
                                    @Override
                                    public ListIterator<String> listIterator(int index) {
                                        return null;
                                    }

                                    @NonNull
                                    @Override
                                    public List<String> subList(int fromIndex, int toIndex) {
                                        return null;
                                    }
                                };
                                CRUDusuario ob;
                                for(int i=0;i<jsonArray.length();i++)
                                {
                                    JSONObject object = jsonArray.getJSONObject(i);
                                    String id = object.getString("id_usuario");
                                    String nombre = object.getString("nombre");
                                    String correo = object.getString("correo");
                                    String password = object.getString("password");
                                    String Tel = object.getString("Tel");
                                    lis.add( id+nombre+correo+password+Tel);

                                }

                            }else
                            {
                                Toast.makeText(pruebas.this,"ERROR EN IF", Toast.LENGTH_LONG).show();
                            }

                        }
                        catch (JSONException e)
                        {
                                Toast.makeText(pruebas.this,"Error al obtener la lista: " + e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                }, new Response.ErrorListener()
        {
            @Override
            public void onErrorResponse(VolleyError error)
            {
                Toast.makeText(pruebas.this, "ERROR EN RESPONSE: "+error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
    }
}