package com.mrgreenapps.checklist;

import android.app.Activity;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import java.util.ArrayList;

public class MyListAddapter extends ArrayAdapter <CheckList>{
    private Activity context;
    private TextView textView;
    private CheckBox checkBox;
    private View rawView;
    private ArrayList<CheckList> checkLists;
    MyListAddapter(Activity context, ArrayList<CheckList> checkLists){
        super(context, R.layout.list_view, checkLists);

        this.context = context;
        this.checkLists = checkLists;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = context.getLayoutInflater();
        rawView = inflater.inflate(R.layout.list_view,null, true);


        checkBox = rawView.findViewById(R.id.checkbox);
        textView = rawView.findViewById(R.id.list_text);

        checkBox.setChecked(getItem(position).isChecked());
        textView.setText(getItem(position).getTitle());
        if(getItem(position).isChecked()){
            textView.setPaintFlags(textView.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        }
        else{
            textView.setPaintFlags(textView.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
        }


        //Db
        final DBHelper db = new DBHelper(context);

        checkBox = rawView.findViewById(R.id.checkbox);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                getItem(position).setChecked(b);
                db.updateList(getItem(position));
                MainActivity.updateAddapter(context, db);
                db.close();
            }
        });

        return rawView;
    }


    //Int to boolean
    @org.jetbrains.annotations.Contract(pure = true)
    private boolean intToBoolean(int a){
        if(a==0) return false;
        else return true;
    }

}
