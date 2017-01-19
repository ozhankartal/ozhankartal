package com.example.ozhankartal.birebirders;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
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


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentKatilEtkinlik extends Fragment {

    public ArrayList<DuyuruModel> arrayList;
    public ListView lv;

    public FragmentKatilEtkinlik() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootView = inflater.inflate(R.layout.fragment_katil_etkinlik, container, false);


        arrayList = new ArrayList<>();
        lv = (ListView) rootView.findViewById(R.id.listView);

        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                new ReadJSON().execute("http://192.168.1.69/Etkinlikgstr.php");
            }
        });



        return  rootView;
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
                JSONArray jsonArray = jsonObject.getJSONArray("KatilEtkinlik");

                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject productObject = jsonArray.getJSONObject(i);
                    arrayList.add(new DuyuruModel(
                            productObject.getString("adi"),
                            productObject.getString("clock"),
                            productObject.getString("aciklama"),
                            productObject.getInt("id")

                    ));
                }

            } catch (JSONException e) {
                e.printStackTrace();


            }

            final EtkinlikEkleCustomAdapter adapter = new EtkinlikEkleCustomAdapter(
                    getContext(), R.layout.fragment_etkinlik_satir_liste, arrayList
            );
            lv.setAdapter(adapter);

            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    DuyuruModel p = adapter.getItem(position);

                    /*Intent intent = new Intent(getContext(), EgitmenDetay.class);
                    intent.putExtra("id", p.getId() + "");
                    startActivity(intent);*/


                }
            });
        }


    }

    private  String readURL(String theUrl) {
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

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

    }

}