package com.sounakmondal.a10khours;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.sounakmondal.a10khours.Fragments.timerView;

public class StatsActivity extends AppCompatActivity {
    Data taskData;

    public static int taskTimeCompleted1 =0;
    TextView spentTimeText;
    TextView timeLeftText;

    TextView taskName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);
        taskData = timerView.getTaskDetails1();
        spentTimeText = findViewById(R.id.spentTimeText);
        timeLeftText = findViewById(R.id.timeLeftText);

        taskName = findViewById(R.id.statsTaskName);
        setStats(taskData);
    }



    public void setStats(Data taskData1)
    {
        taskData1 = this.taskData;
        taskName.setText(taskData1.getTaskName());
        taskTimeCompleted1 = taskData1.getTimeSpent();

        // temporarily changing timer time to whatever it is of the selected task

        int secs = taskTimeCompleted1;
        int mins = secs / 60;
        secs = secs % 60;
        int hour = mins / 60;
        mins = mins % 60;

        int secsLeft = 3600*10000 - taskTimeCompleted1;
        int minsLeft = secsLeft / 60;
        secsLeft = secsLeft % 60;
        int hourLeft = minsLeft / 60;
        minsLeft = minsLeft % 60;


        String hoursString = "00",minutesString= "00", secondsString= "00";
        String hoursLeftString = "10000",minutesLeftString="00",secondsLeftString="00";

        if(hour<1000)
        {
            if(hour<100)
            {
                if(hour<10)
                {
                    hoursString = "000"+ hour;
                }
                else hoursString = "00"+ hour;
            }
            else hoursString = "0"+ hour;
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

        //For Time Left
        if(hourLeft<1000)
        {
            if(hourLeft<100)
            {
                if(hourLeft<10)
                {
                    hoursLeftString = "000"+ hourLeft;
                }
                else hoursLeftString = "00"+ hourLeft;
            }
            else hoursLeftString = "0"+ hourLeft;
        }
        else
        {
            hoursLeftString =  Integer.toString(hourLeft);
        }
        if(minsLeft<10)
        {

            minutesLeftString = "0"+ minsLeft;
        }
        else
        {
            minutesLeftString =  Integer.toString(minsLeft);
        }
        if(secsLeft<10)
        {
            secondsLeftString = "0"+ secsLeft;
        }
        else
        {
            secondsLeftString =  Integer.toString(secsLeft);
        }

        String time_text = hoursString + ":" + minutesString +":"+secondsString;
        String time_text_Remaining =  hoursLeftString + ":" + minutesLeftString +":"+secondsLeftString;

        spentTimeText.setText(time_text);
        timeLeftText.setText(time_text_Remaining);


    }

}
