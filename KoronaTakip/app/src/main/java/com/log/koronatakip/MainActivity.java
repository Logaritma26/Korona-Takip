package com.log.koronatakip;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import static com.log.koronatakip.graphs.listView;
import static com.log.koronatakip.graphs.searchView;
import static com.log.koronatakip.graphs.viewPager_graph;

public class MainActivity extends AppCompatActivity {



    BottomNavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        navigationView = findViewById(R.id.nav_bar);

        navigationView.setOnNavigationItemSelectedListener( navListener);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new informing_section()).commit();

        navigationView.setSelectedItemId(R.id.item_info);



    }

    @Override
    public void onBackPressed() {
        if (!searchView.isIconified()) {
            searchView.setIconified(true);
            listView.setVisibility(View.GONE);
            if (viewPager_graph.getVisibility() == View.GONE){
                viewPager_graph.setVisibility(View.VISIBLE);
            }
        }
        else if (listView.getVisibility() == View.VISIBLE){
            listView.setVisibility(View.GONE);
            if (viewPager_graph.getVisibility() == View.GONE){
                viewPager_graph.setVisibility(View.VISIBLE);
            }
        }

        else {
            super.onBackPressed();
        }
    }




    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selected_fragment = null;

                    switch (item.getItemId()){


                        case R.id.item_graphic:
                            selected_fragment = new graphs();
                            break;

                        case R.id.item_info:
                            selected_fragment = new informing_section();
                            break;

                        case R.id.item_test:
                            selected_fragment = new self_test();
                            break;

                    }



                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selected_fragment).commit();

                    return true;
                }
            };







}
