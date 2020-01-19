package com.example.movies.ui.dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;
import android.support.v7.app.AppCompatActivity;
import android.support.design.widget.BottomNavigationView;

import com.example.movies.R;
import com.example.movies.core.BottomNavigationViewHelper;
import com.example.movies.ui.movie.view.MovieFragment;
import com.example.movies.ui.search.view.SearchFragment;
import com.example.movies.ui.trending.view.TrendingFragment;
import com.example.movies.ui.tv.view.TvFragment;


public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    final Fragment movie = new MovieFragment();
    final Fragment tv = new TvFragment();
    final Fragment search = new SearchFragment();
    final Fragment trending = new TrendingFragment();
    final FragmentManager fragmentManager = getSupportFragmentManager();
    Fragment active = movie;
    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_nav);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        //BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
        fragmentManager.beginTransaction().add(R.id.frame_container, trending, "4").hide(trending).commit();
        fragmentManager.beginTransaction().add(R.id.frame_container, search, "3").hide(search).commit();
        fragmentManager.beginTransaction().add(R.id.frame_container, tv, "2").hide(tv).commit();
        fragmentManager.beginTransaction().add(R.id.frame_container, movie, "1").commit();
    }

    @Override
    public void onBackPressed() {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);

       /* if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }*/
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.movie: {
                fragmentManager.beginTransaction().hide(active).show(movie).commit();
                active = movie;
                return true;
            }
            case R.id.tv: {
                fragmentManager.beginTransaction().hide(active).show(tv).commit();
                active = tv;
                return true;
            }
            case R.id.search: {
                fragmentManager.beginTransaction().hide(active).show(search).commit();
                active = search;
                return true;
            }
            case R.id.trending: {
                fragmentManager.beginTransaction().hide(active).show(trending).commit();
                active = trending;
                return true;
            }
        }
        return false;
    }
}
