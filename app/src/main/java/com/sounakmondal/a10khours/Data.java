package com.sounakmondal.a10khours;

import android.os.Parcel;
import android.os.Parcelable;

public class Data implements Parcelable {
    private String taskName;
    private int timeSpent;


    public Data(String taskName, int timeSpent) {
        this.taskName = taskName;
        this.timeSpent = timeSpent;
    }

    protected Data(Parcel in) {
        taskName = in.readString();
        timeSpent = in.readInt();
    }
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(taskName);
        dest.writeInt(timeSpent);
    }

    public String getTaskName() {
        return taskName;
    }

    public int getTimeSpent() {
        return timeSpent;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public void setTimeSpent(int timeSpent) {
        this.timeSpent = timeSpent;
    }

    public static final Creator<Data> CREATOR = new Creator<Data>() {
        @Override
        public Data createFromParcel(Parcel in) {
            return new Data(in);
        }

        @Override
        public Data[] newArray(int size) {
            return new Data[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }


}
