package com.example.ozhankartal.birebirders;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentAnasayfa extends Fragment {


    public FragmentAnasayfa() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootView = inflater.inflate(R.layout.fragment_anasayfa, container, false);

        Button makale=(Button)rootView.findViewById(R.id.makale11);
        Button video=(Button)rootView.findViewById(R.id.video11);
        Button etkinlik=(Button)rootView.findViewById(R.id.etkinlik11);
        Button eitm=(Button)rootView.findViewById(R.id.egitim11);

        makale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(getContext(),Makale.class);
                startActivity(intent1);

            }
        });

        video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2=new Intent(getContext(),Video.class);
                startActivity(intent2);
            }
        });

        etkinlik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3=new Intent(getContext(),Duyuru.class);
                startActivity(intent3);
            }
        });

        eitm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent4=new Intent(getContext(),Egitim.class);
                startActivity(intent4);
            }
        });

        return rootView;

    }

}
