package com.example.wanted;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MainAdapter extends FragmentStateAdapter {

    private List<WantedBean> wantedList = new ArrayList<>();

    public MainAdapter(@NonNull FragmentActivity fragmentActivity,List<WantedBean> wantedList) {
        super(fragmentActivity);
        this.wantedList = wantedList;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch(position){
            case 0:
                RecommendFragment recommendFragment = new RecommendFragment();
                Bundle bundle=new Bundle();
                bundle.putSerializable("list", (Serializable) wantedList);
                recommendFragment.setArguments(bundle);

                return recommendFragment;
            case 1:
                return new GameFragment();
            case 2:
                return new TravelFragment();
            case 3:
                return new StudyFragment();
            case 4:
                return new RecruitFragment();
            default:
                return new RecommendFragment();
        }
    }


    @Override
    public int getItemCount() {
        return 5;
    }
}
