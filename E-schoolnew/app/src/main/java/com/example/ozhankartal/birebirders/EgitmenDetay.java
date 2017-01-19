package com.example.ozhankartal.birebirders;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
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

import de.hdodenhof.circleimageview.CircleImageView;

public class EgitmenDetay extends AppCompatActivity {
    Toolbar egitmenToolbar;
    CircleImageView resim;
    TextView egitmendetayadi, egitmendetaymail, egitmendetayuni, egitmendetayhesap;
    String adi,okul,image,hesap;
    AlertDialog.Builder builder;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_egitmen_detay);

        egitmenToolbar = (Toolbar) findViewById(R.id.toolbar);
        egitmenToolbar.setTitle("Egitmen Detay");
        setSupportActionBar(egitmenToolbar);

        resim = (CircleImageView) findViewById(R.id.profile_image);
        egitmendetayadi = (TextView) findViewById(R.id.txtEgitmenDetayAdi);
        egitmendetaymail = (TextView) findViewById(R.id.txtEgitmenDetayMail);
        egitmendetayuni = (TextView) findViewById(R.id.txtEgitmenDetayUniver);
        egitmendetayhesap = (TextView) findViewById(R.id.txtEgitmenDetayLinkedin);

        final String id=getIntent().getExtras().getString("id");

        builder = new AlertDialog.Builder(EgitmenDetay.this);
        builder.setTitle("Egitmen Detay");
        builder.setMessage("");

        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        StringRequest sr = new StringRequest(Request.Method.POST, "http://192.168.1.69/EgitmenDetay.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {

                    JSONObject obj = new JSONObject(response);
                    JSONObject obj1= obj.getJSONObject("veriler");


                    image= obj1.getString("image");
                    adi = obj1.getString("adi");
                    okul= obj1.getString("okul");
                    hesap=obj1.getString("hesap");

                    egitmendetayuni.setText(okul);
                    egitmendetayadi.setText(adi);
                    egitmendetayhesap.setText(hesap);
                    Picasso.with(getApplicationContext()).load(image).into(resim);


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
