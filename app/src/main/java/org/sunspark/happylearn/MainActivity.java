package org.sunspark.happylearn;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import android.view.View;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    SharedPreferences sp;
    TextView tx;
    FirebaseAuth fAuth;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    Toolbar toolbar;
    NavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        fAuth = FirebaseAuth.getInstance();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawerLayout = findViewById(R.id.drawer);
        navigationView = findViewById(R.id.navigationView);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.setDrawerIndicatorEnabled(true);
        actionBarDrawerToggle.syncState();

       if(fAuth.getCurrentUser() ==null)updateUI();
        SharedPreferences sp = getApplicationContext().getSharedPreferences("NotLogedIn", Context.MODE_PRIVATE);
       tx  = (TextView) findViewById(R.id.text);
   /*     if(sp.getBoolean("NotLogedIn", true)){
                Intent i = new Intent(this, SignUp.class);
                startActivity(i);
            tx.setText("oops");
        } else{
            tx.setText("oopsie");
        }*/
;
    }

    private void updateUI()
    {
        Intent i = new Intent(this, SignUp.class);
        startActivity(i);
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.Home){

        }
        if(item.getItemId() == R.id.Profile){

        }
        return true;
    }
}