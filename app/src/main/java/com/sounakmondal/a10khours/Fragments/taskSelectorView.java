package com.sounakmondal.a10khours.Fragments;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sounakmondal.a10khours.Data;
import com.sounakmondal.a10khours.MainActivity;
import com.sounakmondal.a10khours.R;
import com.sounakmondal.a10khours.RecyclerViews.TaskSelectorAdapter;
import com.sounakmondal.a10khours.ViewPager.Pageradapter;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class taskSelectorView extends Fragment implements TaskSelectorAdapter.onTaskListener {


    ArrayList<Data> data;
    LayoutInflater mLayoutInflater;
    View rootView;
    public TaskSelectorAdapter adapter;
    ViewPager pager;
    RecyclerView taskSelectorRecyclerView;


    public static taskSelectorView newInstance() {
        return new taskSelectorView();
    }



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mLayoutInflater = inflater;
//        rootView = inflater.inflate(R.layout.activity_main,container,false);

        rootView = inflater.inflate(R.layout.task_selector_layout,container,false);
        taskSelectorRecyclerView = rootView.findViewById(R.id.task_selector_layoutID);
        pager = rootView.findViewById(R.id.main_viewPager);
        data = TaskSelectorAdapter.getData();
        adapter = new TaskSelectorAdapter(data, this);
        taskSelectorRecyclerView.setAdapter(adapter);
        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(rootView.getContext());
        mLinearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        taskSelectorRecyclerView.setLayoutManager(mLinearLayoutManager);
        updateRecyclerView();
        Log.i("TSV onCreate","");
        return rootView;
    }

    public void updateRecyclerView()
    {
        adapter.notifyDataSetChanged();
    }
    public  RecyclerView getRecyclerView()
    {
        return taskSelectorRecyclerView;
    }

    @Override
    public void onTaskClick(int position) {
        data.get(position);
        Toast.makeText(getContext(), "Task Selected", Toast.LENGTH_SHORT).show();
        Log.i("TSV","OnTaskClick Executed");
        //add function to get data(position) for changing timer view
        timerView.setTimeFromTask(data.get(position), position);




//        Fragment fragment = new timerView();
//        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        fragmentTransaction.replace(R.id.main_viewPager, timerView.newInstance()); //issue here
//        fragmentTransaction.hide(taskSelectorView.newInstance());
//        fragmentTransaction.show(timerView.newInstance());
//        fragmentTransaction.addToBackStack(null);
//        fragmentTransaction.commit();
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
