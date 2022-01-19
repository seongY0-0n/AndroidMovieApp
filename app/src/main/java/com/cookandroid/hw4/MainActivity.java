package com.cookandroid.hw4;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.GridView;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    DrawerLayout dl;

    String[] movieTitle = new String[]{"토이스토리4", "호빗", "제이슨본", "반지의제왕", "정직한 후보", "나쁜녀석들", "겨울왕국2", "알라딘", "극한직업", "스파이더맨"};
    myDBHelper helper;
    SQLControl sqlControl;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        helper= new myDBHelper(MainActivity.this);
        sqlControl = new SQLControl(helper);
        dl = (DrawerLayout)findViewById(R.id.drawer_layout);
        navigationView = (NavigationView)findViewById(R.id.nav_view);


        for (int i = 0; i <movieTitle.length ; i++) {
            sqlControl.insert(movieTitle[i],0);
        }
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_baseline_menu_24);

        navigationView.setNavigationItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.vote:
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new VoteFragment()).commit();
                    break;
                case R.id.result:
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new ResultFragment(helper)).commit();
                    break;
                case R.id.reset:
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ResetFragment()).commit();
                    break;
            }
            dl.closeDrawer(GravityCompat.START);
            return true;
        });

    }
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                dl.openDrawer(GravityCompat.START);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onBackPressed() {
        if (dl.isDrawerOpen(GravityCompat.START)) {
            dl.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}