package org.sunspark.happylearn;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ProfileFragment extends Fragment {
    private MainFragment.onFragmentButtonClicked listener;
    private  Button btn;
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.profile_fragment,container,false);
        btn = (Button) view.findViewById(R.id.SignOutBtnT);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onButtonSelected(btn);
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
