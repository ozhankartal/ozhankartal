package com.example.ozhankartal.birebirders;

import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class ProfilSayfa extends AppCompatActivity {

    //Defining Variables
    private Toolbar toolbar;


    private NavigationView navigationView;
    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil_sayfa);


        toolbar = (Toolbar) findViewById(R.id.toolbar7);
        toolbar.setTitle("Anasayfa");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        FragmentProfil fragment = new FragmentProfil();
        android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame,fragment);
        fragmentTransaction.commit();


        drawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.openDrawer, R.string.closeDrawer){

            @Override
            public void onDrawerClosed(View drawerView) {

                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {


                super.onDrawerOpened(drawerView);
            }
        };



        actionBarDrawerToggle.syncState();


        navigationView = (NavigationView) findViewById(R.id.navigation_view);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {


            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {


                if(menuItem.isChecked()) menuItem.setChecked(false);
                else menuItem.setChecked(true);


                drawerLayout.closeDrawers();


                switch (menuItem.getItemId()){


                    case  R.id.anasayfa:


                        FragmentAnasayfa fragment3 = new FragmentAnasayfa();
                        android.support.v4.app.FragmentTransaction fragmentTransaction2 = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction2.replace(R.id.frame,fragment3);
                        fragmentTransaction2.commit();
                        toolbar.setTitle("Anasayfa");
                        return  true;

                    case R.id.profil:

                        FragmentProfil fragment = new FragmentProfil();
                        android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.frame,fragment);
                        fragmentTransaction.commit();
                        toolbar.setTitle("Profil");
                        return true;

                    case R.id.egitim:

                        FragmentEgitimler fragment1 = new FragmentEgitimler();
                        android.support.v4.app.FragmentTransaction fragmentTransaction1 = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction1.replace(R.id.frame,fragment1);
                        fragmentTransaction1.commit();
                        toolbar.setTitle("Eğitmenler");
                        return true;

                    case R.id.satinal:
                        FragmentSatinal fragment11 = new FragmentSatinal();
                        android.support.v4.app.FragmentTransaction fragmentTransaction20 = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction20.replace(R.id.frame,fragment11);
                        fragmentTransaction20.commit();
                        toolbar.setTitle("Aldığım Setler");
                        return true;

                    case R.id.etkinlik:

                        FragmentKatilEtkinlik fragment111 = new FragmentKatilEtkinlik();
                        android.support.v4.app.FragmentTransaction fragmentTransaction200 = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction200.replace(R.id.frame,fragment111);
                        fragmentTransaction200.commit();
                        toolbar.setTitle("Katılcağım Etkinlik");
                        return true;

                    case R.id.iletim:

                        Fragmentilet fragment1111 = new Fragmentilet();
                        android.support.v4.app.FragmentTransaction fragmentTransaction2000 = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction2000.replace(R.id.frame,fragment1111);
                        fragmentTransaction2000.commit();
                        toolbar.setTitle("İletisim");
                        return true;



                    default:
                        Toast.makeText(getApplicationContext(),"Yapım asamasında !!",Toast.LENGTH_SHORT).show();
                        return true;

                }
            }
        });





    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.profill_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        switch (item.getItemId()){
            case R.id.cik:
                Toast.makeText(getApplicationContext(),"Çıkış yapıldı",Toast.LENGTH_SHORT).show();
                this.finish();
        }




        return super.onOptionsItemSelected(item);
    }
}
