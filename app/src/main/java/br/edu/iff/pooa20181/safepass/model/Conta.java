package br.edu.iff.pooa20181.safepass.model;

import org.w3c.dom.Text;

import java.io.Serializable;

import io.realm.RealmObject;
import io.realm.annotations.Ignore;
import io.realm.annotations.PrimaryKey;

public class Conta extends RealmObject implements Serializable {

    @PrimaryKey
    private int id;
    private String urlSite;
    private String nomedaConta;
    @Ignore
    private String password;
    private String login;
    private String hashPassword;
    private String notas;

    public Conta(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrlSite() {
        return urlSite;
    }

    public void setUrlSite(String urlSite) {
        this.urlSite = urlSite;
    }

    public String getNomedaConta() {
        return nomedaConta;
    }

    public void setNomedaConta(String nomedaConta) {
        this.nomedaConta = nomedaConta;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getHashPassword() {
        return hashPassword;
    }

    public void setHashPassword(String hashPassword) {
        this.hashPassword = hashPassword;
    }

    public String getNotas() {
        return notas;
    }

    public void setNotas(String notas) {
        this.notas = notas;
    }
}
