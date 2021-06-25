package com.example.turtleneckdiagnosticapplication.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.turtleneckdiagnosticapplication.fragment.FragPage0;
import com.example.turtleneckdiagnosticapplication.fragment.FragPage1;
import com.example.turtleneckdiagnosticapplication.fragment.FragPage2;
import com.example.turtleneckdiagnosticapplication.fragment.FragPage3;

public class ViewPagerAdapter extends FragmentPagerAdapter {
    public ViewPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    // 프래그먼트 교체를 보여주는 처리를 구현한 곳
    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return FragPage0.newInstance();
            case 1:
                return FragPage1.newInstance();
            case 2:
                return FragPage2.newInstance();
            case 3:
                return FragPage3.newInstance();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 4;
    }
}
