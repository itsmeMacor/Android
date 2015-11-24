package com.example.maricor.helloworld;

import android.content.SharedPreferences;
import android.content.Context;


/**
 * Created by maricor on 8/4/15.
 */
public class UserLocalStore {

    public static final String SP_Name = "userDetails";
    SharedPreferences userLocalDatabase;

    public UserLocalStore(Context context){
        userLocalDatabase = context.getSharedPreferences(SP_Name, 0);
    }
    public void storedUserData(User user){
        SharedPreferences.Editor spEditor = userLocalDatabase.edit();
        spEditor.putString("name", user.name);
        spEditor.putInt("age", user.age);
        spEditor.putString("username", user.username);
        spEditor.putString("password", user.password);

        spEditor.commit();
    }

    public User getLoggedInUser(){

        String name = userLocalDatabase.getString("name", "");
        int age = userLocalDatabase.getInt("age", 1);
        String username = userLocalDatabase.getString("username", "");
        String password = userLocalDatabase.getString("password", "");


        User storedUser = new User (name, age, username, password);
        return  storedUser;

    }

    public void setUserLoggedIn(boolean loggedIn){
         SharedPreferences.Editor spEditor = userLocalDatabase.edit();
         spEditor.putBoolean("loggedIn", loggedIn);
         spEditor.commit();
    }

    public boolean getUserLoggedIn(){
        if (userLocalDatabase.getBoolean("loggedIn", false) == true){
            return true;
        }
        else{
            return false;
        }

    }

    public void clearUserData(){
        SharedPreferences.Editor spEditor = userLocalDatabase.edit();
        spEditor.clear();
        spEditor.commit();

    }


}
