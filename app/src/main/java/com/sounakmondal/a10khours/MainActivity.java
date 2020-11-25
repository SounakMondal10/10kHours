package com.sounakmondal.a10khours;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sounakmondal.a10khours.Fragments.taskSelectorView;
import com.sounakmondal.a10khours.RecyclerViews.TaskSelectorAdapter;
import com.sounakmondal.a10khours.ViewPager.Pageradapter;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    //Arraylist Manipulation todo
    public void saveArrayList(ArrayList<Data> list, String key){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor = prefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(list);
        editor.putString(key, json);
        editor.apply();

    }

    public ArrayList<String> getArrayList(String key){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        Gson gson = new Gson();
        String json = prefs.getString(key, null);
        Type type = new TypeToken<ArrayList<Data>>() {}.getType();
        return gson.fromJson(json, type);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ArrayList<Data> data1 = new ArrayList<>();
        data1 = TaskSelectorAdapter.getData();




        ViewPager pager = findViewById(R.id.main_viewPager);
        final PagerAdapter adapter = new Pageradapter(getSupportFragmentManager());
        TabLayout tabLayout = findViewById(R.id.main_tabLayout);
        pager.setAdapter(adapter);
        tabLayout.setupWithViewPager(pager);
        taskSelectorView.newInstance();
        FloatingActionButton fab = findViewById(R.id.fab);

        final ArrayList<Data> finalData = data1;

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                taskSelectorView.newInstance();
                Context context = getApplicationContext();
                LayoutInflater mLayoutInflater = LayoutInflater.from(getApplicationContext());
                final View view = mLayoutInflater.inflate(R.layout.dialog_layout, null);

                new MaterialAlertDialogBuilder(MainActivity.this)
                        .setTitle("New Task")
                        .setMessage("Type the name of your task")
                        .setPositiveButton("Save", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                TaskSelectorAdapter.newData();
                                new TaskSelectorAdapter(TaskSelectorAdapter.getData()).notifyItemInserted(which);
                                taskSelectorView.getRecyclerViewAdapter().notifyItemInserted(TaskSelectorAdapter.getData().size()-1);
                                taskSelectorView.newInstance();
                                Log.i("onClick num elements =",Integer.toString(finalData.size()));
                            }
                        })
                        .setNegativeButton("Cancel", /* listener = */ null)
                        .show();

                taskSelectorView.newInstance();


//                final AlertDialog alertDialog = new AlertDialog.Builder(context).create();
//
//                alertDialog.setTitle("New Task");
//                alertDialog.setCancelable(false);
//                alertDialog.setMessage("Type the name of your task");
//
//
//                final EditText etComments = (EditText) view.findViewById(R.id.etComments);
//
//                alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Save", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        setData(etComments.toString());
//
//                    }
//                });
//                alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
//
//                    public void onClick(DialogInterface dialog, int which) {
//                        alertDialog.dismiss();
//                    }
//                });
//
//
//                alertDialog.setView(view);
//                alertDialog.show();
            }
        }
        )
                ;

    }
}
