package org.sunspark.happylearn;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class LessonFragment extends Fragment implements DatePickerDialog.OnDateSetListener  {
    private DatePickerDialog.OnDateSetListener onDateSetListener;
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.lesson_fragment,container,false);
        List<String> lessons = new ArrayList<String>();
        lessons.add("Math");
        lessons.add("Biology ");
        lessons.add("Chemistry");
        lessons.add("Physics");
        lessons.add("English");
        lessons.add("Literature");
        Spinner spinner = (Spinner) view.findViewById(R.id.TypeSpin);
        ArrayAdapter dataAdapter  = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, lessons);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
        TextView date = (TextView) view.findViewById(R.id.date);
      /*  date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(),, Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH));datePickerDialog.show();

            }
        });*/

        return view;
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        String date = "month/day/year: " + month + "/" + dayOfMonth + "/" + year;
    }
}
