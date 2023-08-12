package com.example.testeruas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.viewmodel.CreationExtras;

import android.app.Activity;
import android.content.Intent;
import android.media.tv.TvContract;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import javax.security.auth.login.LoginException;

public class home_activity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        BottomNavigationView navigationView = findViewById(R.id.bottom_nav);

        final HomeFragment homeFragment = new HomeFragment();
        final CatatanFragment catatanFragment = new CatatanFragment();
        final ProfileFragment profileFragment = new ProfileFragment();

        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int id = menuItem.getItemId();
                if (id == R.id.home) {
                    setFragment(homeFragment);
                    return true;
                } else if (id == R.id.contact) {
                    setFragment(catatanFragment);
                    return true;
                } else if (id == R.id.profile) {
                    setFragment(profileFragment);
                    return true;
                }
                return false;
            }
        });

        navigationView.setSelectedItemId(R.id.home);
    }

    private void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame, fragment);
        fragmentTransaction.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.header_nav_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
         if (item.getItemId() == R.id.logout) {
            startActivity(new Intent(this, LogOutActivity.class));
        } else if (item.getItemId() == R.id.add) {
             startActivity(new Intent(this, MainActivity2.class));
         }
        return true;
    }

}