package com.example.maricor.helloworld;

/**
 * Created by maricor on 8/4/15.
 */
public class User {

    String name, username, password;
    Integer age;

    public User (String name, Integer age, String username, String password ){
        this.name = name;
        this.age = age;
        this.username = username;
        this.password = password;

    }

    public User (String username, String password){
        this.username = username;
        this.password = password;
        this.age = 1;
        this.name = "";

    }

}
