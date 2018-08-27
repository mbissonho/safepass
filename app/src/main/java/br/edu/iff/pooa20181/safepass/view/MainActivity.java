package br.edu.iff.pooa20181.safepass.view;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import br.edu.iff.pooa20181.safepass.HomeActivity;
import br.edu.iff.pooa20181.safepass.LoginActivity;
import br.edu.iff.pooa20181.safepass.R;
import io.realm.Realm;

public class MainActivity extends AppCompatActivity {

    private static int SPLASH_TIME = 5000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();

            }
        }, SPLASH_TIME);
    }
}
