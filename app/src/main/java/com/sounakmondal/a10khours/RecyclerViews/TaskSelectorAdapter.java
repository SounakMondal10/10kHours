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

    public Context mContext;



    @NonNull
    @Override
    public TaskSelectorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View rootView = inflater.inflate(R.layout.task_selector_element, parent, false);

        return new TaskSelectorViewHolder(rootView);

    }

    public TaskSelectorAdapter(ArrayList<Data> data)
    {
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

                                //DELETED ITEMS NOT UPDATING BECAUSE
                                //DATA CHANGED HERE IS NOT UPDATING THE MAIN
                                //DATA1 ARRAYLIST IN MAINACTIVITY WHERE IT'S
                                //STORED IN SHAREDPREFS


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
    { int hoursleft =0;
        hoursleft = 10000 - hoursCompleted;
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

    public ArrayList<Data> setData(ArrayList<Data> data0)
    {
        data1.clear();
        data1 = data0;
        return data1;
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
    public class DataSync
    {
        public ArrayList<Data> data0 = new ArrayList<>();

        public DataSync(ArrayList<Data> data0){
            this.data0 = data0;
        }
    }




}
