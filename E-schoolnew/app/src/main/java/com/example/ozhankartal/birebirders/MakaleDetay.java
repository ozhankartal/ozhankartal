package com.example.ozhankartal.birebirders;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

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


public class MakaleDetay extends AppCompatActivity {

    TextView txtAdi,txtAciklama,txtYazar;
    Toolbar makaleToolbar;
    ImageView imageView;
    AlertDialog.Builder builder;
    String adi,aciklama,image,yazar;
    int durum;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_makale_detay);

        makaleToolbar=(Toolbar)findViewById(R.id.toolbar);
        makaleToolbar.setTitle("Detay");
        setSupportActionBar(makaleToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        txtAdi=(TextView)findViewById(R.id.gelenad);
        txtAciklama=(TextView)findViewById(R.id.gelenaciklama);
        txtYazar=(TextView)findViewById(R.id.gelenyazar);
        imageView=(ImageView)findViewById(R.id.gelenresim);

        final String id=getIntent().getExtras().getString("id");


        builder = new AlertDialog.Builder(MakaleDetay.this);
        builder.setTitle("Makale Detay");
        builder.setMessage("");

        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        StringRequest sr = new StringRequest(Request.Method.POST, "http://192.168.1.69/Detay.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {

                    JSONObject obj = new JSONObject(response);
                    JSONObject obj1= obj.getJSONObject("veriler");

                    adi = obj1.getString("adi");
                    aciklama= obj1.getString("aciklama");
                    image= obj1.getString("image");
                    yazar= obj1.getString("yazar");

                    txtYazar.setText(yazar);
                    txtAdi.setText(adi);
                    txtAciklama.setText(aciklama);
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

    }

    public  void alertMesaj(String mesaj){
        builder.setMessage(mesaj);
        builder.show();


    }
}
