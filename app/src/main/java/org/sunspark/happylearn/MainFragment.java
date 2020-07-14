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

public class MainFragment extends Fragment {
    private onFragmentButtonClicked listener;
    private  Button btn;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main,container,false);
        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof onFragmentButtonClicked){
            listener = (onFragmentButtonClicked) context;
        } else {
            throw new ClassCastException(context.toString() + " must implement lisener");
        }

    }

    public interface onFragmentButtonClicked
    {
        public void onButtonSelected(Button btn);
    }
}
