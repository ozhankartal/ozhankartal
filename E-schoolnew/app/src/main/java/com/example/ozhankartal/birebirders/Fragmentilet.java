package com.example.ozhankartal.birebirders;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

import de.hdodenhof.circleimageview.CircleImageView;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragmentilet extends Fragment implements View.OnClickListener {

    int durum,tip;
    AlertDialog.Builder mBuilder;
    String konu,ad,mesaj,mail;
    String konuAldik,adsoyadAldik,mailAldik,mesajAldik,ErrorString="";;
    TextView tadi,thakkinda;
    CircleImageView resim;



    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.fragment_fragmentilet, container, false);



        final EditText konu=(EditText)rootView.findViewById(R.id.editText10);
        final EditText ad=(EditText)rootView.findViewById(R.id.editText11);
        final EditText mesaj=(EditText)rootView.findViewById(R.id.editText13);
        final EditText mail=(EditText)rootView.findViewById(R.id.editText12);
        Button gönder = (Button)rootView.findViewById(R.id.button10);

        gönder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                konuAldik = konu.getText().toString().trim();
                adsoyadAldik = ad.getText().toString().trim();
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


                    Toast.makeText(getContext(),ErrorString, Toast.LENGTH_SHORT).show();

                }

                else{

                    RequestQueue queue = Volley.newRequestQueue(getContext());
                    StringRequest sr = new StringRequest(Request.Method.POST,"http://192.168.1.69/Iletisim.php", new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {


                            try {

                                JSONObject obj = new JSONObject(response);

                                Toast.makeText(getContext(), "Mesaj gönderildi.", Toast.LENGTH_SHORT).show();



                                Log.d("My App", obj.toString());

                            } catch (Throwable t) {
                                Log.e("My App", "Could not parse malformed JSON: \"" + response + "\"");

                            }


                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

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







        return rootView;
    }



    @Override
    public void onClick(View v) {

    }


}
