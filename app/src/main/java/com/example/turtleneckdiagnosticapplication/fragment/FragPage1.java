package com.example.turtleneckdiagnosticapplication.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.turtleneckdiagnosticapplication.R;

public class FragPage1 extends Fragment {
    private View view;

    public static FragPage1 newInstance() {
        FragPage1 fragPage1 = new FragPage1();
        return fragPage1;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.howto_page1, container, false);

        return view;
    }
}
