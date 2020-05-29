package com.gantara.mohfajar;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.gantara.mohfajar.Atlet.AtletContract;
import com.gantara.mohfajar.Atlet.AtletFragment;
import com.gantara.mohfajar.Atlet.AtletPresenter;
import com.gantara.mohfajar.Chat.Chat;
import com.gantara.mohfajar.Chat.ChatFragment;
import com.gantara.mohfajar.Form.FormContract;
import com.gantara.mohfajar.Form.FormFragment;
import com.gantara.mohfajar.Form.FormPresenter;
import com.gantara.mohfajar.Info.HomeContract;
import com.gantara.mohfajar.Info.HomeFragment;
import com.gantara.mohfajar.Info.HomePresenter;
import com.gantara.mohfajar.Login.LoginActivity;
import com.example.gantara.mohfajar.R;
import com.gantara.mohfajar.Profile.ProfileFragment;
import com.gantara.mohfajar.Tentang.TentangFragment;
import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity implements
        NavigationView.OnNavigationItemSelectedListener,
        ChatFragment.OnListFragmentInteractionListener,
        OnFragmentInteractionListener{

    FragmentTransaction ft;
    private FrameLayout container;
    boolean doubleBackToExitPressedOnce = false;

    private SharedPrefManager sharedPrefManager;

    private TextView textName;
    private TextView textId;
    private TextView textUserName;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPrefManager = SharedPrefManager.getInstance(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        container = (FrameLayout) findViewById(R.id.containerFragment);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View headerView = navigationView.getHeaderView(0);

        textName = (TextView) headerView.findViewById(R.id.textName);
        textName.setText(sharedPrefManager.getName());
        textId = (TextView) headerView.findViewById(R.id.textId);
        textId.setText(sharedPrefManager.getPsikologID());
        textUserName = (TextView) headerView.findViewById(R.id.textUserName);
        textUserName.setText(sharedPrefManager.getUserName());
        imageView = (ImageView) headerView.findViewById(R.id.imageView);
        Picasso.get().load(""+Config.DATA_URL_PHOTO_PROFIL+"/"+sharedPrefManager.getUserName()+".png").resize(400, 400).centerCrop().into(imageView);

        swapFragment(R.id.nav_home);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            if (doubleBackToExitPressedOnce) {
                super.onBackPressed();
                return;
            }

            this.doubleBackToExitPressedOnce = true;
            Toast.makeText(this, "Tekan sekali lagi untuk keluar", Toast.LENGTH_SHORT).show();

            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    doubleBackToExitPressedOnce=false;
                }
            }, 2000);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
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

    private void swapFragment(@IdRes int id){
        Fragment fragment = null;
        if (id == R.id.nav_home) {
            fragment = HomeFragment.newInstance(1);
            new HomePresenter(Injection.provideHomeRepository(this), (HomeContract.View) fragment);
        } else if (id == R.id.nav_form) {
            fragment = FormFragment.newInstance(1);
            new FormPresenter(Injection.provideFormRepository(this), (FormContract.View) fragment);
        } else if (id == R.id.nav_daftar_atlet) {
            fragment = AtletFragment.newInstance(1);
            new AtletPresenter(Injection.provideAtletRepository(this), (AtletContract.View) fragment);
        } else if (id == R.id.nav_chat) {
            ft = getSupportFragmentManager().beginTransaction();
            ChatFragment chatPsikologFragment = ChatFragment.newInstance(1);
            ft.replace(R.id.containerFragment, chatPsikologFragment);
            ft.disallowAddToBackStack();
            ft.commit();
        } else if (id == R.id.nav_profile) {
            ft = getSupportFragmentManager().beginTransaction();
            ProfileFragment profileFragment = ProfileFragment.newInstance();
            ft.replace(R.id.containerFragment,profileFragment);
            ft.disallowAddToBackStack();
            ft.commit();
        } else if (id == R.id.nav_tentang) {
            ft = getSupportFragmentManager().beginTransaction();
            TentangFragment tentangFragment = TentangFragment.newInstance();
            ft.replace(R.id.containerFragment,tentangFragment);
            ft.disallowAddToBackStack();
            ft.commit();
        } else if (id == R.id.nav_keluar) {
            Intent bukaLoginActivity = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(bukaLoginActivity);
            sharedPrefManager.logout();
            finish();
        }
        if(fragment!=null) {
            ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.containerFragment, fragment);
            ft.disallowAddToBackStack();
            ft.commit();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        swapFragment(item.getItemId());

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onFragmentChanged(int id) {

    }

    @Override
    public void onListFragmentInteraction(Chat item) {

    }
}
