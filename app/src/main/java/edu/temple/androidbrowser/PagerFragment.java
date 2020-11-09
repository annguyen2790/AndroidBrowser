package edu.temple.androidbrowser;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class PagerFragment extends Fragment {
    ViewPager myViewPager;
    PageViewerFragment pageViewerFragment;
    FragmentManager fragmentManager;
    Fragment temp;

    public PagerFragment() {
        // Required empty public constructor
    }
    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

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
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.fragment_pager, container, false);
        fragmentManager = getChildFragmentManager();
        if((temp = fragmentManager.findFragmentById(R.id.browserVIEWER)) instanceof PageViewerFragment){
            pageViewerFragment = (PageViewerFragment) temp;

        }else{
            pageViewerFragment = new PageViewerFragment();
            fragmentManager.beginTransaction()
                    .add(R.id.browserVIEWER, pageViewerFragment)
                    .commit();

        }

        myViewPager = view.findViewById(R.id.viewPager);

        return view;
    }
}