package com.sounakmondal.a10khours.Fragments;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sounakmondal.a10khours.Data;
import com.sounakmondal.a10khours.MainActivity;
import com.sounakmondal.a10khours.R;
import com.sounakmondal.a10khours.RecyclerViews.TaskSelectorAdapter;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class taskSelectorView extends Fragment {


    ArrayList<Data> data;
    LayoutInflater mLayoutInflater;
    View rootView;
    TaskSelectorAdapter adapter;


    public static taskSelectorView newInstance() {
        getRecyclerViewAdapter().notifyDataSetChanged();
        return new taskSelectorView();
    }



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mLayoutInflater = inflater;
        rootView = inflater.inflate(R.layout.task_selector_layout,container,false);
        RecyclerView taskSelectorRecyclerView = rootView.findViewById(R.id.task_selector_layoutID);
        data = TaskSelectorAdapter.getData();
        adapter = new TaskSelectorAdapter(data);
        taskSelectorRecyclerView.setAdapter(adapter);
        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(rootView.getContext());
        mLinearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        taskSelectorRecyclerView.setLayoutManager(mLinearLayoutManager);

        return rootView;
    }

    public static TaskSelectorAdapter getRecyclerViewAdapter()
    {
        final TaskSelectorAdapter adapter = new TaskSelectorAdapter(TaskSelectorAdapter.getData());
        return adapter;
    }














//        final View view = mLayoutInflater.inflate(R.layout.dialog_layout, null);
//        AlertDialog alertDialog = new AlertDialog.Builder(getContext()).create();
//
//        alertDialog.setTitle("New Task");
//        alertDialog.setCancelable(false);
//        Constant.alertDialog.setMessage("Your Message Here");
//
//
//        final EditText etComments = (EditText) view.findViewById(R.id.etComments);
//
//        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK", new OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialog, int which) {
//
//                }
//            });
//
//
//        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Cancel", new OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialog, int which) {
//                    alertDialog.dismiss()
//                }
//            });
//
//
//    alertDialog.setView(view);
//    alertDialog.show();


}
