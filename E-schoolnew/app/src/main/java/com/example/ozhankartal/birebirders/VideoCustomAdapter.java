package com.example.ozhankartal.birebirders;

/**
 * Created by aksoyhakn on 10.12.2016.
 */

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by aksoyhakn on 9.12.2016.
 */

public class VideoCustomAdapter extends ArrayAdapter<VideoModel> {

    ArrayList<VideoModel> products;
    Context context;
    int resource;

    public VideoCustomAdapter(Context context, int resource, ArrayList<VideoModel> products) {
        super(context, resource, products);
        this.products=products;
        this.context=context;
        this.resource=resource;

    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){

            LayoutInflater layoutInflater =(LayoutInflater) getContext().getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.video_satir_liste,null,true);

        }
        VideoModel product=getItem(position);

        TextView txtName=(TextView)convertView.findViewById(R.id.txtVideoName);
        txtName.setText(product.getName());

        TextView txtYazar=(TextView)convertView.findViewById(R.id.txtVideoYazar);
        txtYazar.setText(product.getYazar());




        return convertView;
    }
}