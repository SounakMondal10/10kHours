package com.sounakmondal.a10khours.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.sounakmondal.a10khours.R;
import com.sounakmondal.a10khours.StatsActivity;

public class timerView extends Fragment {
    View rootView;

    public static timerView newInstance() {
        return new timerView();
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.timer_layout,container,false);
        Button button = rootView.findViewById(R.id.Stats_buttonID);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(inflater.getContext(), StatsActivity.class);
                startActivity(intent);
            }
        });

        return rootView;
    }
}
