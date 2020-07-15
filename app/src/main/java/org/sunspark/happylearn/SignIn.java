package org.sunspark.happylearn;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class SignIn extends AppCompatActivity {
    EditText Password;
    EditText Email;
    FirebaseAuth fAuth;
    Button SignUpbtn;
    private FirebaseFirestore db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        fAuth = FirebaseAuth.getInstance();
        signUpbtn  = (Button) findViewById(R.id.signUpbtn);
        db = FirebaseFirestore.getInstance();
        setContentView(R.layout.activity_sign_in);
    }

    private boolean CheckPassword(){
        return (IsEmpty(Password));
    }
}
