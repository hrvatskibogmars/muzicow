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

package hr.mars.muzicow.Activities.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import hr.mars.muzicow.Activities.MainActivity;
import hr.mars.muzicow.R;
import hr.mars.muzicow.Models.DJ;
import hr.mars.muzicow.Activities.Fragments.DJ.EditProfileFragment;
import hr.mars.muzicow.Activities.Fragments.DJ.ManageEventFragment;
import hr.mars.muzicow.Activities.Fragments.User.EventFragment;
import hr.mars.muzicow.Activities.Fragments.User.PlaylistFragment;
import hr.mars.muzicow.Registry.Registry;


public class FragmentAdapter extends AppCompatActivity {

    Context context;
    String role;
    String session;
    int counter;
    DJ djObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        if (bundle != null) {
            role = bundle.getString("userRole");
            session = bundle.getString("Session");
        }


        if(session.isEmpty()){

        }
        else {
            try {
                djObject = bundle.getParcelable("Twitter object");
                Registry.getInstance().set("djObject",djObject);

            }
           catch (Exception e){
               Log.d("Show", e.getMessage());
           }
        }

        context = getApplicationContext();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final ActionBar ab = getSupportActionBar();

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        if (viewPager != null) {
            setupViewPager(viewPager);
        }

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);


    }


    @Override
    public void onResume() {
        super.onResume();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    private void setupViewPager(ViewPager viewPager) {
        Adapter adapter = new Adapter(getSupportFragmentManager());

        switch(role) {
            case "Participant":
                if (session.isEmpty()) {
                    Intent intent = new Intent(this, MainActivity.class);
                    intent.putExtra("userRole", "Participant");
                    startActivity(intent);
                } else {

                   // adapter.addFragment(new AboutDJActivity(), "DJ");
                    adapter.addFragment(new EventFragment(), "Event");
                    adapter.addFragment(new PlaylistFragment(), "Playlist");
                }
                break;
            case "Artist":
                if (session.isEmpty()) {
                    Intent intent = new Intent(this, MainActivity.class);
                    intent.putExtra("userRole", "DJ");
                    startActivity(intent);
                } else {

                    adapter.addFragment(new EditProfileFragment(), "Profile");
                    adapter.addFragment(new ManageEventFragment(), "Manage Event");
                    adapter.addFragment(new PlaylistFragment(), "Review Playlist");
                }
                break;

        }

        viewPager.setAdapter(adapter);

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            counter++;
            Toast toast = Toast.makeText(FragmentAdapter.this, "Press back to exit one more time?", Toast.LENGTH_LONG);
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

    static class Adapter extends FragmentPagerAdapter {
        private final List<android.support.v4.app.Fragment> mFragments = new ArrayList<>();
        private final List<String> mFragmentTitles = new ArrayList<>();

        public Adapter(FragmentManager fm) {
            super(fm);
        }

        public void addFragment(android.support.v4.app.Fragment fragment, String title) {
            mFragments.add(fragment);
            mFragmentTitles.add(title);

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
