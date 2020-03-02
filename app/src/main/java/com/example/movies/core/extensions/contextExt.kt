package com.example.movies.core.extensions

import android.content.Context
import android.support.annotation.LayoutRes
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.movies.data.entity.MovieCrew
import com.example.movies.ui.actorsdetailtabs.adapter.InfoAdapter


fun Context.showToast(text: CharSequence, duration: Int = Toast.LENGTH_LONG) =
        this?.let { Toast.makeText(this, text, duration).show() }

/*fun InfoAdapter.InfoViewHolder?.inflate(context: Context, layoutId: Int, attachToRoot: Boolean, viewGroup: ViewGroup): RecyclerView.ViewHolder? =
        LayoutInflater.from(context).inflate(layoutId, viewGroup, attachToRoot)*/

/*fun <T : RecyclerView.ViewHolder> ViewGroup.bind(layoutId: Int): T {
    return DataBindingUtil.inflate(getLayoutInflater(), layoutId, this, true)
}
fun <T : ViewDataBinding> View.bind() = DataBindingUtil.bind<T>(this) as T

fun <T> Collection<T>.ab(result: Result<T>) {
    val anyResult: Result<T> = result as Result<T>

}


val movieresult: Result<MovieCrew> = result as Result<MovieCrew>*/