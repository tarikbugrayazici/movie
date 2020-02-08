package com.example.movies.core.navigation;

import android.content.Context;
import android.content.Intent;

import com.example.movies.ui.actorsDetail.ActorsDetailActivity;
import com.example.movies.ui.detail.DetailActivity;

public class Navigation {

    public static void startDetailActivity(Context context, int id){
        Intent i = new Intent(context, DetailActivity.class);
        i.putExtra("id", id);
        context.startActivity(i);
    }
    public static void startActorsDetailActivity(Context context, int id){
        Intent i = new Intent(context, ActorsDetailActivity.class);
        i.putExtra("actor", id);
        context.startActivity(i);
    }
}
