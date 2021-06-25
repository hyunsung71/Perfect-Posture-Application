package com.example.turtleneckdiagnosticapplication;

import android.widget.EditText;

public class MemberInfo {
    private String name;
    private String birthday;

    public MemberInfo(String name, String birthday){
        this.name = name;
        this.birthday = birthday;
    }

    public String getName(){
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getBirthday(){
        return this.birthday;
    }
    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }
}
