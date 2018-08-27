package br.edu.iff.pooa20181.safepass;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import br.edu.iff.pooa20181.safepass.model.SafePass;
import io.realm.Realm;

public class LoginActivity extends AppCompatActivity {

    private Realm realm;
    private ImageView safeLogo;

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

        this.safeLogo = findViewById(R.id.safeLogo);
        this.safeLogo.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                LoginActivity.this.reset();

                return false;
            }
        });

    }

    private void reset(){
        this.realm = Realm.getDefaultInstance();
        SafePass safePass = this.realm.where(SafePass.class).findFirst();
        this.realm.beginTransaction();
        safePass.deleteFromRealm();
        this.realm.commitTransaction();
        this.realm.close();
        this.finish();
    }
}
