package com.sounakmondal.a10khours.RecyclerViews;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.sounakmondal.a10khours.Data;
import com.sounakmondal.a10khours.Fragments.taskSelectorView;
import com.sounakmondal.a10khours.MainActivity;
import com.sounakmondal.a10khours.R;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

public class TaskSelectorAdapter extends RecyclerView.Adapter<TaskSelectorViewHolder>  {
    public static ArrayList<Data> data1;
    public onTaskListener onTaskListener;
    public Context mContext;



    @NonNull
    @Override
    public TaskSelectorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View rootView = inflater.inflate(R.layout.task_selector_element, parent, false);

        return new TaskSelectorViewHolder(rootView, onTaskListener);//

    }

    public TaskSelectorAdapter(ArrayList<Data> data, onTaskListener onTaskListener)
    {
        this.onTaskListener = onTaskListener;//
        data1 = data;

    }



    @Override
    public void onBindViewHolder(@NonNull final TaskSelectorViewHolder holder, final int position)
    {
        holder.getHoursCompleted().setText(Integer.toString(getData().get(position).getTimeSpent()));
        holder.getTaskName().setText(getData().get(position).getTaskName());
        holder.getHoursLeft().setText(hoursLeft(getData().get(position).getTimeSpent()));

        holder.buttonViewOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //creating a popup menu
                PopupMenu popup = new PopupMenu(mContext, holder.buttonViewOption);
                //inflating menu from xml resource
                popup.inflate(R.menu.task_element_menu);
                //adding click listener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.delete_optionID:
                                removeData(position);
                                EventBus.getDefault().post(new DataSync(data1)); //calling event bus
                                notifyDataSetChanged();

                                //handle menu1 click
                                return true;
                            case R.id.reset_optionID:
                                resetData(position);
                                notifyDataSetChanged();
                                //handle menu2 click
                                return true;
                            default:
                                return false;
                        }
                    }
                });
                //displaying the popup
                popup.show();

            }
        });


    }

    public String hoursLeft(int hoursCompleted)
    {
        int hoursleft = 10000 - hoursCompleted/3600;
        return Integer.toString(hoursleft);
    }

    public static ArrayList<Data> getData()
    {
        if (data1 == null)
        {
            data1 = new ArrayList<Data>();
        }
//        ArrayList<Data> data = new ArrayList<>();
//        for(int i=0; i<1; i++){
//            data.add(new Data(
//                    "Task Name - "+i,i
//            ));
//        }
        return data1;
    }

    public static void setData(int position, String taskName, int timeCompleted)
    {
        data1.get(position).setTaskName(taskName);
        data1.get(position).setTimeSpent(timeCompleted);
    }

    public static ArrayList<Data> newData()
    {
        data1.add(new Data("Task Name", 0));
        return data1;
    }
    public static ArrayList<Data> resetData(int position)
    {
        data1.get(position).setTimeSpent(0);
        return data1;
    }

    public static ArrayList<Data> removeData(int position)
    {

        data1.remove(position);
        return data1;
    }


    @Override
    public int getItemCount() {
        if(getData() == null)
        {
            return 0;
        }
        return getData().size();
    }

    //EVENTBUS POJO CLASS (STEP-1)
    public static class DataSync
    {
        public ArrayList<Data> data0 = new ArrayList<>();

        public DataSync(ArrayList<Data> data0){
            this.data0 = data0;
        }
    }

    //recyclerview item touch listener interface
    //step1 - declare interface
    //step2 - go to fragment -> implement and add methods -> Do what is required (like changing activities etc.) in the method
    //step3 - implement onClickListener in the ViewHolder class so that the interface can detect click
    //step4 - make the changes in variables required in the main activity as well where the adapter is called and set
    public interface onTaskListener
    {
        void onTaskClick(int position);
    }


}
