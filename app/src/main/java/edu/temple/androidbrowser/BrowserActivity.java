package edu.temple.androidbrowser;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.graphics.pdf.PdfDocument;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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
        addFragments();






    }
    public void addFragments(){
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
        int i  = pagerFragment.myViewPager.getCurrentItem();
        pagerFragment.viewerFragments.get(i).goFor();
    }

    @Override
    public void backPress() {
        int i  = pagerFragment.myViewPager.getCurrentItem();
        pagerFragment.viewerFragments.get(i).goBackward();

    }

    @Override
    public void okPress(CharSequence urlInput) {
        int i  = pagerFragment.myViewPager.getCurrentItem();
        if(pagerFragment.viewerFragments.size() == 0){
            pagerFragment.viewerFragments.add(new PageViewerFragment());
            pagerFragment.myViewPager.getAdapter().notifyDataSetChanged();
        }
        pagerFragment.viewerFragments.get(i).okPressed(urlInput.toString());
    }

    @Override
    public void updateURL(String url) {

    }

    @Override
    public void openNewPage() {
        findViewById(R.id.addTabButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pagerFragment.viewerFragments.add(new PageViewerFragment());
                pagerFragment.myViewPager.getAdapter().notifyDataSetChanged();
                Log.e("tagw", "openNewPage");
            }
        });
    }
}