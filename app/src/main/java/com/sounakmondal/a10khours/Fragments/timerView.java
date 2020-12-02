package com.sounakmondal.a10khours.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.sounakmondal.a10khours.R;
import com.sounakmondal.a10khours.StatsActivity;

import java.util.Timer;
import java.util.TimerTask;

public class timerView extends Fragment {
    View rootView;
    protected Timer myTimer;
    TextView timerTextView;
    Button startButton, pauseButton, stopButton;

    public static timerView newInstance() {
        return new timerView();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.timer_layout,container,false);
        //Timer
        timerTextView = rootView.findViewById(R.id.timeElapsedID);
        startButton = rootView.findViewById(R.id.start_buttonID);
        pauseButton = rootView.findViewById(R.id.pause_buttonID);
        stopButton = rootView.findViewById(R.id.stop_buttonID);


        //click stats button to start stats activity
        Button statsButton = rootView.findViewById(R.id.Stats_buttonID);
        statsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(inflater.getContext(), StatsActivity.class);
                startActivity(intent);
            }
        });

        //start button action
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startTime = System.currentTimeMillis();
                startTimer();
            }
        });


            return rootView;
}





    public void startTimer()
    {
        myTimer = new Timer();
        myTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                TimerMethod();
            }

        }, 0, 1000);

    }



    private void TimerMethod()
    {
        //This method is called directly by the timer
        //and runs in the same thread as the timer.

        //We call the method that will work with the UI
        //through the runOnUiThread method.
        getActivity().runOnUiThread(Timer_Tick);
    }

    long startTime = 0;


    private Runnable Timer_Tick = new Runnable() {
        public void run() {
            long millis = System.currentTimeMillis() - startTime;
            int seconds = (int) (millis/1000);
            int minutes = seconds / 60;
            seconds = seconds % 60;
            int hours = minutes / 60;
            minutes = minutes % 60;
            String hoursStr = "00",minutesStr= "00", secondsStr= "00";

            if(hours<10)
            {
                hoursStr = "0"+ hours;
            }
            else
            {
                hoursStr =  Integer.toString(hours);
            }
            if(minutes<10)
            {
                minutesStr = "0"+ minutes;
            }
            else
            {
                minutesStr =  Integer.toString(minutes);
            }
            if(seconds<10)
            {
                secondsStr = "0"+ seconds;
            }
            else
            {
                secondsStr =  Integer.toString(seconds);
            }
            String time_text = hoursStr + ":" + minutesStr +":"+secondsStr;

            timerTextView.setText(time_text);
            Log.i("seconds passed - ",secondsStr);


            //update this part
            //This method runs in the same thread as the UI.
            //Do something to the UI thread here

        }
    };



}

