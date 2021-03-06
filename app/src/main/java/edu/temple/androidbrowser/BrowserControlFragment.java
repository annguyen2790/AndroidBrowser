package edu.temple.androidbrowser;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;


public class BrowserControlFragment extends Fragment {

    ImageButton addButton;
    browserControlListener browserControlListener;
    public BrowserControlFragment() {
        // Required empty public constructor
    }
    interface browserControlListener{
        void openNewPage();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof browserControlListener){
            browserControlListener = (browserControlListener) context;
        }else{
            throw  new RuntimeException("Please implement PageControlListenter");
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_browser_control, container, false);
        addButton = view.findViewById(R.id.addTabButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                browserControlListener.openNewPage();
            }
        });
        return view;
    }
}