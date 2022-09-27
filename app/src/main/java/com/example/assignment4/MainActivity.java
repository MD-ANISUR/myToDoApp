package com.example.assignment4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Switch;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    MaterialToolbar toolbar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    FrameLayout frameLayout;
    Switch switcher;
    boolean nightMODE;
    SharedPreferences sp;
    SharedPreferences.Editor editor;



    my_task p1 = new my_task();
    pending_task p2 = new pending_task();
    inputPage_fragment p3 = new inputPage_fragment();
    completed_task p4 = new completed_task();
    themes t = new themes();
    help h = new help();
    about a = new about();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,p3).commit();


        switcher = findViewById(R.id.switcher);
        sp = getSharedPreferences("MODE", Context.MODE_PRIVATE);
        nightMODE = sp.getBoolean("night", false);

        if(nightMODE){
            switcher.setChecked(true);
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }

        switcher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(nightMODE){
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    editor = sp.edit();
                    editor.putBoolean("night", false);
                }else{
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    editor = sp.edit();
                    editor.putBoolean("night", true);
                }
                editor.apply();
            }
        });





        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        frameLayout = findViewById(R.id.main_frame);
        drawerLayout = findViewById(R.id.drawer);
        navigationView = findViewById(R.id.navigation_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener(){

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                item.setChecked(true);
                drawerLayout.closeDrawer(GravityCompat.START);
                switch (item.getItemId()){
                    case R.id.helppage:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,h).commit();
                        return true;
                    case R.id.aboutus:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,a).commit();
                        return true;
                    case R.id.themes:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,t).commit();
                        return true;
                }
                return false;
            }
        });

        bottomNavigationView  = findViewById(R.id.bottomNavigationView);



        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.page1:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,p1).commit();
                        return true;
                    case R.id.page2:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,p2).commit();
                        return true;
                    case R.id.page3:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,p3).commit();
                        return true;
                    case R.id.page4:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,p4).commit();
                        return true;

                }

                return false;
            }
        });

    }

}