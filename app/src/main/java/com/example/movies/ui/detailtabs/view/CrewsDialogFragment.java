package com.example.movies.ui.detailtabs.view;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.movies.R;
import com.example.movies.data.entity.Crew;
import com.example.movies.ui.detailtabs.adapter.CrewsDialogAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class CrewsDialogFragment extends DialogFragment implements com.example.movies.ui.detailtabs.view.Dialog.DialogListener {

    @BindView(R.id.number_of_actors)
    TextView numberOfActors;
    @BindView(R.id.sort)
    TextView sort;
    @BindView(R.id.recycler_view_person)
    RecyclerView recyclerViewPerson;
    Unbinder unbinder;
    private ArrayList<Crew> crews = new ArrayList<>();
    private CrewsDialogAdapter adapter;
    private LinearLayoutManager layout;

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            int width = ViewGroup.LayoutParams.MATCH_PARENT;
            int height = ViewGroup.LayoutParams.MATCH_PARENT;
            dialog.getWindow().setLayout(width, height);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.actors, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, android.R.style.Theme_Black_NoTitleBar_Fullscreen);
    }

    @Override
    public void onFinishDialog(int inputText) {
        if (inputText == 0) {
            adapter.notifyDataSetChanged();
        } else if (inputText == 1) {
            Collections.sort(crews, new Comparator<Crew>() {
                @Override
                public int compare(Crew o1, Crew o2) {
                    return o1.getName().compareTo(o2.getName());
                }
            });
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        numberOfActors.setText(crews.size() + " kişi");
        sort = view.findViewById(R.id.sort);
        sort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();
            }
        });
        setRecyclerView();
    }

    private void openDialog() {
        com.example.movies.ui.detailtabs.view.Dialog dialog = new com.example.movies.ui.detailtabs.view.Dialog();
        dialog.setTargetFragment(CrewsDialogFragment.this, 1);
        dialog.show(getFragmentManager(), "dialog");
    }

    private void setRecyclerView() {
        layout = new LinearLayoutManager(getContext());
        recyclerViewPerson.setLayoutManager(layout);
        adapter = new CrewsDialogAdapter(getContext(), crews);
        recyclerViewPerson.setAdapter(adapter);
    }

    public static CrewsDialogFragment newInstance(ArrayList<Crew> crew) {
        CrewsDialogFragment f = new CrewsDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("crews", crew);
        f.setArguments(bundle);
        f.setCrew(crew);
        return f;
    }

    public void setCrew(ArrayList<Crew> crew) {
        this.crews = crew;

    }
}
