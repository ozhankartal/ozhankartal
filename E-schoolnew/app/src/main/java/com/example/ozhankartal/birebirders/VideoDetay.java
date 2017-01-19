package com.example.ozhankartal.birebirders;

import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

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

public class VideoDetay extends AppCompatActivity {
    TextView videoad,videoaciklama,videoyazar,videourl;
    Toolbar videoToolbar;
    VideoView videoview;
    AlertDialog.Builder builder;
    String adi,aciklama,url,yazar;
    int durum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_detay);

        videoToolbar=(Toolbar)findViewById(R.id.toolbar);
        videoToolbar.setTitle("Detay");
        setSupportActionBar(videoToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        videoad=(TextView)findViewById(R.id.videoad);
        videoaciklama=(TextView)findViewById(R.id.videoaciklama);
        videoyazar=(TextView)findViewById(R.id.videoyazar);

        videoview=(VideoView)findViewById(R.id.videoresim);
        final MediaController  mediaController = new MediaController(this);
         mediaController.setAnchorView(videoview);


        final String id=getIntent().getExtras().getString("id");


        builder = new AlertDialog.Builder(VideoDetay.this);
        builder.setTitle("Video Detay");
        builder.setMessage("");

        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        StringRequest sr = new StringRequest(Request.Method.POST, "http://192.168.1.69/Videodetay.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {

                    JSONObject obj = new JSONObject(response);
                    JSONObject obj1= obj.getJSONObject("veriler");

                    adi = obj1.getString("adi");
                    aciklama= obj1.getString("aciklama");
                    url= obj1.getString("url");
                    yazar= obj1.getString("yazar");


                    videoyazar.setText(yazar);
                    videoad.setText(adi);
                    videoaciklama.setText(aciklama);

                    String videoPath=url;
                    Uri uri= Uri.parse(videoPath);
                    mediaController.setAnchorView(videoview);
                    videoview.setMediaController(mediaController);
                    videoview.setVideoURI(uri);
                    videoview.start();



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
