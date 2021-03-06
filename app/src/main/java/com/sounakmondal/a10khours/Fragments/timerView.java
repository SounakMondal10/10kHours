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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.sounakmondal.a10khours.Data;
import com.sounakmondal.a10khours.MainActivity;
import com.sounakmondal.a10khours.R;
import com.sounakmondal.a10khours.StatsActivity;
import com.sounakmondal.a10khours.RecyclerViews.TaskSelectorAdapter;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import static com.sounakmondal.a10khours.RecyclerViews.TaskSelectorAdapter.data1;

public class timerView extends Fragment {
    public static TextView taskNameText;
    public static int taskTimeCompleted;
    View rootView;
    public static TextView timerTextView;
    Button startButton, pauseButton, stopButton;
    int minutes = 0, hours = 0,seconds = 0; //For storing data
    long milliseconds=0;
    int time=0;


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
        taskNameText = rootView.findViewById(R.id.taskNameText);

        //click stats button to start stats activity
        Button statsButton = rootView.findViewById(R.id.Stats_buttonID);
        statsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(taskChosen == false)
                {
                    Toast.makeText(getContext(), "Select a task", Toast.LENGTH_SHORT).show();
                }
                else
                    {
                Intent intent = new Intent(inflater.getContext(), StatsActivity.class);
                startActivity(intent);
                    }
            }
        });

        //start button action
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startTimer();
            }
        });

        pauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Timer Paused", Toast.LENGTH_SHORT).show();
                pauseTimer();
            }
        });

        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Timer Stopped", Toast.LENGTH_SHORT).show();
                stopTimer();
            }
        });


            return rootView;
}


    static long startTime = System.currentTimeMillis();
    static long temp_time = 0; //time elapsed since the timer was started
    long millis =0; // same as temp_time
    boolean isPaused = false;
    public static Timer myTimer;
    boolean timerIsRunning = false;
    boolean firstTime = true;



    public boolean startTimer()
    {
        if(taskChosen == false)
        {
            Toast.makeText(getContext(), "Select a task", Toast.LENGTH_SHORT).show();
            return false;

        }
        Toast.makeText(getContext(), "Timer Started", Toast.LENGTH_SHORT).show();
        if(firstTime == true)
            {
                startTime = System.currentTimeMillis();
                firstTime = false;
            }

        else
            {
                if(timerIsRunning == true) //needs checking
                {

                }
                if(isPaused = false)
                {
                    startTime = System.currentTimeMillis();
                    //millis =temp_time;
                }
                else
                {
                    startTime = System.currentTimeMillis()-temp_time;
                    isPaused = false;

                }

            }


        if(timerIsRunning == false)
        {
            myTimer = new Timer();
            myTimer.schedule(new TimerTask() {
                @Override
                public void run() {
                    TimerMethod();
                    timerIsRunning = true;
                }

            }, 0, 1000);

        }
    return true;



    }

    public void pauseTimer()
    {
        if(myTimer!=null)
        {   temp_time = millis;
            isPaused = true;
            myTimer.cancel();
            myTimer.purge();
            timerIsRunning = false;
        }
    }

    public void stopTimer()
    {
        if(myTimer!=null)
        {
            updateCompletedTime(taskDetails1.getTimeSpent()+ (int) temp_time/1000);
            TaskSelectorAdapter.setData(pos, taskDetails1.getTaskName(),taskDetails1.getTimeSpent());
            EventBus.getDefault().post(new TaskSelectorAdapter.DataSync(TaskSelectorAdapter.getData()));
            Log.i("completed time",Integer.toString(taskDetails1.getTimeSpent()));
            millis = 0;
            temp_time = 0;

            myTimer.cancel();
            myTimer.purge();
            myTimer = null;
            firstTime = true;
            timerIsRunning = false;
        }


    }

    private void TimerMethod()
    {
        //This method is called directly by the timer
        //and runs in the same thread as the timer.
        //We call the method that will work with the UI
        //through the runOnUiThread method.
        getActivity().runOnUiThread(Timer_Tick);
    }



    private Runnable Timer_Tick = new Runnable() {
        public void run() {

            millis = System.currentTimeMillis() - startTime;
            millis += taskDetails1.getTimeSpent()*1000;
            temp_time = millis;
            seconds = (int) (millis/1000);
            minutes = seconds / 60;
            seconds = seconds % 60;
//            currentSec = seconds;
            hours = minutes / 60;
            minutes = minutes % 60;
//            currentMin = minutes;
//            currentHour = hours;

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
//            Log.i("seconds passed - ",secondsStr);
            //update this part
            //This method runs in the same thread as the UI.
            //Do something to the UI thread here
            //Done!

        }
    };

    public static Data taskDetails1;
    public static int pos;
    public static boolean taskChosen = false;




    public static Data getTaskDetails1() {
        return taskDetails1;
    }

    public static void setTimeFromTask(Data taskDetails, int position)
    {
        taskDetails1 = taskDetails;
        taskNameText.setText(taskDetails.getTaskName());
        taskTimeCompleted = taskDetails.getTimeSpent();
        taskChosen = true;


        // temporarily changing timer time to whatever it is of the selected task

        int secs = taskTimeCompleted;
        int mins = secs / 60;
        secs = secs % 60;
        int hour = mins / 60;
        mins = mins % 60;


        String hoursString = "00",minutesString= "00", secondsString= "00";

        if(hour<10)
        {
            hoursString = "0"+ hour;
        }
        else
        {
            hoursString =  Integer.toString(hour);
        }
        if(mins<10)
        {
            minutesString = "0"+ mins;
        }
        else
        {
            minutesString =  Integer.toString(mins);
        }
        if(secs<10)
        {
            secondsString = "0"+ secs;
        }
        else
        {
            secondsString =  Integer.toString(secs);
        }
        String time_text = hoursString + ":" + minutesString +":"+secondsString;
        timerTextView.setText(time_text);

        //


        pos = position;
    }


    public static void updateCompletedTime(int seconds)
    {
        taskDetails1.setTimeSpent(seconds);
    }




}

