package com.example.ozhankartal.birebirders;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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
import java.util.Random;

public class AnaSayfa extends AppCompatActivity {
    Toolbar mToolbar;
    AlertDialog.Builder mBuilder;
    Button evideo,egitim,eduyuru,emakale,eiletisim;
    String adi,sifre,mesaj,adicek,sifrecek;
    int durum,tip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ana_sayfa);

        Typeface mTypeface=Typeface.createFromAsset(getAssets(),"capture.ttf");
        TextView tv=(TextView)findViewById(R.id.textView2);
        tv.setTypeface(mTypeface);


        mToolbar=(Toolbar)findViewById(R.id.toolbar);
        mToolbar.setTitle("AnaSayfa");
        setSupportActionBar(mToolbar);

        evideo=(Button)findViewById(R.id.button);
        emakale=(Button)findViewById(R.id.button2);
        egitim=(Button)findViewById(R.id.button3);
        eduyuru=(Button)findViewById(R.id.button4);
        eiletisim=(Button)findViewById(R.id.button5);


    }


    public void tıkladık(View view){
        switch (view.getId()){

            case R.id.button:
                /*Intent intent=new Intent(AnaSayfa.this,Video.class);
                startActivity(intent);*/
                Toast.makeText(this, "Videoları görmek mi istiyorsun üye ol ? :D", Toast.LENGTH_SHORT).show();
                break;
            case R.id.button2:
                Intent intent1=new Intent(AnaSayfa.this,Makale.class);
                startActivity(intent1);

                break;
            case R.id.button3:
                Intent intent2=new Intent(AnaSayfa.this,Egitim.class);
                startActivity(intent2);
                break;
            case R.id.button4:
               /* Intent intent3=new Intent(AnaSayfa.this,Duyuru.class);
                startActivity(intent3);*/
                Toast.makeText(this, "Etkinlikleri merak mı edıyorsun ? :D Hadi gel üye ol ", Toast.LENGTH_SHORT).show();
                break;
            case R.id.button5:
                Intent intent4=new Intent(AnaSayfa.this,Iletisim.class);
                startActivity(intent4);
                break;

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater mMenuInflater= getMenuInflater();
        mMenuInflater.inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){

            case R.id.login:

                Random r =new Random();

                mBuilder =new AlertDialog.Builder(AnaSayfa.this);
                View mView = getLayoutInflater().inflate(R.layout.login,null);

                final EditText mLogin=(EditText)mView.findViewById(R.id.editText3);
                final EditText mPassword=(EditText)mView.findViewById(R.id.editText4);
                TextView mText=(TextView)mView.findViewById(R.id.textView5);

                TextView sayi1=(TextView)mView.findViewById(R.id.textView33);
                TextView sayi2=(TextView)mView.findViewById(R.id.textView36);

                EditText toplam=(EditText)mView.findViewById(R.id.editText16);
                String alinan= toplam.getText().toString().trim();




                int ilk = r.nextInt(5);
                int iki = r.nextInt(5);
                int sonuc= ilk+iki;
                String alinan2 = Integer.toString(sonuc);
                String alinan3= Integer.toString(ilk);
                String alinan4= Integer.toString(iki);


                sayi1.setText(alinan3);
                sayi2.setText(alinan4);

                if(alinan == alinan2){

                    Toast.makeText(this, "Doğru sonuc", Toast.LENGTH_SHORT).show();


                }else{

                   /* Toast.makeText(this, "Yanlış sonuc", Toast.LENGTH_SHORT).show();*/
                }






                Button giris =(Button)mView.findViewById(R.id.button6);

                giris.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        adi=mLogin.getText().toString().trim();
                        sifre=mPassword.getText().toString().trim();


                        if(adi.matches("")|| sifre.matches("")){
                            String error = null;

                            if (adi.matches("")) {

                              error="Lutfen Kullanıcı veya Sıfre  griniz";
                            }
                            if (sifre.matches("")) {

                                error="Lutfen sifre giriniz";

                            }

                            Toast.makeText(AnaSayfa.this,error, Toast.LENGTH_SHORT).show();



                        }

                        else{

                            RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                            StringRequest sr = new StringRequest(Request.Method.POST, "http://eticaret.merkezyazilim.com/service/giris", new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {


                                    try {

                                        JSONObject obj = new JSONObject(response);
                                        durum = obj.getInt("durum");
                                        mesaj = obj.getString("mesaj");
                                        tip = obj.getInt("type");
                                        if (durum == 0) {

                                            if (tip == 1) {
                                                Toast.makeText(AnaSayfa.this, "Veriler Gönderilemedi Lütfen Daha Sonra Tekrar Deneyin", Toast.LENGTH_SHORT).show();

                                            } else if (tip == 2) {

                                                Toast.makeText(AnaSayfa.this,mesaj, Toast.LENGTH_SHORT).show();

                                            } else if (tip == 3) {

                                                Toast.makeText(AnaSayfa.this, "Sunucuya Erişilemedi", Toast.LENGTH_SHORT).show();
                                            } else if (tip == 4) {
                                                Toast.makeText(AnaSayfa.this, "Kullanıcı Adı ve Şifre Hatalı", Toast.LENGTH_SHORT).show();

                                            } else if (tip == 5) {

                                                Toast.makeText(AnaSayfa.this,mesaj, Toast.LENGTH_SHORT).show();
                                            }

                                        } else if (durum == 1) {


                                            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                                            SharedPreferences.Editor editor = preferences.edit();

                                            editor.putString("kadi",adi);
                                            editor.putString("sifre",sifre);
                                            editor.commit();

                                            Toast.makeText(AnaSayfa.this, "Giriş yapıldı", Toast.LENGTH_SHORT).show();

                                            Intent intent=new Intent(AnaSayfa.this,ProfilSayfa.class);
                                            startActivity(intent);
                                            mLogin.setText("");
                                            mPassword.setText("");




                                        }


                                        Log.d("My App", obj.toString());

                                    } catch (Throwable t) {
                                        Log.e("My App", "Could not parse malformed JSON: \"" + response + "\"");
                                        Toast.makeText(AnaSayfa.this, "Bir problemle karşılaştık, Hatayı bize bildirin", Toast.LENGTH_SHORT).show();
                                    }


                                }
                            }, new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    Toast.makeText(AnaSayfa.this, "İnternet bağlantınızı kontrol edin", Toast.LENGTH_SHORT).show();
                                }
                            }) {
                                @Override
                                protected Map<String, String> getParams() {
                                    Map<String, String> params = new HashMap<String, String>();
                                    params.put("kadi", adi);
                                    params.put("sifre",sifre);


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




                    }
                });


                mText.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                      Intent intent5=new Intent(AnaSayfa.this,KayitOl.class);
                        startActivity(intent5);
                    }
                });


                mBuilder.setView(mView);
                AlertDialog dialog=mBuilder.create();
                dialog.show();
                return true;

            case R.id.hedef:

                mBuilder=new AlertDialog.Builder(AnaSayfa.this);
                View hView = getLayoutInflater().inflate(R.layout.custom_hakkinda,null);
                TextView hText=(TextView)hView.findViewById(R.id.textView7);
                hText.setText("Piyasada birçok forum, makale, eğitim seti ve video konferans hizmeti veren web sayfaları ve\n" +
                        "\n" +
                        "uygulamalar bulunmaktadır. E-School projesini yapmamızın sebebi makale, eğitim seti ve video konferans\n" +
                        "\n" +
                        "hizmeti veren web sayfaları ve uygulamaların hepsinin bir birinden bağımsız sistemler olmasıdır. E-School\n" +
                        "\n" +
                        "projesinde bu saymış olduğumuz sistemlerin tamamını tek bir platform da toplayarak, kullanıcıların sayfadan\n" +
                        "\n" +
                        "sayfaya veya uygulamadan uygulamaya geçmesini ortadan kaldırıp, istediklerine daha hızlı ulaşmalarını ve\n" +
                        "\n" +
                        "zamandan tasarruf etmeleri hedeflenmektedir.");

                mBuilder.setView(hView);
                AlertDialog dialog1=mBuilder.create();
                dialog1.show();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

    }


}
