package org.sunspark.happylearn;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class TeacherCreateLesson extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_create_lesson);
        Spinner searchableSpinner = (Spinner) findViewById(R.id.Subject) ;
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
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

      /**  searchableSpinner.setTitle("Select Subject");
        searchableSpinner.setPositiveButton("OK");*/
    }
}
