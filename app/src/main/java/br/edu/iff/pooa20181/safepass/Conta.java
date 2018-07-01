package br.edu.iff.pooa20181.safepass;

import org.w3c.dom.Text;

import io.realm.RealmObject;
import io.realm.annotations.Ignore;
import io.realm.annotations.PrimaryKey;

public class Conta extends RealmObject {

    @PrimaryKey
    private int id;
    private String urlSite;
    private String nomedaConta;
    @Ignore
    private String password;
    private String login;
    private String hashPassword;
    private Text notas;

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

    public Text getNotas() {
        return notas;
    }

    public void setNotas(Text notas) {
        this.notas = notas;
    }
}
