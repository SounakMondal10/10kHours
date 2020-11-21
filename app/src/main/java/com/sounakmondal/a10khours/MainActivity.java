package com.sounakmondal.a10khours;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;

import com.google.android.material.tabs.TabLayout;
import com.sounakmondal.a10khours.ViewPager.Pageradapter;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ViewPager pager = findViewById(R.id.main_viewPager);
        PagerAdapter adapter = new Pageradapter(getSupportFragmentManager());
        TabLayout tabLayout = findViewById(R.id.main_tabLayout);
        pager.setAdapter(adapter);
        tabLayout.setupWithViewPager(pager);








    }
}
