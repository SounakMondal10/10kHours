package com.sounakmondal.a10khours.Fragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sounakmondal.a10khours.Data;
import com.sounakmondal.a10khours.R;
import com.sounakmondal.a10khours.RecyclerViews.TaskSelectorAdapter;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class taskSelectorView extends Fragment {

    public static taskSelectorView newInstance() {
        return new taskSelectorView();
    }

    ArrayList<Data> data;

    //Arraylist Manipulation
    public void saveArrayList(ArrayList<Data> list, String key){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getContext());
        SharedPreferences.Editor editor = prefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(list);
        editor.putString(key, json);
        editor.apply();

    }

    public ArrayList<String> getArrayList(String key){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getContext());
        Gson gson = new Gson();
        String json = prefs.getString(key, null);
        Type type = new TypeToken<ArrayList<Data>>() {}.getType();
        return gson.fromJson(json, type);
    }

    public void newTask()
    {
        //Adds new Task
    }




    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.task_selector_layout,container,false);
        RecyclerView taskSelectorRecyclerView = rootView.findViewById(R.id.task_selector_layoutID);
        data = getData();
        TaskSelectorAdapter adapter = new TaskSelectorAdapter(data);
        taskSelectorRecyclerView.setAdapter(adapter);
        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(rootView.getContext());
        mLinearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        taskSelectorRecyclerView.setLayoutManager(mLinearLayoutManager);
        return rootView;
    }

    private ArrayList<Data> getData()
    {
        ArrayList<Data> data = new ArrayList<>();
        for(int i=0; i<120; i++){
            data.add(new Data(
                    "Task Name - "+i,i
            ));
        }
        return data;
    }


}
