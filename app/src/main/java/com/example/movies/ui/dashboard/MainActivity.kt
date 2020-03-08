package com.example.movies.ui.dashboard

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.view.MenuItem

import com.example.movies.R
import com.example.movies.core.base.BaseActivity
import com.example.movies.ui.movie.view.MovieFragment
import com.example.movies.ui.search.view.SearchFragment
import com.example.movies.ui.trending.view.TrendingFragment
import com.example.movies.ui.tv.view.TvFragment
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : BaseActivity(), BottomNavigationView.OnNavigationItemSelectedListener {
    internal val movie: Fragment = MovieFragment()
    internal val tv: Fragment = TvFragment()
    internal val search: Fragment = SearchFragment()
    internal val trending: Fragment = TrendingFragment()
    internal val fragmentManager = supportFragmentManager
    internal var active = movie
    override val layoutId: Int?
        get() = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bottomNav!!.setOnNavigationItemSelectedListener(this)
        //BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
        fragmentManager.beginTransaction().add(R.id.frame_container, trending, "4").hide(trending).commit()
        fragmentManager.beginTransaction().add(R.id.frame_container, search, "3").hide(search).commit()
        fragmentManager.beginTransaction().add(R.id.frame_container, tv, "2").hide(tv).commit()
        fragmentManager.beginTransaction().add(R.id.frame_container, movie, "1").commit()
    }

    override fun onBackPressed() {
        val intent = Intent(Intent.ACTION_MAIN)
        intent.addCategory(Intent.CATEGORY_HOME)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)

        /* if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }*/
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.movie -> {
                fragmentManager.beginTransaction().hide(active).show(movie).commit()
                active = movie
                return true
            }
            R.id.tv -> {
                fragmentManager.beginTransaction().hide(active).show(tv).commit()
                active = tv
                return true
            }
            R.id.search -> {
                fragmentManager.beginTransaction().hide(active).show(search).commit()
                active = search
                return true
            }
            R.id.trending -> {
                fragmentManager.beginTransaction().hide(active).show(trending).commit()
                active = trending
                return true
            }
        }
        return false
    }
}
