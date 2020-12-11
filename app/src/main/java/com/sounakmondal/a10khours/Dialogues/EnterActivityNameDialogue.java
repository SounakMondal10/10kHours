package com.sounakmondal.a10khours.Dialogues;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.sounakmondal.a10khours.Data;
import com.sounakmondal.a10khours.Fragments.taskSelectorView;
import com.sounakmondal.a10khours.R;
import com.sounakmondal.a10khours.RecyclerViews.TaskSelectorAdapter;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

public class EnterActivityNameDialogue extends DialogFragment {
    public String taskName;
    public static ArrayList<Data> dataDialogue = new ArrayList<>();
    public int position;
    public static boolean newItemOrRename = false; //true for new item, false for rename

    public static void setNewItemOrRename()
    {
        newItemOrRename = true;
    }
    public static void setNewItemOrRenameFalse()
    {
        newItemOrRename = false;
    }


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_layout, null);
        dataDialogue = TaskSelectorAdapter.getData();
        final EditText taskNameET = view.findViewById(R.id.taskNameET);
        builder.setView(view)
                .setTitle("Set Task Name")
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                })
                .setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String taskName = taskNameET.getText().toString();


                        if(newItemOrRename == true)
                        {
                            dataDialogue.add(dataDialogue.size(),new Data(taskName,0));

                        }

                        else {
                            position = taskSelectorView.getTaskPosition();
                            dataDialogue.get(position).setTaskName(taskName);
                            newItemOrRename = true;
                        }
                        //rename taskname in main ArrayList<Data>
                        EventBus.getDefault().post(new TaskSelectorAdapter.DataSync(dataDialogue)); //possible source of error maybe?
                    }
                });
        return builder.create();
    }

    public interface EnterActivityNameDialogueListener{
        void applyTaskName(String taskName, int position);
    }

}
