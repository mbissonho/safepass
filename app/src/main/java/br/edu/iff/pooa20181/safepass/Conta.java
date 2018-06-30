package br.edu.iff.pooa20181.safepass;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Conta extends RealmObject {

    @PrimaryKey
    private int id;
    private String urlSite;
    private String nome;
    private String password;
    private String hashPassword;
}
