package com.example.ozhankartal.birebirders;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
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

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Iletisim extends AppCompatActivity {
    Toolbar iToolbar;
    EditText konu,adsoyad,mail,mesaj;
    AlertDialog.Builder builder;
    Button gönder;
    String konuAldik,adsoyadAldik,mailAldik,mesajAldik,ErrorString="";;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iletisim);

        iToolbar = (Toolbar) findViewById(R.id.toolbar5);
        iToolbar.setTitle("Iletisim");
        setSupportActionBar(iToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        konu = (EditText) findViewById(R.id.editText10);
        adsoyad = (EditText) findViewById(R.id.editText11);
        mail = (EditText) findViewById(R.id.editText12);
        mesaj = (EditText) findViewById(R.id.editText13);
        gönder = (Button) findViewById(R.id.button10);


        gönder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                konuAldik = konu.getText().toString().trim();
                adsoyadAldik = adsoyad.getText().toString().trim();
                mailAldik = mail.getText().toString().trim();
                mesajAldik = mesaj.getText().toString().trim();

                if(konuAldik.matches("") || adsoyadAldik.matches("") ||mailAldik.matches("") || mesajAldik.matches("")   ){
                    ErrorString="";
                    if(konuAldik.matches("")){

                        ErrorString += "* Email Boş Lütfen Doldorunuz\n";

                    }
                    if(adsoyadAldik.matches("")){

                        ErrorString += "* Adiniz Boş Lütfen Doldorunuz\n";

                    }
                    if(mailAldik.matches("")){

                        ErrorString += "* Soyadiniz Boş Lütfen Doldorunuz\n";

                    }
                    if(mesajAldik.matches("")){

                        ErrorString += "* Şifreniz Boş Lütfen Doldorunuz\n";

                    }


                    Toast.makeText(Iletisim.this,ErrorString, Toast.LENGTH_SHORT).show();

                }

                else{

                    RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                    StringRequest sr = new StringRequest(Request.Method.POST,"http://192.168.1.69/Iletisim.php", new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {


                            try {

                                JSONObject obj = new JSONObject(response);

                                Toast.makeText(Iletisim.this, "Veri eklendi", Toast.LENGTH_SHORT).show();


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
                            params.put("konu", konuAldik);
                            params.put("adsoyad", adsoyadAldik);
                            params.put("mail", mailAldik);
                            params.put("mesaj", mesajAldik);

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









            }
        });



    }



    public  void alertMesaj(String mesaj){
        builder.setMessage(mesaj);
        builder.show();


    }


}
