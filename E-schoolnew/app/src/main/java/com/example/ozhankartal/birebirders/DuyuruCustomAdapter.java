package com.example.ozhankartal.birebirders;

/**
 * Created by aksoyhakn on 13.12.2016.
 * EtkinlikEkleCustomAdapter
 */


import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
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

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by aksoyhakn on 9.12.2016.
 */

public class DuyuruCustomAdapter extends ArrayAdapter<DuyuruModel> {

    ArrayList<DuyuruModel> products;
    Context context;
    int i = 0;
    int resource;
    public String adi, aciklama, ucret, image, yazar;
    public int id;

    public DuyuruCustomAdapter(Context context, int resource, ArrayList<DuyuruModel> products) {
        super(context, resource, products);
        this.products = products;
        this.context = context;
        this.resource = resource;

    }

    @NonNull
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {

            LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.duyuru_satir_liste, null, true);

        }
        final DuyuruModel product = getItem(position);

        TextView txtName = (TextView) convertView.findViewById(R.id.txtDuyuru);
        txtName.setText(product.getName());

        TextView txtClock = (TextView) convertView.findViewById(R.id.txtDuyuruName);
        txtClock.setText(product.getClock());

        TextView txtYazar = (TextView) convertView.findViewById(R.id.txtDuyuruAciklama);
        txtYazar.setText(product.getPrice());

        final TextView txtktl = (TextView) convertView.findViewById(R.id.txtDuyurukatil);


        final ImageView bt = (ImageView) convertView.findViewById(R.id.imageView1);
        final long a = getItemId(position) + 1;

        bt.setOnClickListener(new View.OnClickListener() {
            @Override


            public void onClick(View v) {
                i++;


                if (i == 1) {

                    txtktl.setText("Katilacak");
                    bt.setImageResource(R.drawable.tick);

                    RequestQueue queue = Volley.newRequestQueue(getContext());
                    StringRequest sr = new StringRequest(Request.Method.POST, "http://localhost.com/Katiletkinlik.php", new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {


                            try {

                                JSONObject obj = new JSONObject(response);
                                Toast.makeText(context, "Etkinliğe katılacak :D ", Toast.LENGTH_SHORT).show();


                                Log.d("My App", obj.toString());

                            } catch (Throwable t) {
                                Log.e("My App", "Could not parse malformed JSON: \"" + response + "\"");

                            }


                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    }) {
                        @Override
                        protected Map<String, String> getParams() {
                            Map<String, String> params = new HashMap<String, String>();

                            params.put("id1", String.valueOf(a));

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


                } else if (i == 2) {
                    bt.setImageResource(R.drawable.katil);
                    txtktl.setText("");


                    RequestQueue queue = Volley.newRequestQueue(getContext());
                    StringRequest sr = new StringRequest(Request.Method.POST, "http://192.168.1.69/Siletkinlik.php", new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {


                            try {

                                JSONObject obj = new JSONObject(response);


                                Log.d("My App", obj.toString());

                            } catch (Throwable t) {
                                Log.e("My App", "Could not parse malformed JSON: \"" + response + "\"");

                            }


                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    }) {
                        @Override
                        protected Map<String, String> getParams() {
                            Map<String, String> params = new HashMap<String, String>();

                            params.put("id1", String.valueOf(a));

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

                    Toast.makeText(context, "Etkinliğe Katılmıcak :( ", Toast.LENGTH_SHORT).show();


                    i = 0;
                }


            }


        });


        return convertView;
    }
}
