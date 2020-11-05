package edu.temple.androidbrowser;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;


public class PageControlFragment extends Fragment {

    EditText urlText;
    ImageButton backButton;
    ImageButton okButton;
    ImageButton forwardButton;
    PageControlListener listener;

    public PageControlFragment() {
        // Required empty public constructor
    }
    interface PageControlListener{
        void forwardPress();
        void backPress();
        void okPress(CharSequence urlInput);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof PageControlListener ){
            listener = (PageControlListener) context;
        }else{
            throw  new RuntimeException("Please implement PageControlListenter");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_page_control, container, false);
        forwardButton = v.findViewById(R.id.forward_button);
        backButton = v.findViewById(R.id.back_button);
        okButton = v.findViewById(R.id.ok_button);
        urlText = v.findViewById(R.id.url_editText);
        //Navigate to the next page
        forwardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.forwardPress();
            }
        });
        //Navigate to the last page visited
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.backPress();
            }
        });
        //Go to the specified url
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CharSequence input = urlText.getText();
                listener.okPress(input);
            }
        });

        return v;
    }
}