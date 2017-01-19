package com.example.ozhankartal.birebirders;

/**
 * Created by aksoyhakn on 13.12.2016.
 */
import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by aksoyhakn on 9.12.2016.
 */

public class EgitmenCustomAdapter extends ArrayAdapter<EgitmenModel> {

    ArrayList<EgitmenModel> products;
    Context context;
    int i=0;
    int resource;

    public EgitmenCustomAdapter(Context context, int resource, ArrayList<EgitmenModel> products) {
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
            convertView = layoutInflater.inflate(R.layout.egitmen_satir_liste,null,true);

        }
        EgitmenModel product=getItem(position);

        TextView txtName=(TextView)convertView.findViewById(R.id.txtEgitmenName);
        txtName.setText(product.getName());

        CircleImageView resim=(CircleImageView)convertView.findViewById(R.id.profile_image);
        Picasso.with(context).load(product.getImage()).into(resim);




        return convertView;
    }
}
