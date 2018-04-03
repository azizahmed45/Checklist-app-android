package com.ahmed.aziz.checklist;


import android.app.Activity;
import android.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddNewDialog {


    private Activity context;
    private AlertDialog.Builder dialogBuilder;
    private AlertDialog dialog;
    private Button addButton, cancelButton;
    private EditText listEditText;

    AddNewDialog(Activity context){
        this.context = context;
    }


    public void showDialog() {
        //Dialog
        dialogBuilder = new AlertDialog.Builder(context);
        LayoutInflater inflater = context.getLayoutInflater();
        final View view = inflater.inflate(R.layout.add_new_dialog, null, true);
        dialogBuilder.setCancelable(false);
        dialogBuilder.setView(view);
        dialog = dialogBuilder.create();


        //EditText
        listEditText = view.findViewById(R.id.add_new_field);

        //DB Object
        final DBHelper db = new DBHelper(context);

        //Dialog Button
        addButton = (Button)view.findViewById(R.id.add_new_add);
        cancelButton = (Button)view.findViewById(R.id.add_new_cancel);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = listEditText.getText().toString();
                CheckList checkList = new CheckList(title, false);
                db.setList(checkList);
                MainActivity.updateAddapter(context, db);
                dialog.cancel();
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
            }
        });

        dialog.show();
    }

}
