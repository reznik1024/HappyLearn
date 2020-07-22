package org.sunspark.happylearn;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class TeacherSignup extends AppCompatActivity {

    EditText Name;
    EditText FamilyName;
    EditText PhoneNumber;
    EditText Password;
    EditText Email;
    FirebaseAuth fAuth;
    Button signUpbtn;
    String UserID;
    // FirebaseFirestore db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_signup);

        fAuth = FirebaseAuth.getInstance();
        // db = FirebaseFirestore.getInstance();
        final FirebaseFirestore db = FirebaseFirestore.getInstance();
        signUpbtn  = (Button) findViewById(R.id.signUpbtn);
        signUpbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Name = (EditText) findViewById(R.id.Name);
                FamilyName = (EditText) findViewById(R.id.FamilyName);
                PhoneNumber = (EditText) findViewById(R.id.PhoneNumber);
                Password = (EditText) findViewById(R.id.Password);
                Email = (EditText) findViewById(R.id.Email);

                final String email = Email.getText().toString().trim();
                final String password = Password.getText().toString().trim();
                final String phoneNum =PhoneNumber.getText().toString().trim();
                final String name = Name.getText().toString().trim();
                final String familyName = FamilyName.getText().toString().trim();

                if(StudentSignUpCheckValid()) {

                    fAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {

                              UserID=fAuth.getCurrentUser().getUid();
                               DocumentReference documentReference = db.collection("users").document(UserID);
                                Map<String,Object> user= new HashMap<>();
                                user.put("Name",name);
                                user.put("Family Name",familyName);
                                user.put("Phoneumber",phoneNum);
                                user.put("Email",email);
                                user.put("Password",password);
                                user.put("Status","Teacher");
                                documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        Toast.makeText(TeacherSignup.this, "Signed up yay", Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                                        SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);
                                        SharedPreferences.Editor myEdit = sharedPreferences.edit();
                                        myEdit.putString("Status", "Teacher");
                                        myEdit.commit();
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(TeacherSignup.this, "Sign up failed", Toast.LENGTH_SHORT).show();
                                    }
                                });
                              /**  documentReference.set(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        Toast.makeText(TeacherSignup.this, "Signed up yay", Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                                        SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);
                                        SharedPreferences.Editor myEdit = sharedPreferences.edit();
                                        myEdit.putString("Status", "Teacher");
                                        myEdit.commit();
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(TeacherSignup.this, "Sign up failed", Toast.LENGTH_SHORT).show();
                                    }
                                });*/



                                Toast.makeText(TeacherSignup.this, "2", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(TeacherSignup.this, "Sign up firebase failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
    }


    public boolean StudentSignUpCheckValid()
    {
        if(!CheckMail()) Email.setError("Invalid Email");
        if(!CheckName()) Name.setError("Invalid Name");
        if(!CheckFamilyName()) FamilyName.setError("Invalid Family Name");
        if(!CheckPhoneNum()) PhoneNumber.setError("Invalid Phone Number");
        if(CheckPassword()) Password.setError("Invalid Password");
        return CheckMail() && !CheckPassword() && CheckPhoneNum()&& CheckName() && CheckFamilyName() && CheckPhoneNum();
    }


    private boolean CheckName(){
        // return (IsEmpty(Name) && CheckOnlyLetter(Name));
        return  CheckOnlyLetter(Name);
    }

    private boolean CheckFamilyName(){
        return CheckOnlyLetter(FamilyName);
    }

    private boolean CheckPassword(){
        return (IsEmpty(Password));
    }

    public boolean CheckOnlyLetter(EditText text)
    {
        if(IsEmpty(text))return false;
        CharSequence c = text.getText().toString();
        char d;
        for (int i=0; i< text.length();i++)
        {
            d= c.charAt(i);
            if( !((d >= 'a' && d <= 'z') || (d >= 'A' && d <= 'Z'))) return false;
        }
        return true;
    }

    private boolean CheckMail() {
        if(IsEmpty(Email))return false;
        CharSequence email = Email.getText().toString();
        return (!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches());
    }

    private boolean CheckPhoneNum() {
        if(IsEmpty(PhoneNumber))return false;
       /* CharSequence PhoneNum = PhoneNumber.getText().toString();
        char d= PhoneNum.charAt(0);
        if( PhoneNum.length()<=9 ) return true;
        return  false;*/
        return true;
    }

    public boolean IsEmpty(@org.jetbrains.annotations.NotNull EditText text){
        CharSequence c = text.getText().toString();
        return  TextUtils.isEmpty(c);

    }
}
