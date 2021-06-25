package com.example.turtleneckdiagnosticapplication.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.turtleneckdiagnosticapplication.R;

public class FragPage0 extends Fragment {
    private View view;

    public static FragPage0 newInstance() {
        FragPage0 fragPage0 = new FragPage0();
        return fragPage0;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.howto_page0, container, false);

        return view;
    }
}
