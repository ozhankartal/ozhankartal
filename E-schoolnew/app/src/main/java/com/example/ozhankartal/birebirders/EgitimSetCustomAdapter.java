package com.example.ozhankartal.birebirders;

/**
 * Created by aksoyhakn on 11.12.2016.
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

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by aksoyhakn on 9.12.2016.
 */

public class EgitimSetCustomAdapter extends ArrayAdapter<EgitimSetModel> {

    ArrayList<EgitimSetModel> products;
    Context context;
    int resource;

    public EgitimSetCustomAdapter(Context context, int resource, ArrayList<EgitimSetModel> products) {
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
            convertView = layoutInflater.inflate(R.layout.egitimset_satir_liste,null,true);

        }
        EgitimSetModel product=getItem(position);
        ImageView ımageView=(ImageView)convertView.findViewById(R.id.imageViewEgitim);
        Picasso.with(context).load(product.getImage()).into(ımageView);

        TextView txtName=(TextView)convertView.findViewById(R.id.txtEgitimName);
        txtName.setText(product.getName());

        TextView txtPrice=(TextView)convertView.findViewById(R.id.txtEgitimYazar);
        txtPrice.setText(product.getYazar());

        TextView txtUcret=(TextView)convertView.findViewById(R.id.txtEgtimUcret);
        txtUcret.setText(product.getUcret());


        return convertView;
    }
}