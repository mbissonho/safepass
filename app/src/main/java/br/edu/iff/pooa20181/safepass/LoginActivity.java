package br.edu.iff.pooa20181.safepass;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import io.realm.Realm;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if(savedInstanceState == null){
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.fragmentArea, new LoginFragment())
                    .commit();
        }
    }

    /*@Override
    public void onBackPressed() {

        Fragment f = this.getFragmentManager().findFragmentById(R.id.fragmentArea);

        if(f instanceof RegistryFragment){

        }else{
            super.onBackPressed();
        }

    }*/
}
