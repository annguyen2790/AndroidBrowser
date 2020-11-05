package edu.temple.androidbrowser;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.graphics.pdf.PdfDocument;
import android.os.Bundle;
import android.view.Window;
import android.webkit.WebView;

public class BrowserActivity extends AppCompatActivity implements PageViewerFragment.browswerInterface, PageControlFragment.PageControlListener {
    FragmentManager fragmentManager;
    PageControlFragment pageControlFragment;
    PageViewerFragment pageViewerFragment;
    BrowserControlFragment browserControlFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browser);

        fragmentManager = getSupportFragmentManager();
        Fragment temp;

        if((temp = fragmentManager.findFragmentById(R.id.page_control)) instanceof PageControlFragment){
            pageControlFragment = (PageControlFragment) temp;
        }else{
            pageControlFragment = new PageControlFragment();
            fragmentManager.beginTransaction()
                    .add(R.id.page_control, pageControlFragment)
                    .commit();
        }
        if((temp = fragmentManager.findFragmentById(R.id.page_viewer)) instanceof PageViewerFragment){
            pageViewerFragment = (PageViewerFragment) temp;
        }else{
            pageViewerFragment = new PageViewerFragment();
            fragmentManager.beginTransaction()
                    .add(R.id.page_viewer, pageViewerFragment)
                    .commit();
        }

        if((temp = fragmentManager.findFragmentById(R.id.browser_control) )instanceof BrowserControlFragment){
            browserControlFragment = (BrowserControlFragment) temp;
        }else{
            browserControlFragment = new BrowserControlFragment();
            fragmentManager.beginTransaction()
                    .add(R.id.browser_control, browserControlFragment)
                    .commit();
        }

    }

    @Override
    public void forwardPress() {
        WebView wb = pageViewerFragment.webView;
        wb.goForward();
    }

    @Override
    public void backPress() {
        WebView wb = pageViewerFragment.webView;
        wb.goBack();
    }

    @Override
    public void okPress(CharSequence urlInput) {
        WebView wb = pageViewerFragment.webView;
        if(!urlInput.toString().startsWith("https://")){
            wb.loadUrl("https://" + urlInput.toString());
        }else {
            wb.loadUrl(urlInput.toString());
        }
    }

    @Override
    public void updateURL(String url) {
        //update url
        pageControlFragment.urlText.setText((CharSequence) url);
    }
}