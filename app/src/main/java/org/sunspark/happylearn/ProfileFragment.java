package org.sunspark.happylearn;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.concurrent.Executor;

public class ProfileFragment extends Fragment {
    private MainFragment.onFragmentButtonClicked listener;
    private  Button btn;
    FirebaseAuth fAuth;
    String UserID;
    String str;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.profile_fragment,container,false);

        btn = (Button) view.findViewById(R.id.SignOutBtnT);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onButtonSelected(btn);
            }
        });

        final Button lessonRequest = (Button)  view.findViewById(R.id.LessonRequest);
        final TextView status = (TextView) view.findViewById(R.id.Status);
        fAuth = FirebaseAuth.getInstance();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        UserID = fAuth.getCurrentUser().getUid();
        DetermineStatus(view);
        lessonRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 if(lessonRequest.getText().toString().equals("Create Lesson Request")){
                     Intent intent = new Intent(getActivity(), StudentRequestLesson.class);
                     startActivity(intent);
                 }else if (lessonRequest.getText().toString().equals("Create New Lesson")){
                     Intent intent = new Intent(getActivity(), TeacherCreateLesson.class);
                     startActivity(intent);
                 }
            }
        });

        return view;
    }

    public void DetermineStatus(View view){
        final Button lessonRequest = (Button)  view.findViewById(R.id.LessonRequest);
        final TextView status = (TextView) view.findViewById(R.id.Status);
        SharedPreferences sharedPref = getActivity().getSharedPreferences("MySharedPref",Context.MODE_PRIVATE);
        status.setText( sharedPref.getString("Status", "null"));
        if(status.getText().equals("Student")) {
            lessonRequest.setText("Create Lesson Request");
        } else if(status.getText().equals("Teacher")){
            lessonRequest.setText("Create New Lesson");
        }
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof MainFragment.onFragmentButtonClicked){
            listener = (MainFragment.onFragmentButtonClicked) context;
        } else {
            throw new ClassCastException(context.toString() + " must implement lisener");
        }

    }


    public interface onFragmentButtonClicked
    {
        public void onButtonSelected(Button btn);
    }
}
