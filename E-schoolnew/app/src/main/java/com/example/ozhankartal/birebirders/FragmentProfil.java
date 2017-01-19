package com.example.ozhankartal.birebirders;


import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
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

import de.hdodenhof.circleimageview.CircleImageView;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentProfil extends Fragment implements View.OnClickListener {

    int durum,tip;
    AlertDialog.Builder mBuilder;
    String mesaj,kadi,Psifre,Padi,khakkinda,kresim;

    TextView tadi,thakkinda;
    CircleImageView resim;
    int PICK_IMAGE_REQUEST = 1;


    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.fragment_profil, container, false);
        ImageButton profil=(ImageButton)rootView.findViewById(R.id.imageButton3);
        tadi=(TextView)rootView.findViewById(R.id.textView6);
        thakkinda=(TextView)rootView.findViewById(R.id.textView14);


        ImageButton bakiye=(ImageButton)rootView.findViewById(R.id.imageButton5);
        ImageButton iletisim=(ImageButton)rootView.findViewById(R.id.imageButton6);
        resim=(CircleImageView)rootView.findViewById(R.id.profile_image);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        Padi= preferences.getString("kadi", null);
        Psifre= preferences.getString("sifre", null);

        resim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new LoadImage().execute();
            }
        });


        RequestQueue queue = Volley.newRequestQueue(getContext());
        StringRequest sr = new StringRequest(Request.Method.POST, "http://eticaret.merkezyazilim.com/service/giris", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {


                try {

                    JSONObject obj = new JSONObject(response);
                    durum = obj.getInt("durum");
                    mesaj = obj.getString("mesaj");
                    tip = obj.getInt("type");
                    if (durum == 0) {

                            Toast.makeText(getContext(),"Veri çekemedik !!", Toast.LENGTH_SHORT).show();

                    } else if (durum == 1) {

                        kadi=obj.getString("adi");
                        tadi.setText(kadi);

                        khakkinda=obj.getString("hakkinda");
                        thakkinda.setText(khakkinda);

                        kresim= obj.getString("resim");
                        Picasso.with(getContext()).load(kresim).into(resim);






                    }


                    Log.d("My App", obj.toString());

                } catch (Throwable t) {
                    Log.e("My App", "Could not parse malformed JSON: \"" + response + "\"");
                    Toast.makeText(getContext(), "Bir problemle karşılaştık, Hatayı bize bildirin", Toast.LENGTH_SHORT).show();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), "SQLite ile hata durumları eklenecek", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();

                params.put("kadi",Padi);
                params.put("sifre",Psifre);
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





        profil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mBuilder=new AlertDialog.Builder(getContext());
                View hView = inflater.inflate(R.layout.profil_bilgileri,null);

                final TextView adim=(TextView)hView.findViewById(R.id.textView20);
                final TextView soyadi=(TextView)hView.findViewById(R.id.textView22);
                TextView dgünü=(TextView)hView.findViewById(R.id.textView24);
                dgünü.setText("12.05.1994");
                final TextView gmail=(TextView)hView.findViewById(R.id.textView26);
                TextView face=(TextView)hView.findViewById(R.id.textView28);
                face.setText("ozhnkrtl");
                Button düzenle=(Button)hView.findViewById(R.id.button9);

                düzenle.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getContext(), "Yapım asamasında !!", Toast.LENGTH_SHORT).show();
                    }
                });

                RequestQueue queue = Volley.newRequestQueue(getContext());
                StringRequest sr = new StringRequest(Request.Method.POST, "http://eticaret.merkezyazilim.com/service/giris", new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {


                        try {

                            JSONObject obj = new JSONObject(response);
                            durum = obj.getInt("durum");
                            mesaj = obj.getString("mesaj");
                            tip = obj.getInt("type");
                            if (durum == 0) {

                                Toast.makeText(getContext(),"Veri çekemedik !!", Toast.LENGTH_SHORT).show();

                            } else if (durum == 1) {

                                String name=obj.getString("adi");
                                adim.setText(name);
                                String surname=obj.getString("soyadi");
                                soyadi.setText(surname);
                                String email=obj.getString("email");
                                gmail.setText(email);





                            }


                            Log.d("My App", obj.toString());

                        } catch (Throwable t) {
                            Log.e("My App", "Could not parse malformed JSON: \"" + response + "\"");
                            Toast.makeText(getContext(), "Bir problemle karşılaştık, Hatayı bize bildirin", Toast.LENGTH_SHORT).show();
                        }


                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getContext(), "SQLite ile hata durumları eklenecek", Toast.LENGTH_SHORT).show();
                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() {
                        Map<String, String> params = new HashMap<String, String>();

                        params.put("kadi",Padi);
                        params.put("sifre",Psifre);
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



                mBuilder.setView(hView);
                AlertDialog dialog1=mBuilder.create();
                dialog1.show();



            }
        });


        bakiye.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mBuilder=new AlertDialog.Builder(getContext());
                View bView = inflater.inflate(R.layout.bakiye_bilgileri,null);
                final TextView bakiye=(TextView)bView.findViewById(R.id.textView12);
                final TextView hesapsoru=(TextView)bView.findViewById(R.id.textView16);
                TextView para=(TextView)bView.findViewById(R.id.textView13);
                Button paracek=(Button)bView.findViewById(R.id.button8);

                paracek.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getContext(),"Yapım asamasında !!", Toast.LENGTH_SHORT).show();
                    }
                });


                mBuilder.setView(bView);
                AlertDialog dialog1=mBuilder.create();
                dialog1.show();



            }
        });

        iletisim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Yapım asamasında !", Toast.LENGTH_SHORT).show();
            }
        });


        return rootView;
    }




    //
    //FOTOĞRAF SEÇİM
    //
    public class LoadImage extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(galleryIntent,PICK_IMAGE_REQUEST);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == getActivity().RESULT_OK && data != null && data.getData() != null) {
            Uri selectedImage = data.getData();
            Toast.makeText(getActivity(),"SA",Toast.LENGTH_LONG);
            resim.setImageURI(selectedImage);
        }
    }

    @Override
    public void onClick(View v) {

    }


}
