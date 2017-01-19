package com.example.ozhankartal.birebirders;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class KayitOl extends AppCompatActivity {

    Button kButton;
    AlertDialog.Builder builder;
    TextView kTextRegister,kTextGirisgit;
    EditText kEditKullanici,kEditEmail,kEditPassword,kEditAdi,kEditSoyadi,kEditTelefon;
    String RegisterAdi,RegisterSoyadi,RegisterKadi,RegisterSifre,RegisterTelno,RegisterEmail,mesaj,ErrorString="";
    int durum,tip ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kayit_ol);


        builder = new AlertDialog.Builder(KayitOl.this);
        builder.setTitle("Kayit ol");
        builder.setMessage("");


        kButton=(Button)findViewById(R.id.button7);
        kTextRegister=(TextView)findViewById(R.id.textView3);
        kTextGirisgit=(TextView)findViewById(R.id.textView6);

        kEditKullanici=(EditText)findViewById(R.id.editText);
        kEditEmail=(EditText)findViewById(R.id.editText2);
        kEditPassword=(EditText)findViewById(R.id.editText5);
        kEditAdi=(EditText)findViewById(R.id.editText6);
        kEditSoyadi=(EditText)findViewById(R.id.editText7);
        kEditTelefon=(EditText)findViewById(R.id.editText8);

        kButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                RegisterAdi=kEditAdi.getText().toString().trim();
                RegisterSoyadi=kEditSoyadi.getText().toString().trim();
                RegisterKadi=kEditKullanici.getText().toString().trim();
                RegisterSifre=kEditPassword.getText().toString().trim();
                RegisterEmail=kEditEmail.getText().toString().trim();
                RegisterTelno=kEditTelefon.getText().toString().trim();

                if(RegisterEmail.matches("") || RegisterAdi.matches("") ||RegisterSoyadi.matches("") || RegisterSifre.matches("")   ){
                    ErrorString="";
                    if(RegisterEmail.matches("")){

                        ErrorString += "* Email Boş Lütfen Doldorunuz\n";

                    }
                    if(RegisterAdi.matches("")){

                        ErrorString += "* Adiniz Boş Lütfen Doldorunuz\n";

                    }
                    if(RegisterSoyadi.matches("")){

                        ErrorString += "* Soyadiniz Boş Lütfen Doldorunuz\n";

                    }
                    if(RegisterSifre.matches("")){

                        ErrorString += "* Şifreniz Boş Lütfen Doldorunuz\n";

                    }
                    if(RegisterTelno.matches("")){

                        ErrorString += "* Telefon Numarnaız Boş Lütfen Doldorunuz\n";

                    }

                    Toast.makeText(KayitOl.this,ErrorString, Toast.LENGTH_SHORT).show();

                }

                else{

                    RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                    StringRequest sr = new StringRequest(Request.Method.POST,"http://eticaret.merkezyazilim.com/service/kayit", new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {


                          try {

                                JSONObject obj = new JSONObject(response);
                                durum = obj.getInt("durum");
                                tip=obj.getInt("type");
                                mesaj=obj.getString("mesaj");
                                if(durum==0){

                                    if(tip==1){
                                        alertMesaj("Veriler Gönderilemedi Lütfen Daha Sonra Tekrar Deneyin");

                                    }else if(tip==2){

                                        alertMesaj(mesaj);

                                    }else if(tip==3){

                                        alertMesaj("Sunucuya Erişilemedi");
                                    }
                                    else if(tip==4){
                                        alertMesaj("Kullanıcı Adı ve Şifre Hatalı");

                                    }else if(tip==5){

                                        alertMesaj(mesaj);
                                    }

                                }else if(durum==1){


                                        Intent RegisterdenAnasayfaya = new Intent(getApplicationContext(),AnaSayfa.class);
                                        startActivity(RegisterdenAnasayfaya);

                                }


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
                            params.put("adi",RegisterAdi);
                            params.put("soyadi",RegisterSoyadi);
                            params.put("kadi",RegisterKadi);
                            params.put("sifre",RegisterSifre);
                            params.put("email",RegisterEmail);
                            params.put("tel",RegisterTelno);

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


        kTextGirisgit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();

            }
        });

    }



    public  void alertMesaj(String mesaj){
        builder.setMessage(mesaj);
        builder.show();


    }


}
