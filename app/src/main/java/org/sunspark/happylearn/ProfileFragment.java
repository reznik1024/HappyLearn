package org.sunspark.happylearn;

import android.content.Context;
import android.content.Intent;
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
        final DocumentReference documentReference = db.collection("users").document(UserID);

        documentReference.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                status.setText(value.getString("Status"));
                str=status.toString().trim();
                if(status.toString().trim() =="Student")
                {
                    lessonRequest.setText("Create Lesson Request");
                } else if (str =="Teacher"){
                    lessonRequest.setText("Create New Lesson");
                }
                Toast.makeText(getActivity(), str, Toast.LENGTH_SHORT).show();
            }
        });

        lessonRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 if(lessonRequest.getText().toString() == "Create Lesson Request"){
                     Intent intent = new Intent(getActivity(), StudentRequestLesson.class);
                     startActivity(intent);
                 }else if (lessonRequest.getText().toString() == "Create New Lesson"){
                     Intent intent = new Intent(getActivity(), TeacherCreateLesson.class);
                     startActivity(intent);
                 }
            }
        });

        return view;
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
