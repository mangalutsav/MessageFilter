package com.cfd.messagefilter;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

import eu.long1.spacetablayout.SpaceTabLayout;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    SpaceTabLayout tabLayout;
    String TAG = this.getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ArrayList<Fragment> fragmentList = new ArrayList<>();

        for (int i = 1; i < 6; i++) {
            FragmentList frag = new FragmentList();
            frag.setCategory(i);
            fragmentList.add(frag);
        }

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);
        tabLayout = (SpaceTabLayout) findViewById(R.id.spaceTabLayout);

        //we need the savedInstanceState to retrieve the position
        tabLayout.initialize(viewPager, getSupportFragmentManager(), fragmentList, savedInstanceState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_clear_all) {
            //mydb.deleteAllHistory();
            return true;
        } else if (id == R.id.action_about_us) {
            startActivity(new Intent(getApplicationContext(), AboutUsActivity.class));
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        //TODO Complete this method
        if (id == R.id.nav_invite_friends) {

        } else if (id == R.id.nav_how_to) {

        } else if (id == R.id.nav_settings) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    private void getPermission(String permission) {
        if (isPermissionAllowed(permission)) {
            //If permission is already having then showing the toast
            //Toast.makeText(MainActivity.this,"You already have the permission",Toast.LENGTH_LONG).show();
            Log.d(TAG, "getPermission() called with: " + "permission = [" + permission + "]");
            //Existing the method with retur
        } else {
            Log.d(TAG, "else getPermission() called with: " + "permission = [" + permission + "]");
            //If the app has not the permission then asking for the permission
            requestPermission(permission);
        }
    }

    private boolean isPermissionAllowed(String permission) {
        //Getting the permission status
//        permission = Manifest.permission.READ_EXTERNAL_STORAGE
        int result = ContextCompat.checkSelfPermission(this, permission);

        //If permission is granted returning true
        if (result == PackageManager.PERMISSION_GRANTED)
            return true;

        //If permission is not granted returning false
        return false;
    }

    //Requesting permission
    private void requestPermission(String permission) {

        if (ActivityCompat.shouldShowRequestPermissionRationale(this, permission)) {
            //If the user has denied the permission previously your code will come to this block
            //Here you can explain why you need this permission
            //Explain here why you need this permission
            Log.d(TAG, "requestPermission() denied previouslycalled with: " + "permission = [" + permission + "]");
        }
        Log.d(TAG, "requestPermission() called with: " + "permission = [" + permission + "]");
        //And finally ask for the permission
        ActivityCompat.requestPermissions(this, new String[]{permission}, 123);
    }

    //This method will be called when the user will tap on allow or deny
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        //Checking the request code of our request
        Log.d(TAG, "onRequestPermissionsResult() called with: " + "requestCode = [" + requestCode + "], permissions = [" + permissions + "], grantResults = [" + grantResults[0] + "]");
        if (requestCode == 123) {
            Log.d(TAG, "onRequestPermissionsResult() called with: " + "requestCode = [" + requestCode + "], permissions = [" + permissions + "], grantResults = [" + grantResults.length + "]");
            //If permission is granted
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                //Displaying a toast
                //Toast.makeText(this,"Permission granted",Toast.LENGTH_LONG).show();
            } else {
                //Displaying another toast if permission is not granted
                //Toast.makeText(this,"Oops you just denied the permission",Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        tabLayout.saveState(outState);
        super.onSaveInstanceState(outState);
    }

    public static void notifyDataLoaded() {

    }
}
