package com.example.movies.ui.detailtabs.view;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.movies.R;
import com.example.movies.core.base.BaseFragment;
import com.example.movies.core.helper.DateFormatHelper;
import com.example.movies.core.helper.LanguageHelper;
import com.example.movies.core.helper.NumberFormatHelper;
import com.example.movies.core.util.Constants;
import com.example.movies.data.entity.Crew;
import com.example.movies.data.entity.DetailInfo;
import com.example.movies.data.entity.Genres;
import com.example.movies.data.entity.MovieCrew;
import com.example.movies.data.entity.ProductionCompanies;
import com.example.movies.data.entity.ProductionCountries;
import com.example.movies.data.entity.Result;
import com.example.movies.data.entity.Trailer;
import com.example.movies.data.entity.Trailers;
import com.example.movies.data.service.RetroFitService;
import com.example.movies.ui.detailtabs.adapter.InfoAdapter;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

import butterknife.BindView;

public class InfoFragment extends BaseFragment {
    @BindView(R.id.text_genres)
    TextView textGenres;
    @BindView(R.id.text_rating)
    TextView textRating;
    @BindView(R.id.text_overview)
    TextView textOverview;
    @BindView(R.id.show_all)
    TextView showAll;
    @BindView(R.id.crew_one)
    TextView crewOne;
    @BindView(R.id.crew_two)
    TextView crewTwo;
    @BindView(R.id.crew_ones_two)
    TextView crewOnesTwo;
    @BindView(R.id.crew_twos_two)
    TextView crewTwosTwo;
    @BindView(R.id.crew_three)
    TextView crewThree;
    @BindView(R.id.crew_four)
    TextView crewFour;
    @BindView(R.id.crew_threes_three)
    TextView crewThreesThree;
    @BindView(R.id.crew_fours_four)
    TextView crewFoursFour;
    @BindView(R.id.info_recycler_view)
    RecyclerView infoRecyclerView;
    @BindView(R.id.original_name)
    TextView originalName;
    @BindView(R.id.situtation)
    TextView situtation;
    @BindView(R.id.runtime)
    TextView runtime;
    @BindView(R.id.release_date1)
    TextView releaseDate1;
    @BindView(R.id.original_language)
    TextView originalLanguage;
    @BindView(R.id.production_countries)
    TextView productionCountries;
    @BindView(R.id.certificate)
    TextView certificate;
    @BindView(R.id.budget)
    TextView budget;
    @BindView(R.id.revenues)
    TextView revenues;
    @BindView(R.id.companies)
    TextView companies;
    @BindView(R.id.homepage)
    TextView homepage;


    private LinearLayoutManager layoutManager;
    private InfoAdapter adapter;
    private ArrayList<Trailer> trailer;
    private ArrayList<MovieCrew> movieCrews;
    private ArrayList<DetailInfo> detailInfos;
    private ArrayList<Genres> genres;
    private ArrayList<ProductionCompanies> productionCompanies;
    private ArrayList<ProductionCountries> productionCountriess;
    private boolean isCharacterCountOfOverviewOverflowed = false;
    RetroFitService service = new RetroFitService();


    @Override
    public Integer getFragmentLayoutId() {
        return R.layout.tab_bilgiler_item;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (getArguments() != null) {
            int id = getArguments().getInt("id");
            fetchInfo(id);
            fetchCrews(id);
            fetchTrailers(id);
        }
    }

    private void fetchTrailers(int id) {
        RetroFitService.ResultCallBack serviceCallBack = new RetroFitService.ResultCallBack() {
            @Override
            public void getResult(Result result) {
                Result<Trailers> trailersResult = result;
                if (trailersResult.getData() != null) {
                    setVideos(trailersResult.getData().getResults());
                } else {
                    Toast.makeText(getActivity(), trailersResult.getErrorMessage(), Toast.LENGTH_LONG).show();
                }
            }
        };
        service.fetchTrailers(id, serviceCallBack);
    }

    private void fetchInfo(int id) { //419704
        RetroFitService.ResultCallBack serviceCallBack = new RetroFitService.ResultCallBack() {
            @Override
            public void getResult(Result result) {
                Result<DetailInfo> detailInfoResult = result;
                if (detailInfoResult.getData() != null) {
                    setData(detailInfoResult.getData());
                } else {
                    Toast.makeText(getActivity(), detailInfoResult.getErrorMessage(), Toast.LENGTH_LONG).show();
                }
            }
        };
        service.fetchInfo(id, serviceCallBack);
    }

    private void fetchCrews(int id) {
        RetroFitService.ResultCallBack serviceCallBack = new RetroFitService.ResultCallBack() {
            @Override
            public void getResult(Result result) {
                Result<MovieCrew> movieCrewResult = result;
                if (movieCrewResult.getData() != null) {
                    setDataCrews(movieCrewResult.getData());
                } else {
                    Toast.makeText(getActivity(), movieCrewResult.getErrorMessage(), Toast.LENGTH_LONG).show();
                }
            }
        };
        service.fetchCrews(id, serviceCallBack);

    }

    private void setVideos(ArrayList<Trailer> list) {
        trailer = new ArrayList<>();
        trailer.addAll(list);
        setRecyclerView();
    }

    private void setRecyclerView() {
        layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        infoRecyclerView.setLayoutManager(layoutManager);
        adapter = new InfoAdapter(getContext(), trailer);
        infoRecyclerView.setAdapter(adapter);
    }

    private void setDataCrews(MovieCrew list) {
        movieCrews = new ArrayList<>();
        movieCrews.add(list);
        setTexts(list.getCrew());
    }

    private void setTexts(final ArrayList<Crew> crews) {
        String[] crew = new String[crews.size()];
        String[] crew2 = new String[crews.size()];
        String liste = "";
        String liste2 = "";
        for (Crew s : crews) {
            liste += s.getName() + ",";
            liste2 += s.getDepartment() + ",";
        }
        for (int i = 0; i < crews.size(); i++) {
            crew = liste.split(",");
            crew2 = liste2.split(",");
        }
        if (crews.size() > 3) {
            crewOne.setText(crew[0]);
            crewOnesTwo.setText(crew2[0]);
            crewTwo.setText(crew[1]);
            crewTwosTwo.setText(crew2[1]);
            crewThree.setText(crew[2]);
            crewThreesThree.setText(crew2[2]);
            crewFour.setText(crew[3]);
            crewFoursFour.setText(crew2[3]);
        } else {
            crewOne.setText("empty");
            crewOnesTwo.setText("empty");
            crewTwo.setText("empty");
            crewTwosTwo.setText("empty");
            crewThree.setText("empty");
            crewThreesThree.setText("empty");
            crewFour.setText("empty");
            crewFoursFour.setText("empty");
        }

        showAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = getFragmentManager();
                FragmentTransaction ft = manager.beginTransaction();
                Fragment prev = manager.findFragmentByTag("crews");
                if (prev != null) {
                    ft.remove(prev);
                }
                DialogFragment newFragment = CrewsDialogFragment.newInstance(crews);
                newFragment.show(ft, "crews");
            }
        });
    }

    private void setData(DetailInfo list) {
        detailInfos = new ArrayList<>();
        detailInfos.add(list);
        setItems(list);
        setTag(list, list.getProduction_companies(), list.getProduction_countries());
        list.getGenres();
        setGenres(list.getGenres());
    }

    private void setTag(final DetailInfo list, ArrayList<
            ProductionCompanies> productionCompaniesArrayList,
                        ArrayList<ProductionCountries> productionCountriesArrayList) {
        productionCompanies = new ArrayList<>();
        productionCompanies.addAll(productionCompaniesArrayList);
        productionCountriess = new ArrayList<>();
        productionCountriess.addAll(productionCountriesArrayList);
        originalName.setText(list.getOriginal_title());
        releaseDate1.setText(DateFormatHelper.formatDate(list.getRelease_date()));
        situtation.setText(list.getStatus());

        String companiess = " ";
        for (ProductionCompanies s : productionCompanies) {
            String.valueOf(s);
            companiess += s.getName() + ",";
        }
        companies.setText(companiess);
        budget.setText(NumberFormatHelper.formatNumber((double) list.getBudget()));
        runtime.setText(String.valueOf(list.getRuntime() + " dk"));

        originalLanguage.setText(LanguageHelper.formatLanguage(list.getOriginal_language()));
        revenues.setText(NumberFormatHelper.formatNumber((double) list.getRevenue()));
        String countries = " ";
        for (ProductionCountries s : productionCountriess) {
            countries += s.getName() + ",";
        }
        productionCountries.setText(countries);

        homepage.setText(list.getHomepage());
        homepage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse(list.getHomepage()));
                v.getContext().startActivity(intent);
            }
        });

    }

    private void setGenres(ArrayList<Genres> list) {
        genres = new ArrayList<>();
        genres.addAll(list);
        String liste = " ";
        for (Genres s : genres) {
            String.valueOf(s);
            liste += s.getName() + " ";
        }
        textGenres.setText(liste);
    }

    private void setItems(final DetailInfo list) {
        textRating.setText(String.valueOf(list.getVote_average()));
        final String overview = list.getOverview();
        isCharacterCountOfOverviewOverflowed = overview.length() > 140;
        setOverviewText(overview);
        textOverview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isCharacterCountOfOverviewOverflowed = !isCharacterCountOfOverviewOverflowed;
                setOverviewText(overview);
            }
        });
    }

    private void setOverviewText(String overview) {
        if (isCharacterCountOfOverviewOverflowed) {
            textOverview.setText(overview.substring(0, 137) + "...");
        } else {
            textOverview.setText(overview);
        }
    }

    public static InfoFragment newInstance(int id) {
        InfoFragment f = new InfoFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("id", id);
        f.setArguments(bundle);
        return f;
    }
}
