package br.edu.iff.pooa20181.safepass;

import android.util.Base64;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import io.realm.Realm;


public class ManageContaActivity extends AppCompatActivity {

    private EditText tNomeDaConta, tUrlSite, tLogin, tPass, tNotas;
    private Button btnSalvar, btnAtualizar, btnDeletar;

    private int id;
    private Conta conta;
    private Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_conta);

        this.bind();

        Intent intent = this.getIntent();
        this.id = (int) intent.getSerializableExtra("id");
        this.realm = Realm.getDefaultInstance();

        if(this.id != 0){

            this.btnSalvar.setEnabled(false);

            this.conta = this.realm.where(Conta.class).equalTo("id",this.id).findFirst();

            this.tNomeDaConta.setText(this.conta.getNomedaConta());
            this.tUrlSite.setText(this.conta.getUrlSite());
            this.tLogin.setText(this.conta.getLogin());

            //Persistência de senha encriptada será implementada posteriormente!
            //byte[] pass = Base64.decode(this.conta.getHashPassword(), Base64.DEFAULT);

            this.tPass.setText(this.conta.getHashPassword());
            this.tNotas.setText(this.conta.getNotas());


        }else{

            this.btnDeletar.setEnabled(false);
            this.btnAtualizar.setEnabled(false);

        }


        this.btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ManageContaActivity.this.salvar();
            }
        });

        this.btnAtualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ManageContaActivity.this.atualizar();
            }
        });

        this.btnDeletar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ManageContaActivity.this.deletar();
            }
        });


    }

    private void salvar(){

        int nextID = 1;

        if(this.realm.where(Conta.class).max("id") != null){
            nextID = this.realm.where(Conta.class).max("id").intValue() + 1;
        }

        this.realm.beginTransaction();

        Conta c = new Conta();
        c.setId(nextID);

        populate(c);

        this.realm.copyToRealm(c);
        this.realm.commitTransaction();
        this.realm.close();

        Toast.makeText(this,"Conta cadastrada com Sucesso!",Toast.LENGTH_LONG).show();
        this.finish();
    }

    private void atualizar(){
        realm.beginTransaction();

        populate(this.conta);

        realm.copyToRealm(this.conta);
        realm.commitTransaction();
        realm.close();

        Toast.makeText(this,"Conta atualizada com sucesso!",Toast.LENGTH_LONG).show();
        this.finish();
    }

    private void deletar(){
        realm.beginTransaction();
        this.conta.deleteFromRealm();
        realm.commitTransaction();
        realm.close();

        Toast.makeText(this,"Conta Excluída com sucesso!",Toast.LENGTH_LONG).show();
        this.finish();
    }

    private void bind(){
        this.tNomeDaConta = findViewById(R.id.tNomeConta);
        this.tUrlSite = findViewById(R.id.tUrl);
        this.tLogin = findViewById(R.id.tLogin);
        this.tPass = findViewById(R.id.tPass);
        this.tNotas = findViewById(R.id.tNotas);

        this.btnSalvar = findViewById(R.id.btnSalvar);
        this.btnAtualizar = findViewById(R.id.btnAtualizar);
        this.btnDeletar = findViewById(R.id.btnDeletar);

    }

    private void populate(Conta conta){

        conta.setNomedaConta(this.tNomeDaConta.getText().toString());
        conta.setUrlSite(this.tUrlSite.getText().toString());
        conta.setLogin(this.tLogin.getText().toString());

        //byte[] pass = this.tPass.getText().toString().getBytes();

        //Persistência de senha encriptada será implementada posteriormente!
        //conta.setHashPassword(String.valueOf(Base64.encode(pass, Base64.DEFAULT)));
        conta.setHashPassword(this.tPass.getText().toString());

        conta.setNotas(this.tNotas.getText().toString());
    }
}
