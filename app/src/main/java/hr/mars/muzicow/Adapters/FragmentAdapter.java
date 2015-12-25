/*
 * Copyright (C) 2015 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package hr.mars.muzicow.Adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import hr.mars.muzicow.R;
import hr.mars.muzicow.RESTful.model.DJ;
import hr.mars.muzicow.fragments.DJ.EditProfileFragment;
import hr.mars.muzicow.Login.Login;
import hr.mars.muzicow.fragments.User.AboutDJFragment;
import hr.mars.muzicow.fragments.DJ.CreateEventFragment;
import hr.mars.muzicow.fragments.User.EventFragment;
import hr.mars.muzicow.fragments.User.PlaylistFragment;

/**
 * TODO
 */
public class FragmentAdapter extends AppCompatActivity {
    private DrawerLayout mDrawerLayout;
    Context context;
    String role;
    String sesija;
    int counter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();


        if (bundle != null) {

            role = bundle.getString("userRole");
            sesija = bundle.getString("sesija");

        }

        if(sesija.isEmpty()){

        }
        else {
            try {
                DJ djObject = bundle.getParcelable("hr.mars.muzicow.RESTful.model.DJ");
                Log.d("prikaz",djObject.get_ID());
            }
           catch (Exception e){
               Log.d("prikaz", e.getMessage());

           }

        }




        context = getApplicationContext();

        Toast toast = Toast.makeText(FragmentAdapter.this, role, Toast.LENGTH_LONG);
        toast.show();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final ActionBar ab = getSupportActionBar();
        //ab.setHomeAsUpIndicator(R.drawable.ic_menu);
        //ab.setDisplayHomeAsUpEnabled(true);

        //mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);


        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        if (viewPager != null) {
            setupViewPager(viewPager);
        }

        /*
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Here's a Snackbar", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        */
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);


    }

    @Override
    public void onResume() {
        super.onResume();  // Always call the superclass method first

        //role = "Korisnik";

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    /*
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
        case android.R.id.home:
            mDrawerLayout.openDrawer(GravityCompat.START);
            return true;
    }
    return super.onOptionsItemSelected(item);
    }
*/
    private void setupViewPager(ViewPager viewPager) {
        Adapter adapter = new Adapter(getSupportFragmentManager());

        if (role.equals("Korisnik")) {


            if (sesija.equals("")) {
                Intent intent = new Intent(this, Login.class);

             intent.putExtra("userRole", "Korisnik");
                startActivity(intent);
            }

            else {

                adapter.addFragment(new AboutDJFragment(), "DJ");
                adapter.addFragment(new PlaylistFragment(), "Playlist");
                adapter.addFragment(new EventFragment(), "Event");


            }


        } else {

            if(sesija.equals("")){
                Intent intent = new Intent(this, Login.class);
                intent.putExtra("userRole","DJ");
                startActivity(intent);
            }

            else {

            adapter.addFragment(new EditProfileFragment(), "Profile");
            adapter.addFragment(new CreateEventFragment(), "Manage Event");
            adapter.addFragment(new PlaylistFragment(), "Review Playlist");
              }
        }
        viewPager.setAdapter(adapter);

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            counter++;
            Toast toast = Toast.makeText(FragmentAdapter.this, "Press back to exit on more time?", Toast.LENGTH_LONG);
            toast.show();
            if (counter == 2) {
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                counter = 0;
            }
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        menuItem.setChecked(true);
                        mDrawerLayout.closeDrawers();
                        return true;
                    }
                });
    }

    static class Adapter extends FragmentPagerAdapter {
        private final List<android.support.v4.app.Fragment> mFragments = new ArrayList<>();
        private final List<String> mFragmentTitles = new ArrayList<>();
       // private final List<String> mFragmentBundle = new ArrayList<>();

        public Adapter(FragmentManager fm) {
            super(fm);
        }

        public void addFragment(android.support.v4.app.Fragment fragment, String title) {
            mFragments.add(fragment);
            mFragmentTitles.add(title);
          //  mFragmentBundle.add((bundle));

        }

        @Override
        public android.support.v4.app.Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitles.get(position);
        }
    }

}
