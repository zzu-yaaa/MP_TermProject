package com.example.mp_termproject;

import static java.sql.Types.NULL;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;
import com.jakewharton.threetenabp.AndroidThreeTen;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import java.util.HashMap;
import java.util.HashSet;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawer;
    //NavigationView navigationView;
    private HomeFragment homeFragment;
    private SubjectFragment subjectFragment;

    UserDBHelper userHelper;
    NavigationView navigationView;

    TextView name;
    TextView studentNum;
    TextView major;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        homeFragment = (HomeFragment) getSupportFragmentManager().findFragmentById(R.id.home_fragment);
        subjectFragment = (SubjectFragment) getSupportFragmentManager().findFragmentById(R.id.subject_fragment);
        navigationView = findViewById(R.id.navigation_view);
        navigationView.setItemIconTintList(null);
        View headerView = navigationView.getHeaderView(0);
        userHelper = new UserDBHelper(this);
        String result[] = userHelper.selectUser();
        setLayout();

        Toolbar toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar); //toolbar as AppBar for MainActivity
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        drawer = findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawer,toolbar,
                R.string.navigation_drawer_open
                ,R.string.navigation_drawer_close);

        drawer.addDrawerListener(toggle);

        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        name = headerView.findViewById(R.id.name);
        major = headerView.findViewById(R.id.major);
        studentNum = headerView.findViewById(R.id.studentNum);
        name.setText(result[0]);
        studentNum.setText(result[2]);
        major.setText(result[1]);

    }

    private void setLayout() {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_view, new HomeFragment()).commit();
    }

    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem){
        switch(menuItem.getItemId()){
            case R.id.home:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_view, new HomeFragment()).commit();
                break;
            case R.id.color:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_view, new ColorFragment()).commit();
                break;
            case R.id.subject:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_view, new SubjectFragment()).commit();
                break;
            case R.id.speech:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_view, new SpeechFragment()).commit();
                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}