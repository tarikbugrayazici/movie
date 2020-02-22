package com.example.movies.core.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

import butterknife.ButterKnife

abstract class BaseActivity : AppCompatActivity() {

    abstract val layoutId: Int?

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutId!!)
    }
}
