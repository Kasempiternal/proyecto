package com.example.mainmenu;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.mainmenu.CreateDialog;
import com.example.mainmenu.JoinDialog;
import com.example.mainmenu.R;

import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.fragment.app.FragmentManager;


public class AskDialog extends AppCompatDialogFragment {

    private Button create, join;


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.popup_askjoincreate, null);
        create = view.findViewById(R.id.createbt);
        join = view.findViewById(R.id.joinbt);


        create.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                openPopupCreate();
            }
        });

        join.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                openPopupJoin();
            }
        });


        builder.setView(view)
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });


        return builder.create();
    }

    public void openPopupJoin() {
        JoinDialog jd = new JoinDialog();
        jd.show(getFragmentManager(), "");
    }


    public void openPopupCreate() {
        CreateDialog createDialog = new CreateDialog();
        createDialog.show(getFragmentManager(), "create");
    }
}
