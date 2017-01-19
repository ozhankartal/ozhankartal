package com.example.ozhankartal.birebirders;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class Egitim extends AppCompatActivity {

    Toolbar eToolbar;
    ArrayList<EgitimSetModel> arrayList;
    ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_egitim);

        eToolbar=(Toolbar)findViewById(R.id.toolbar6);
        eToolbar.setTitle("E-Egitim Setleri");
        setSupportActionBar(eToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        arrayList = new ArrayList<>();
        lv = (ListView) findViewById(R.id.listView);

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                new ReadJSON().execute("http://192.168.1.69/Egitimseti.php");
            }
        });
    }

    class ReadJSON extends AsyncTask<String, Integer, String> {

        @Override
        protected String doInBackground(String... params) {
            return readURL(params[0]);
        }

        @Override
        protected void onPostExecute(String content) {
            try {
                JSONObject jsonObject = new JSONObject(content);
                JSONArray jsonArray =  jsonObject.getJSONArray("veriler");

                for(int i =0;i<jsonArray.length(); i++){
                    JSONObject productObject = jsonArray.getJSONObject(i);
                    arrayList.add(new EgitimSetModel(
                            productObject.getString("image"),
                            productObject.getString("adi"),
                            productObject.getString("ucret"),
                            productObject.getInt("id"),
                            productObject.getString("yazar")
                    ));
                }

            } catch (JSONException e) {
                e.printStackTrace();

            }
            final EgitimSetCustomAdapter adapter = new EgitimSetCustomAdapter(
                    getApplicationContext(), R.layout.egitimset_satir_liste, arrayList
            );
            lv.setAdapter(adapter);

            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    EgitimSetModel p =adapter.getItem(position);

                    Intent intent= new Intent(getApplicationContext(),EgitimsetDetay.class);
                    intent.putExtra("id",p.getId()+"");
                    startActivity(intent);

                }
            });
        }
    }


    private static String readURL(String theUrl) {
        StringBuilder content = new StringBuilder();
        try {
            // create a url object
            URL url = new URL(theUrl);
            // create a urlconnection object
            URLConnection urlConnection = url.openConnection();
            // wrap the urlconnection in a bufferedreader
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String line;
            // read from the urlconnection via the bufferedreader
            while ((line = bufferedReader.readLine()) != null) {
                content.append(line + "\n");
            }
            bufferedReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return content.toString();
    }



    }

