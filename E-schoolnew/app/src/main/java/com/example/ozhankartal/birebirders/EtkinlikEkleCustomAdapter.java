package com.example.ozhankartal.birebirders;

/**
 * Created by aksoyhakn on 13.12.2016.
 * EtkinlikEkleCustomAdapter
 */


import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by aksoyhakn on 9.12.2016.
 */

public class EtkinlikEkleCustomAdapter extends ArrayAdapter<DuyuruModel> {

    ArrayList<DuyuruModel> products;
    Context context;
    int i=0;
    int resource;
    public String adi,aciklama,ucret,image,yazar;
    public int id;

    public EtkinlikEkleCustomAdapter(Context context, int resource, ArrayList<DuyuruModel> products) {
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
            convertView = layoutInflater.inflate(R.layout.fragment_etkinlik_satir_liste,null,true);

        }
        final DuyuruModel product=getItem(position);

        TextView txtName=(TextView)convertView.findViewById(R.id.txtDuyuru);
        txtName.setText(product.getName());

        TextView txtClock=(TextView)convertView.findViewById(R.id.txtDuyuruName);
        txtClock.setText(product.getClock());

        TextView txtYazar=(TextView)convertView.findViewById(R.id.txtDuyuruAciklama);
        txtYazar.setText(product.getPrice());

        final TextView txtktl=(TextView)convertView.findViewById(R.id.txtDuyurukatil);


        final ImageView bt =(ImageView)convertView.findViewById(R.id.imageView1);


        return convertView;
    }
}
