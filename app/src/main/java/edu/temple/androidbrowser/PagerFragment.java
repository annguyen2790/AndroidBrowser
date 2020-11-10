package edu.temple.androidbrowser;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


public class PagerFragment extends Fragment {
    ViewPager myViewPager;
    PageViewerFragment pageViewerFragment;
    FragmentManager fragmentManager;
    Fragment temp;
    ArrayList<PageViewerFragment> viewerFragments;

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
        viewerFragments = new ArrayList<>();
        myViewPager = view.findViewById(R.id.viewPager);
        myViewPager.setAdapter(new FragmentStatePagerAdapter(getChildFragmentManager()) {
            @NonNull
            @Override
            public Fragment getItem(int position) {
                return viewerFragments.get(position);
            }

            @Override
            public int getCount() {
                return viewerFragments.size();
            }
        });



        return view;
    }
    public int getCurrentPage(){
        return myViewPager.getCurrentItem();
    }
}