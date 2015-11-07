package com.vivekbalachandra.pigdice;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import java.util.logging.Handler;



public class MainActivity extends AppCompatActivity implements MyFraginterface{

    android.support.v4.app.FragmentTransaction fragmentTransaction;
    android.support.v4.app.FragmentManager fragmentManager=getSupportFragmentManager();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        SplashFragment splashFragment=new SplashFragment();


        fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.rlayout, splashFragment, "Splashscreen");
        fragmentTransaction.commit();





    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void Replace() {
        Mainplay mainplay=new Mainplay();
        fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.rlayout,mainplay);
        fragmentTransaction.commit();
    }

    @Override
    public void changelayout(boolean flag) {
     if(flag)
     {

         fragmentTransaction=fragmentManager.beginTransaction();
         //fragmentTransaction.replace(R.id.rlayout,mainplay);
         fragmentTransaction.commit();
     }
    }
}

