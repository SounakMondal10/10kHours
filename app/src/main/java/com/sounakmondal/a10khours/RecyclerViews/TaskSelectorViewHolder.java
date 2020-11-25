package com.sounakmondal.a10khours.RecyclerViews;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sounakmondal.a10khours.R;

public class TaskSelectorViewHolder extends RecyclerView.ViewHolder {
    TextView taskName,hoursCompleted,hoursLeft, buttonViewOption;


    public TaskSelectorViewHolder(@NonNull View itemView) {
        super(itemView);
        taskName = itemView.findViewById(R.id.taskNameText);
        hoursCompleted = itemView.findViewById(R.id.hoursCompletedText);
        hoursLeft = itemView.findViewById(R.id.hoursLeftText);
        buttonViewOption = itemView.findViewById(R.id.textViewOptions);
    }

    public TextView getTaskName() {
        return taskName;
    }

    public TextView getHoursCompleted() {
        return hoursCompleted;
    }

    public TextView getHoursLeft() {
        return hoursLeft;
    }

    public TextView getButtonViewOption() {
        return buttonViewOption;
    }

    public void setTaskName(TextView taskName) {
        this.taskName = taskName;
    }

    public void setHoursCompleted(TextView hoursCompleted) {
        this.hoursCompleted = hoursCompleted;
    }

    public void setHoursLeft(TextView hoursLeft) {
        this.hoursLeft = hoursLeft;
    }

    public void setButtonViewOption(TextView buttonViewOption) {
        this.buttonViewOption = buttonViewOption;
    }
}
