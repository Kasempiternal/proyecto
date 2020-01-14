package com.example.mainmenu.Users;

import androidx.annotation.NonNull;

public class Users {


   String uid,name,email;


   public Users(){

    }


    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @NonNull
    @Override
    public String toString() {
        return "User{"
                + "uid'" +uid+'\''
                +", name='"+name+'\''
                +", email='"+email+'\''
                +'}';
    }
}
