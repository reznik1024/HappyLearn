package org.sunspark.happylearn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class SignInOrSignUp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_or_sign_up);
    }

    public void SignUp_Click(){
        Intent i = new Intent(this, SignUp.class);
        startActivity(i);
    }

    public void SignIn_Click(){
        Intent i = new Intent(this, SignIn.class);
        startActivity(i);
    }

}
