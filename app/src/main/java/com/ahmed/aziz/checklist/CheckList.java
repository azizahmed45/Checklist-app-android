package com.ahmed.aziz.checklist;



public class CheckList {
    private int id;
    private String title;
    private boolean isChecked;


    CheckList(String title, boolean isChecked){
        this.title = title;
        this.isChecked = isChecked;
    }

    //For create object from database;
    CheckList(int id, String title, boolean isChecked){
        this.id = id;
        this.title = title;
        this.isChecked = isChecked;
    }

    ///Get methods
    public int getId(){
        return id;
    }

    public String getTitle(){
        return title;
    }


    public boolean isChecked(){
        return isChecked;
    }


    ///Set methods
    public void setId(int id){
        this.id = id;
    }


    public void setTitle(String title){
        this.title = title;
    }


    public void setChecked(boolean isChecked){
        this.isChecked = isChecked;
    }

}
