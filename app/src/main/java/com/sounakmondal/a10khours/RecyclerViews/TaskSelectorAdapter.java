package com.sounakmondal.a10khours.RecyclerViews;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sounakmondal.a10khours.Data;
import com.sounakmondal.a10khours.R;

import java.util.ArrayList;

public class TaskSelectorAdapter extends RecyclerView.Adapter<TaskSelectorViewHolder> {
    ArrayList<Data> data;

    public ArrayList<Data> getData() {
        return data;
    }

    @NonNull
    @Override
    public TaskSelectorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View rootView = inflater.inflate(R.layout.task_selector_element, parent, false);
        return new TaskSelectorViewHolder(rootView);
    }

    public TaskSelectorAdapter(ArrayList<Data> data1)
    {
        data = data1;
    }



    @Override
    public void onBindViewHolder(@NonNull TaskSelectorViewHolder holder, int position)
    {
        holder.getHoursCompleted().setText(Integer.toString(getData().get(position).getTimeSpent()));
        holder.getTaskName().setText(getData().get(position).getTaskName());
        holder.getHoursLeft().setText(hoursLeft(getData().get(position).getTimeSpent()));
    }

    public String hoursLeft(int hoursCompleted)
    {
        int hoursleft = 10000 - hoursCompleted;
        return Integer.toString(hoursleft);
    }


    @Override
    public int getItemCount() {
        return getData().size();
    }
}
