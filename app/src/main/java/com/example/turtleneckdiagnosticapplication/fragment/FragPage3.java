package com.example.turtleneckdiagnosticapplication.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.turtleneckdiagnosticapplication.R;

public class FragPage3 extends Fragment {
    private View view;

    public static FragPage3 newInstance() {
        FragPage3 fragPage3 = new FragPage3();
        return fragPage3;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.howto_page3, container, false);

        return view;
    }
}
