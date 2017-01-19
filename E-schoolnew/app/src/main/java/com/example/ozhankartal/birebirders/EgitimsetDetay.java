package com.example.ozhankartal.birebirders;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class EgitimsetDetay extends AppCompatActivity {
    TextView txtAdi,txtAciklama,txtYazar,txtUcret;
    Toolbar egitimToolbar;
    ImageView imageView;
    AlertDialog.Builder builder;
    String adi,aciklama,image,yazar,ucret;
    Button satinal;
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_egitimset_detay);

        egitimToolbar=(Toolbar)findViewById(R.id.toolbar);
        egitimToolbar.setTitle("Detay");
        setSupportActionBar(egitimToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        txtAdi=(TextView)findViewById(R.id.egitimad);
        txtAciklama=(TextView)findViewById(R.id.egitimaciklama);
        txtYazar=(TextView)findViewById(R.id.egitimyazar);
        txtUcret=(TextView)findViewById(R.id.egitimucret);
        imageView=(ImageView)findViewById(R.id.egitimresim);
        satinal=(Button)findViewById(R.id.satinal);

        id=getIntent().getExtras().getString("id");


        builder = new AlertDialog.Builder(EgitimsetDetay.this);
        builder.setTitle("Egitim Detay");
        builder.setMessage("");

        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        StringRequest sr = new StringRequest(Request.Method.POST, "http://192.168.1.69/EgitimDetay.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {

                    JSONObject obj = new JSONObject(response);
                    JSONObject obj1= obj.getJSONObject("veriler");

                    adi = obj1.getString("adi");
                    aciklama= obj1.getString("aciklama");
                    ucret=obj1.getString("ucret");
                    image= obj1.getString("image");
                    yazar= obj1.getString("yazar");

                    txtYazar.setText(yazar);
                    txtAdi.setText(adi);
                    txtAciklama.setText(aciklama);
                    txtUcret.setText(ucret);
                    Picasso.with(getApplicationContext()).load(image).into(imageView);


                    Log.d("My App", obj.toString());

                } catch (Throwable t) {
                    Log.e("My App", "Could not parse malformed JSON: \"" + response + "\"");
                    alertMesaj("Bir problemle karşılaştık, Hatayı bize bildirin");
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                alertMesaj("SQLite ile hata durumları eklenecek");
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();

                params.put("id", id);

                return params;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Content-Type", "application/x-www-form-urlencoded");
                return params;
            }
        };
        queue.add(sr);





        satinal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                StringRequest sr = new StringRequest(Request.Method.POST,"http://hakan.merkezyazilim.com/Satinalekle.php", new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {


                        try {

                            JSONObject obj = new JSONObject(response);
                            Toast.makeText(EgitimsetDetay.this, "Satinalindi.", Toast.LENGTH_SHORT).show();



                            Log.d("My App", obj.toString());

                        } catch (Throwable t) {
                            Log.e("My App", "Could not parse malformed JSON: \"" + response + "\"");
                            alertMesaj("Bir problemle karşılaştık, Hatayı bize bildirin");
                        }


                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        alertMesaj("SQLite ile hata durumları eklenecek");
                    }
                }){
                    @Override
                    protected Map<String,String> getParams(){
                        Map<String,String> params = new HashMap<String, String>();
                        params.put("id1",id);
                        params.put("adi", adi);
                        params.put("aciklama", aciklama);
                        params.put("image", image);
                        params.put("yazar", yazar);

                        return params;
                    }

                    @Override
                    public Map<String, String> getHeaders() throws AuthFailureError {
                        Map<String,String> params = new HashMap<String, String>();
                        params.put("Content-Type","application/x-www-form-urlencoded");
                        return params;
                    }
                };
                queue.add(sr);




















            }
        });
    }


    public  void alertMesaj(String mesaj){
        builder.setMessage(mesaj);
        builder.show();


    }
}
