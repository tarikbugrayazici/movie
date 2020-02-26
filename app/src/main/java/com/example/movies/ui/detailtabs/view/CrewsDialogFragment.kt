package com.example.movies.ui.detailtabs.view

import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.movies.R
import com.example.movies.data.entity.Crew
import com.example.movies.ui.detailtabs.adapter.CrewsDialogAdapter
import kotlinx.android.synthetic.main.actors.*
import java.util.*
import kotlin.collections.ArrayList

class CrewsDialogFragment : DialogFragment(), Dialog.DialogListener {
    private var crews = ArrayList<Crew>()
    private var adapter: CrewsDialogAdapter? = null
    private var layout: LinearLayoutManager? = null

    override fun onStart() {
        super.onStart()
        val dialog = dialog
        if (dialog != null) {
            val width: Int = ViewGroup.LayoutParams.MATCH_PARENT
            val height: Int = ViewGroup.LayoutParams.MATCH_PARENT
            dialog.window.setLayout(width, height);
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.actors, container, false);
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, android.R.style.Theme_Black_NoTitleBar_Fullscreen)
    }

    override fun onFinishDialog(inputText: Int) {
        if (inputText == 0) {
            adapter!!.notifyDataSetChanged();
        } else if (inputText == 1) {
            crews.sortWith(Comparator { o1, o2 -> o1.name!!.compareTo(o2.name!!) })
            adapter!!.notifyDataSetChanged();
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        number_of_actors.text = "${crews.size} kişi"
        sort.setOnClickListener() { openDialog() }
        setRecyclerView();
    }

    private fun openDialog() {
        val dialog = Dialog()
        dialog.setTargetFragment(this@CrewsDialogFragment, 1)
        dialog.show(fragmentManager, "dialog")
    }

    private fun setRecyclerView() {
        layout = LinearLayoutManager(context)
        recycler_view_person.setLayoutManager(layout)
        adapter = CrewsDialogAdapter(context!!, crews)
        recycler_view_person.setAdapter(adapter)
    }

    companion object {
        fun newInstance(crew: ArrayList<Crew>): CrewsDialogFragment {
            val f = CrewsDialogFragment()
            val bundle = Bundle()
            bundle.putParcelableArrayList("crews", crew)
            f.arguments = bundle
            f.setCrew(crew)
            return f
        }
    }

    fun setCrew(crew: ArrayList<Crew>) {
        this.crews = crew
    }
}

