package com.example.ozhankartal.birebirders;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

public class Makale extends AppCompatActivity {
    Toolbar mToolbar;
    ArrayList<MakaleModel> arrayList;
    ListView lv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_makale);

        mToolbar = (Toolbar) findViewById(R.id.toolbar4);
        mToolbar.setTitle("E-Makale");
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        arrayList = new ArrayList<>();
        lv = (ListView) findViewById(R.id.listView);

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                new ReadJSON().execute("http://192.168.1.69/makale.php");
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
                JSONArray jsonArray = jsonObject.getJSONArray("veriler");

                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject productObject = jsonArray.getJSONObject(i);
                    arrayList.add(new MakaleModel(
                            productObject.getString("image"),
                            productObject.getString("adi"),
                            productObject.getString("aciklama"),
                            productObject.getInt("id"),
                            productObject.getString("yazar")
                    ));
                }

            } catch (JSONException e) {
                e.printStackTrace();

            }
            final MakaleCustomAdapter adapter = new MakaleCustomAdapter(
                    getApplicationContext(), R.layout.makale_satir_liste, arrayList
            );
            lv.setAdapter(adapter);

            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    MakaleModel p = adapter.getItem(position);

                   Intent intent= new Intent(getApplicationContext(),MakaleDetay.class);
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

