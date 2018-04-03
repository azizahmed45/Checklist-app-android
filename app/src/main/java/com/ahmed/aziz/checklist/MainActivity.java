package com.ahmed.aziz.checklist;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    public static MyListAddapter addapter;
    public static ListView list;
    private Activity context;
    private Button addButton, deleteButton;
    private CheckBox checkBox;
    //DB object
    final DBHelper db = new DBHelper(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;


        //List
        addapter = new MyListAddapter(this, db.getLists());
        list = findViewById(R.id.list);
        list.setAdapter(addapter);


        //Add New Button
        addButton = findViewById(R.id.add_button);
        final AddNewDialog dialog = new AddNewDialog(this);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.showDialog();
            }
        });


        //Delete Button
        deleteButton = findViewById(R.id.delete_button);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.delete();
                updateAddapter(context, db);
            }
        });


    }

    public static void updateAddapter(Activity context, DBHelper db){
        addapter = new MyListAddapter(context, db.getLists());
        //addapter.notifyDataSetChanged();
        list.setAdapter(addapter);
    }

}
