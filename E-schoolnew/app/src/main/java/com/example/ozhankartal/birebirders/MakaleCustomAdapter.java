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
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by aksoyhakn on 9.12.2016.
 */

public class MakaleCustomAdapter extends ArrayAdapter<MakaleModel> {

    ArrayList<MakaleModel> products;
    Context context;
    int resource;

    public MakaleCustomAdapter(Context context, int resource, ArrayList<MakaleModel> products) {
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
            convertView = layoutInflater.inflate(R.layout.makale_satir_liste,null,true);

        }
        MakaleModel product=getItem(position);
        ImageView ımageView=(ImageView)convertView.findViewById(R.id.imageViewProduct);
        Picasso.with(context).load(product.getImage()).into(ımageView);

        TextView txtName=(TextView)convertView.findViewById(R.id.txtName);
        txtName.setText(product.getName());


        TextView txtYazar=(TextView)convertView.findViewById(R.id.txtYazar);
        txtYazar.setText(product.getYazar());
        return convertView;
    }
}