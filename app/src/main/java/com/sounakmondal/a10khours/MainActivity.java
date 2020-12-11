package com.sounakmondal.a10khours;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
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
import com.sounakmondal.a10khours.Dialogues.EnterActivityNameDialogue;
import com.sounakmondal.a10khours.Fragments.taskSelectorView;
import com.sounakmondal.a10khours.RecyclerViews.TaskSelectorAdapter;
import com.sounakmondal.a10khours.ViewPager.Pageradapter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Objects;
import java.util.prefs.Preferences;

import static com.sounakmondal.a10khours.RecyclerViews.TaskSelectorAdapter.getData;
import static com.sounakmondal.a10khours.RecyclerViews.TaskSelectorAdapter.setData;

public class MainActivity extends AppCompatActivity implements TaskSelectorAdapter.onTaskListener, EnterActivityNameDialogue.EnterActivityNameDialogueListener {


// Subscribing to event bus and mentioning the action to be taken when the action in adapter is done
    @Subscribe (threadMode = ThreadMode.MAIN)
    public void onDataSync(TaskSelectorAdapter.DataSync dataSync)
    {
        data1 = dataSync.data0;
        saveArrayList(data1,key);
    }



    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    //Arraylist Manipulation
    public void saveArrayList(ArrayList<Data> list, String key){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor = prefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(list);
        editor.putString(key, json);
        editor.apply();
    }

    public ArrayList<Data> getArrayList(String key){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        Gson gson = new Gson();
        String json = prefs.getString(key, null);
        Type type = new TypeToken<ArrayList<Data>>() {}.getType();
        return gson.fromJson(json, type);
    }

    String key = "key";
    ArrayList<Data> data1 = new ArrayList<>();
    FloatingActionButton fab;
    ViewPager pager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //Variables declaration

         data1 = getArrayList(key);



        final TaskSelectorAdapter taskSelectorAdapter;
        taskSelectorAdapter = new TaskSelectorAdapter(data1, this); //possible issue



        //ViewPager Initiation
        pager = findViewById(R.id.main_viewPager);
        final PagerAdapter adapter = new Pageradapter(getSupportFragmentManager());
        TabLayout tabLayout = findViewById(R.id.main_tabLayout);
        pager.setAdapter(adapter);
        tabLayout.setupWithViewPager(pager);


        fab = findViewById(R.id.fab);



        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                taskSelectorView.newInstance();
                Context context = getApplicationContext();
                LayoutInflater mLayoutInflater = LayoutInflater.from(getApplicationContext());

                final View view = mLayoutInflater.inflate(R.layout.dialog_layout, null);

                new MaterialAlertDialogBuilder(MainActivity.this)
                        .setTitle("Add a New Task")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
//                                data1 = taskSelectorAdapter.newData();
                                taskSelectorAdapter.notifyItemInserted(which-1);
                                EnterActivityNameDialogue.setNewItemOrRename();
                                openDialog();
                                saveArrayList(data1,key);
                                //taskSelectorAdapter.notifyItemInserted(which);
                                //new TaskSelectorAdapter(TaskSelectorAdapter.getData()).notifyItemInserted(which);
                                //taskSelectorView.getRecyclerViewAdapter().notifyItemInserted(TaskSelectorAdapter.getData().size());

                                Log.i("onClick num elements =",Integer.toString(data1.size()));
                            }
                        })
                        .setNegativeButton("No", /* listener = */ new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Log.i("onClick negative =","null");
                            }
                        })
                        .show();

            }
        });
    }


    @Override
    public void onTaskClick(int position) {
        Log.i("Clicked","OnTaskClick Executed");
        pager.setCurrentItem(1, true);
    }

    @Override
    public void applyTaskName(String taskName, int position) {
        setData(position,taskName,getData().get(position).getTimeSpent());
    }

    public void openDialog() {
        EnterActivityNameDialogue dialog = new EnterActivityNameDialogue();
        dialog.show(taskSelectorView.getTSVfragmentManager(), "example dialog");
    }
}
