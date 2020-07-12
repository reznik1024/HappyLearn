package org.sunspark.happylearn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    SharedPreferences sp;
    TextView tx;
    FirebaseAuth fAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        fAuth = FirebaseAuth.getInstance();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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


}