package org.sunspark.happylearn;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class SignIn extends AppCompatActivity {
    EditText Password;
    EditText Email;
    FirebaseAuth fAuth;
    Button SignInbtn;
    private FirebaseFirestore db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        fAuth = FirebaseAuth.getInstance();
        SignInbtn  = (Button) findViewById(R.id.SignInbtn);
        db = FirebaseFirestore.getInstance();
        SignInbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Password = (EditText) findViewById(R.id.Password);
                Email = (EditText) findViewById(R.id.Email);
               if(SignIn())
               {
                   String email = Email.getText().toString().trim();
                   final String password = Password.getText().toString().trim();
                   fAuth.signInWithEmailAndPassword(email,password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                       @Override
                       public void onSuccess(AuthResult authResult) {
                           Toast.makeText(SignIn.this, "Signed in", Toast.LENGTH_SHORT).show();
                           startActivity(new Intent(getApplicationContext(), MainActivity.class));
                       }
                   }).addOnFailureListener(new OnFailureListener() {
                       @Override
                       public void onFailure(@NonNull Exception e) {
                           Toast.makeText(SignIn.this, "Sign in fail", Toast.LENGTH_SHORT).show();
                       }
                   });
               }
            }
        });
    }
    public boolean SignIn()
    {
        if(!CheckMail()) Email.setError("Invalid Email");
        if(CheckPassword()) Password.setError("Invalid Password");
        return CheckMail() && !CheckPassword();
    }

    private boolean CheckPassword(){
        return (IsEmpty(Password));
    }

    private boolean CheckMail() {
        if(IsEmpty(Email))return false;
        CharSequence email = Email.getText().toString();
        return (!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches());
    }

    public boolean IsEmpty(@org.jetbrains.annotations.NotNull EditText text){
        CharSequence c = text.getText().toString();
        return  TextUtils.isEmpty(c);

    }

}
