package org.sunspark.happylearn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class SignUp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
    }

    public void student_click(View view){
        Intent i = new Intent(this, StudentSignup.class);
        startActivity(i);
    }

    public void teacher_click(View view){
        Intent i = new Intent(this, TeacherSignup.class);
        startActivity(i);
    }


}
