package org.sunspark.happylearn;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.navigation.NavigationView;


public class Drawer extends AppCompatActivity {
    Toolbar toolbar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);
        toolbar = findViewById(R.id.main_toolbar);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.openNav,  R.string.closeNav
        );
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                FragmentManager fm;
                FragmentTransaction ft;
                drawerLayout.closeDrawers();
                switch (item.getItemId()) {
                    case R.id.nav_hardware:
                        //Toast.makeText(DrawerActivity.this, "hardware", Toast.LENGTH_LONG).show();
                        fm = getSupportFragmentManager();
                        ProfileFragment p = new ProfileFragment();
                        ft = fm.beginTransaction();
                        ft.replace(R.id.frag_container, p);
                        ft.addToBackStack(null);
                        ft.commit();
                        break;
                 /*   case R.id.nav_software:
                        //Toast.makeText(DrawerActivity.this, "software", Toast.LENGTH_LONG).show();
                        fm = getSupportFragmentManager();
                        SoftwareFragment sf = new SoftwareFragment();
                        ft = fm.beginTransaction();
                        ft.replace(R.id.frag_container, sf);
                        ft.addToBackStack(null);
                        ft.commit();
                        break;
                    case R.id.nav_lecturer:
                        Toast.makeText(Drawer.this, "lecturer", Toast.LENGTH_LONG).show();
                        break;*/
                }
                return false;
            }
        });
    }
}
