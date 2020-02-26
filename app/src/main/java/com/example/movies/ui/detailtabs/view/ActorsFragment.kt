package com.example.movies.ui.detailtabs.view

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.Toast
import com.example.movies.R
import com.example.movies.core.base.BaseFragment
import com.example.movies.data.entity.Cast
import com.example.movies.data.entity.MovieCrew
import com.example.movies.data.entity.Result
import com.example.movies.data.enums.Sorting
import com.example.movies.data.service.RetroFitService
import com.example.movies.data.service.RetroFitService.ResultCallBack
import com.example.movies.ui.detailtabs.adapter.ActorsAdapter
import com.example.movies.ui.detailtabs.view.Dialog.DialogListener
import kotlinx.android.synthetic.main.actors.*
import java.util.*

class ActorsFragment : BaseFragment(), DialogListener {

    private var adapter: ActorsAdapter? = null
    private val casts = ArrayList<Cast>()
    private var layoutManager: LinearLayoutManager? = null
    private var actorId = 0
    var service = RetroFitService()

    override fun getFragmentLayoutId() = R.layout.actors

    override fun onFinishDialog(inputText: Int) {
        val list = ArrayList(sortedList(inputText))
        adapter = ActorsAdapter(context!!, list)
        recycler_view_person.adapter = adapter
    }

    private fun sortedList(type: Int): ArrayList<Cast> {
        val castSorted = ArrayList(casts)
        if (type == Sorting.BY_NAME.value) {
            castSorted.sortWith(Comparator { o1, o2 -> o1.name!!.compareTo(o2.name!!) })
        } else if (type == Sorting.BY_CHARACTER.value) {
            castSorted.sortWith(Comparator { o1, o2 -> o1.character!!.compareTo(o2.character!!) })
        }
        return castSorted
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sort.setOnClickListener { openDialog() }
        if (arguments != null) {
            actorId = arguments!!.getInt("id")
            setRecyclerView()
            fetchActorMovies(actorId)
        }
    }

    private fun openDialog() {
        val dialog = Dialog()
        dialog.setTargetFragment(this@ActorsFragment, 1)
        dialog.show(fragmentManager, "dialog")
    }

    @Suppress("UNCHECKED_CAST")
    private fun fetchActorMovies(id: Int) {
        val serviceCallBack = object : ResultCallBack {
            override fun getResult(result: Result<*>) {
                val movieCrewResult: Result<MovieCrew> = result as Result<MovieCrew>
                if (movieCrewResult.data != null) {
                    setData(movieCrewResult.data!!.cast)
                } else {
                    Toast.makeText(activity, movieCrewResult.errorMessage, Toast.LENGTH_LONG).show()
                }
            }
        }
        service.fetchActorMovies(id, serviceCallBack)
    }

    private fun setRecyclerView() {
        layoutManager = LinearLayoutManager(context)
        recycler_view_person.layoutManager = layoutManager
        val sharedPreferences = context!!.getSharedPreferences("sorting", Context.MODE_PRIVATE)
        val sortType = sharedPreferences.getInt("key", Sorting.BY_RECOMMENDED.value)
        val list = ArrayList(sortedList(sortType))
        adapter = ActorsAdapter(context!!, list)
        recycler_view_person.adapter = adapter
    }

    @SuppressLint("SetTextI18n")
    private fun setData(list: ArrayList<Cast>) {
        casts.addAll(list)
        number_of_actors.text = "${casts.size} kişi"
        setRecyclerView()
    }

    companion object {
        @JvmStatic
        fun newInstance(id: Int): ActorsFragment {
            val fragment = ActorsFragment()
            val bundle = Bundle()
            bundle.putInt("id", id)
            fragment.arguments = bundle
            return fragment
        }
    }
}