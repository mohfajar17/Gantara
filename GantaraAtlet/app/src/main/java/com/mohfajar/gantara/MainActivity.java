package com.mohfajar.gantara;

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

import com.mohfajar.gantara.Chat.ChatActivity;
import com.mohfajar.gantara.Data.Statistik;
import com.mohfajar.gantara.Data.source.Form.FormDataSource;
import com.mohfajar.gantara.Form.FormContract;
import com.mohfajar.gantara.Form.FormFragment;
import com.mohfajar.gantara.Form.FormPresenter;
import com.mohfajar.gantara.Info.HomeContract;
import com.mohfajar.gantara.Info.HomeFragment;
import com.mohfajar.gantara.Info.HomePresenter;
import com.mohfajar.gantara.Login.LoginActivity;
import com.example.mohfajar.gantara.R;
import com.mohfajar.gantara.Profile.ProfileFragment;
import com.mohfajar.gantara.RekamMedis.RekamMedisContract;
import com.mohfajar.gantara.RekamMedis.RekamMedisFragment;
import com.mohfajar.gantara.RekamMedis.RekamMedisPresenter;
import com.mohfajar.gantara.Statistik.StatistikContract;
import com.mohfajar.gantara.Statistik.StatistikFragment;
import com.mohfajar.gantara.Statistik.StatistikPresenter;
import com.mohfajar.gantara.Tanggapan.TanggapanContract;
import com.mohfajar.gantara.Tanggapan.TanggapanFragment;
import com.mohfajar.gantara.Tanggapan.TanggapanPresenter;
import com.mohfajar.gantara.Tentang.TentangFragment;
import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity implements
        NavigationView.OnNavigationItemSelectedListener,
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

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View headerView = navigationView.getHeaderView(0);

        textName = (TextView) headerView.findViewById(R.id.textName);
        textName.setText(sharedPrefManager.getName());
        textId = (TextView) headerView.findViewById(R.id.textId);
        textId.setText(sharedPrefManager.getAtletID());
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
            fragment = FormFragment.newInstance();
            new FormPresenter(Injection.provideFormRepository(this),(FormContract.View) fragment);
        } else if (id == R.id.nav_tanggapan) {
            fragment = TanggapanFragment.newInstance(1);
            new TanggapanPresenter(Injection.provideTanggapanRepository(this), (TanggapanContract.View) fragment);
        } else if (id == R.id.nav_rekam_medis) {
            fragment = RekamMedisFragment.newInstance(1);
            new RekamMedisPresenter(Injection.provideRekamMedisRepository(this), (RekamMedisContract.View) fragment);
        } else if (id == R.id.nav_statistik) {
            fragment = StatistikFragment.newInstance(1);
            new StatistikPresenter(Injection.provideStatistikRepository(this), (StatistikContract.View) fragment);
        } else if (id == R.id.nav_chat) {
            startActivity(new Intent(MainActivity.this, ChatActivity.class));
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
        // Handle navigation view item clicks here.
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
        swapFragment(id);
    }

}
