package org.sunspark.happylearn;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class TeacherCreateLesson extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {
EditText Price;
TextView Date;
TextView Time;
FirebaseAuth fAuth;
String UserID;
String Subject,Place;
String institution;
EditText Length;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_create_lesson);

        fAuth = FirebaseAuth.getInstance();
        FirebaseUser user = fAuth.getCurrentUser();
        user.reload();
        final FirebaseFirestore db = FirebaseFirestore.getInstance();

        final Spinner searchableSpinner = (Spinner) findViewById(R.id.Subject) ;
        ArrayList<String> Subjects = new ArrayList<>();
        Subjects.add("Chess");
        Subjects.add("Math");
        Subjects.add("Physics");
        Subjects.add("English");
        Subjects.add("Guitar");
        Subjects.add("Biology");
        Subjects.add("Chemistry");
        Subjects.add("Algebra");
        Subjects.add("Cell Biology");
        Subjects.add("Cooking");
        Subjects.add("Geology");
        searchableSpinner.setAdapter(new ArrayAdapter<>(TeacherCreateLesson.this,android.R.layout.simple_spinner_dropdown_item,Subjects));
        searchableSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position == 0){
                    Toast.makeText(getApplicationContext(),"Please select a Subject",Toast.LENGTH_SHORT);
                } else {
                    String sNumber = parent.getItemAtPosition(position).toString();
                    Subject = sNumber;
                }

            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        final Spinner searchableInstitution = (Spinner) findViewById(R.id.Institution) ;
        final ArrayList<String> Institution = new ArrayList<>();
        Institution.add("Kinergarden");
        Institution.add("Middle school");
        Institution.add("High school");
        Institution.add("University");
        Institution.add("College");
        searchableInstitution.setAdapter(new ArrayAdapter<>(TeacherCreateLesson.this,android.R.layout.simple_spinner_dropdown_item,Subjects));
        searchableInstitution.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position == 0){
                    Toast.makeText(getApplicationContext(),"Please select an Institution",Toast.LENGTH_SHORT);
                } else {
                    String sNumber2 =  parent.getItemAtPosition(position).toString();
                    institution = sNumber2;
                }

            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        final ArrayList<String> Location = new ArrayList<>();
        Location.add("Online");
        Location.add("Tutor's home");
        Location.add("Students home");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, Location);

        final Spinner location = (Spinner) findViewById(R.id.Location) ;
        location.setAdapter(dataAdapter);

        Price = (EditText) findViewById(R.id.Price);
        Date = (TextView) findViewById(R.id.Date);
        Time = (TextView) findViewById(R.id.Time);
        Length = (EditText) findViewById(R.id.Length);
        Button Create = (Button)  findViewById(R.id.CreateNewLesson);

        Create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(CreateNewLessonValid()){
                    UserID=fAuth.getCurrentUser().getUid();
                    DocumentReference documentReference = db.collection("LessonTeacher").document(UserID);
                    Map<String,Object> lesson = new HashMap<>();

                    String price = Price.getText().toString().trim();
                    final String date = Date.getText().toString().trim();
                    final String time =Time.getText().toString().trim();
                    final String length =Length.getText().toString().trim();
                    institution = searchableInstitution.getSelectedItem().toString();
                    Subject = searchableSpinner.getSelectedItem().toString();
                    Place = location.getSelectedItem().toString();
                    lesson.put("Price",price);
                    lesson.put("Date",date);
                    lesson.put("Time",time);
                    lesson.put("Place",Place);
                    lesson.put("Institution",institution);
                    lesson.put("Subject",Subject);
                    lesson.put("Length",length);
                    documentReference.set(lesson).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            Toast.makeText(TeacherCreateLesson.this, "Lesson creation Success", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(TeacherCreateLesson.this, "Lesson creation failed", Toast.LENGTH_SHORT).show();
                        }
                    });

                }
            }
        });

    }

    public void SetDateTeacher(View view)
    {
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, this,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
        );
        datePickerDialog.show();
    }

    public void SetTimeTeacher(View view){
        TimePickerDialog TimePickerDialog =  new TimePickerDialog(this, this,
                Calendar.getInstance().get(Calendar.HOUR_OF_DAY),
                Calendar.getInstance().get(Calendar.MINUTE), true
        );
        TimePickerDialog.show();
    }

    public boolean CreateNewLessonValid(){
        if(CheckPrice()) Price.setError("Enter a price");
        if(CheckTime()) Time.setError("Enter a time");
        if(CheckDate()) Date.setError("Enter a date");
        if(CheckLength()) Length.setError("Enter lesson length");
        return  !CheckTime() && !CheckDate() && !CheckPrice()  && !CheckLength();
    }

    private boolean CheckPrice(){
        Price = (EditText) findViewById(R.id.Price);
        return IsEmpty(Price);
    }

    private boolean CheckTime(){
        Time = (TextView) findViewById(R.id.Time);
        CharSequence c = Time.getText().toString();
        return   TextUtils.isEmpty(c);
    }

    private boolean CheckDate(){
        Date = (TextView) findViewById(R.id.Date);
        CharSequence c = Date.getText().toString();
        return   TextUtils.isEmpty(c);
    }

    private boolean CheckLength(){
        Length = (EditText) findViewById(R.id.Length);
        return IsEmpty(Length);
    }


    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        TextView Date= (TextView) findViewById(R.id.Date);
        Date.setText(dayOfMonth +"/" + month + "/" + year);
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        TextView Time= (TextView) findViewById(R.id.Time);
        Time.setText(hourOfDay + ":" + minute);
    }

    public boolean IsEmpty(@org.jetbrains.annotations.NotNull EditText text){
        CharSequence c = text.getText().toString();
        return   TextUtils.isEmpty(c);
    }

}
