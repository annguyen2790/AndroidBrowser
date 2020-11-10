package edu.temple.androidbrowser;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;


public class PageViewerFragment extends Fragment {
    WebView webView;
    browswerInterface browswerListener;

    public PageViewerFragment() {
        // Required empty public constructor
    }
    public interface browswerInterface{
        void updateURL(String url);
    }
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof browswerInterface){
            browswerListener = (browswerInterface) context;
        }else{
            throw new RuntimeException("Please implement browserInterface");
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        webView.saveState(outState);
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setRetainInstance(true);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_page_viewer, container, false);
        webView = v.findViewById(R.id.browserView);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                browswerListener.updateURL(url);
            }
        });
        //restore web session
        if(savedInstanceState != null){
            webView.restoreState(savedInstanceState);
        }
        return v;
    }

    public void goFor(){
        webView.goForward();
    }

    public void goBackward(){
        webView.goBack();
    }

    public void okPressed(String url){
        if(!url.startsWith("https://")){
            url = "https://" + url;
            webView.loadUrl(url);
        }else {
            webView.loadUrl(url);
        }
    }
}