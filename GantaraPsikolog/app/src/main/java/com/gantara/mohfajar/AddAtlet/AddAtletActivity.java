package com.gantara.mohfajar.AddAtlet;

import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.gantara.mohfajar.R;
import com.gantara.mohfajar.Injection;
import com.gantara.mohfajar.OnFragmentInteractionListener;

public class AddAtletActivity extends AppCompatActivity implements
        OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_atlet_main);

        Fragment fragment = null;
        fragment = AddAtletFragment.newInstance(1);
        new AddAtletPresenter(Injection.provideAddAtletRepository(this), (AddAtletContract.View) fragment);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onFragmentChanged(int id) {

    }
}