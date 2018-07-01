package br.edu.iff.pooa20181.safepass;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        if (fab != null) {
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    launchMessage("Ainda não implementado!");
                }
            });
        }

        this.drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, this.drawerLayout, toolbar, R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        if (this.drawerLayout != null) {
            this.drawerLayout.addDrawerListener(toggle);
        }
        toggle.syncState();

        this.navigationView = (NavigationView) findViewById(R.id.nav_view);

        if (this.navigationView != null) {
            this.navigationView.setNavigationItemSelectedListener(this);
        }
    }

    @Override
    public void onBackPressed() {

        if (this.drawerLayout != null) {
            if (this.drawerLayout.isDrawerOpen(GravityCompat.START)) {
                this.drawerLayout.closeDrawer(GravityCompat.START);

            } else {
                super.onBackPressed();
            }
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.settings_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.nav_nova_conta:

                this.drawerLayout.closeDrawer(GravityCompat.START);
                launchMessage("Criar nova conta!");
                return true;

            case R.id.nav_gerar_senha:

                this.drawerLayout.closeDrawer(GravityCompat.START);
                launchMessage("Ainda não implementado!");
                return true;
            case R.id.nav_manage:

                this.drawerLayout.closeDrawer(GravityCompat.START);

                launchMessage("Ainda não implementado!");

                return true;
            case R.id.nav_log_out:

                HomeActivity.this.finish();
                return true;

            default:
                return false;
        }

    }

    public void launchMessage(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }
}
