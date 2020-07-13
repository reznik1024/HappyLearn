package org.sunspark.happylearn;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends Fragment {

    SharedPreferences sp;
    TextView tx;
    FirebaseAuth fAuth;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        fAuth = FirebaseAuth.getInstance();
        super.onCreate(savedInstanceState);
       if(fAuth.getCurrentUser() ==null)updateUI();

;
    }

    private void updateUI()
    {
        Intent i = new Intent(MainActivity.class, SignUp.class);
        startActivity(i);
    }


}