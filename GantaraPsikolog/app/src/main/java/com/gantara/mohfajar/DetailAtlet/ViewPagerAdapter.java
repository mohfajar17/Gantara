package com.gantara.mohfajar.DetailAtlet;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.gantara.mohfajar.DetailAtlet.Biodata.BiodataFragment;
import com.gantara.mohfajar.DetailAtlet.Intensitas.IntensitasFragment;
import com.gantara.mohfajar.DetailAtlet.RekamMedis.RekamMedisContract;
import com.gantara.mohfajar.DetailAtlet.RekamMedis.RekamMedisFragment;
import com.gantara.mohfajar.DetailAtlet.RekamMedis.RekamMedisPresenter;
import com.gantara.mohfajar.DetailAtlet.Statistik.StatistikContract;
import com.gantara.mohfajar.DetailAtlet.Statistik.StatistikFragment;
import com.gantara.mohfajar.DetailAtlet.Statistik.StatistikPresenter;
import com.gantara.mohfajar.Injection;

/**
 * Created by hp1 on 21-01-2015.
 */
public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    CharSequence Titles[]; // This will Store the Titles of the Tabs which are Going to be passed when ViewPagerAdapter is created
    int NumbOfTabs; // Store the number of tabs, this will also be passed when the ViewPagerAdapter is created
    Context context;

    // Build a Constructor and assign the passed Values to appropriate values in the class
    public ViewPagerAdapter(FragmentManager fm, CharSequence mTitles[], int mNumbOfTabsumb, Context context) {
        super(fm);

        this.Titles = mTitles;
        this.NumbOfTabs = mNumbOfTabsumb;
        this.context = context;

    }

    //This method return the fragment for the every position in the View Pager
    @Override
    public Fragment getItem(int position) {

        if(position == 0){
            RekamMedisFragment tab1 = new RekamMedisFragment();
            new RekamMedisPresenter(Injection.provideRekamMedisRepository(context), (RekamMedisContract.View) tab1);
            return tab1;
        }
        else if(position == 1){
            StatistikFragment tab2 = new StatistikFragment();
            new StatistikPresenter(Injection.provideStatistikRepository(context), (StatistikContract.View) tab2);
            return tab2;
        }
        else if(position == 2){
            IntensitasFragment tab3 = new IntensitasFragment();
            return tab3;
        }
        else{
            BiodataFragment tab4 = new BiodataFragment();
            return tab4;
        }
    }

    // This method return the titles for the Tabs in the Tab Strip

    @Override
    public CharSequence getPageTitle(int position) {
        return Titles[position];
    }

    // This method return the Number of tabs for the tabs Strip

    @Override
    public int getCount() {
        return NumbOfTabs;
    }
}