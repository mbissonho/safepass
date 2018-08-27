package br.edu.iff.pooa20181.safepass.model;

import java.io.Serializable;

import io.realm.RealmObject;
import io.realm.annotations.Ignore;
import io.realm.annotations.PrimaryKey;

public class SafePass extends RealmObject implements Serializable {

    @PrimaryKey
    private int id;
    @Ignore
    private static SafePass instance = null;

    public SafePass(){}

    private String email, masterPass;

    public static  void setInstance(SafePass safePass){
        instance = safePass;
    }

    public static SafePass getInstance(){
        return instance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMasterPass() {
        return masterPass;
    }

    public void setMasterPass(String masterPass) {
        this.masterPass = masterPass;
    }
}
