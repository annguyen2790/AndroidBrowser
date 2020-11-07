package edu.temple.androidbrowser;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.graphics.pdf.PdfDocument;
import android.os.Bundle;
import android.view.Window;
import android.webkit.WebView;

public class BrowserActivity extends AppCompatActivity implements PageViewerFragment.browswerInterface,
            PageControlFragment.PageControlListener, BrowserControlFragment.browserControlListener {
    FragmentManager fragmentManager;
    PageControlFragment pageControlFragment;
    BrowserControlFragment browserControlFragment;
    PageListFragment pageListFragment;
    PagerFragment pagerFragment;
    Fragment temp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browser);

        fragmentManager = getSupportFragmentManager();

        if((temp = fragmentManager.findFragmentById(R.id.page_control)) instanceof PageControlFragment){
            pageControlFragment = (PageControlFragment) temp;
        }else{
            pageControlFragment = new PageControlFragment();
            fragmentManager.beginTransaction()
                    .add(R.id.page_control, pageControlFragment)
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
        if((temp = fragmentManager.findFragmentById(R.id.page_list) )instanceof PageListFragment){
            pageListFragment = (PageListFragment) temp;
        }else{
            pageListFragment = new PageListFragment();
            fragmentManager.beginTransaction()
                    .add(R.id.page_list, pageListFragment)
                    .commit();
        }

        if ((temp = fragmentManager.findFragmentById(R.id.pager)) instanceof PagerFragment){
            pagerFragment = (PagerFragment) temp;
        }else {
            pagerFragment = new PagerFragment();
            fragmentManager.beginTransaction().
                    add(R.id.page_viewer, pagerFragment)
                    .commit();

        }




    }

    @Override
    public void forwardPress() {
        WebView wb = pagerFragment.pageViewerFragment.webView;
        wb.goForward();
    }

    @Override
    public void backPress() {
        WebView wb = pagerFragment.pageViewerFragment.webView;
        wb.goBack();
    }

    @Override
    public void okPress(CharSequence urlInput) {
        WebView wb = pagerFragment.pageViewerFragment.webView;
        if(!urlInput.toString().startsWith("https://")){
            wb.loadUrl("https://" + urlInput.toString());
        }else {
            wb.loadUrl(urlInput.toString());
        }
    }

    @Override
    public void updateURL(String url) {
        pageControlFragment.urlText.setText((CharSequence) url);
    }

    @Override
    public void openNewPage() {

    }
}