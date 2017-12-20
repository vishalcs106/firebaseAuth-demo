package com.android.firebasedemo.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.android.firebasedemo.BR;

/**
 * Created by Vishal on 19-12-2017.
 */

public class UserLogin extends BaseObservable{
    private String username;
    private String password;

    @Bindable
    public String getUsername(){
        return username;
    }

    public void setUsername(String username){
        this.username = username;
        notifyPropertyChanged(BR.username);
    }

    @Bindable
    public String getPassword(){
        return password;
    }

    public void setPassword(String password){
        this.password = password;
        notifyPropertyChanged(BR.password);
    }
}
