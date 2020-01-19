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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.movies.R;
import com.example.movies.data.entity.Crew;
import com.example.movies.data.entity.DetailInfo;
import com.example.movies.data.entity.Genres;
import com.example.movies.data.entity.MovieCrew;
import com.example.movies.data.entity.ProductionCompanies;
import com.example.movies.data.entity.ProductionCountries;
import com.example.movies.data.entity.Trailer;
import com.example.movies.data.entity.Trailers;
import com.example.movies.data.service.API;
import com.example.movies.data.service.RetroFitService;
import com.example.movies.ui.detailtabs.adapter.InfoAdapter;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InfoFragment extends Fragment {
    private String DATE_FORMAT = "yyyy-MM-dd";
    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private InfoAdapter adapter;
    private ArrayList<Trailer> trailer;
    private ArrayList<Crew> crews;
    private ArrayList<MovieCrew> movieCrews;
    private ArrayList<DetailInfo> detailInfos;
    private ArrayList<Genres> genres;
    private ArrayList<ProductionCompanies> productionCompanies;
    private ArrayList<ProductionCountries> productionCountries;
    private TextView textOverview, textRating, textGenres, textCrewOne, textCrewOnes, textCrewTwo,
            textCrewTwos, textCrewThree, textCrewThrees, textCrewFour, textCrewFours, textShowAll,
            textOriginalName, textSitutation,
            textReleaseDate, textProductionCountries, textBudget, textRuntime,
            textOriginalLanguage, textCertificate, textRevenues, textCompanies, textHomepage;
    private boolean isCharacterCountOfOverviewOverflowed = false;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.tab_bilgiler_item, container, false);

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
        recyclerView = view.findViewById(R.id.info_recycler_view);
        textHomepage = view.findViewById(R.id.homepage);
        textOriginalName = view.findViewById(R.id.original_name);
        textSitutation = view.findViewById(R.id.situtation);
        textReleaseDate = view.findViewById(R.id.release_date1);
        textProductionCountries = view.findViewById(R.id.production_countries);
        textBudget = view.findViewById(R.id.budget);
        textRuntime = view.findViewById(R.id.runtime);
        textOriginalLanguage = view.findViewById(R.id.original_language);
        textCertificate = view.findViewById(R.id.certificate);
        textRevenues = view.findViewById(R.id.revenues);
        textCompanies = view.findViewById(R.id.companies);

        textOverview = view.findViewById(R.id.text_overview);
        textRating = view.findViewById(R.id.text_rating);
        textGenres = view.findViewById(R.id.text_genres);

        textCrewOne = view.findViewById(R.id.crew_one);
        textCrewOnes = view.findViewById(R.id.crew_ones_two);
        textCrewTwo = view.findViewById(R.id.crew_two);
        textCrewTwos = view.findViewById(R.id.crew_twos_two);
        textCrewThree = view.findViewById(R.id.crew_three);
        textCrewThrees = view.findViewById(R.id.crew_threes_three);
        textCrewFour = view.findViewById(R.id.crew_four);
        textCrewFours = view.findViewById(R.id.crew_fours_four);

        textShowAll = view.findViewById(R.id.show_all);
    }

    private void fetchTrailers(int id) {
        API retrofit = RetroFitService.getRetrofit().create(API.class);
        Call<Trailers> call = retrofit.getTrailers(id, "788a71cfbb2953df3cc3b1e7531ef259",
                "en-US");
        call.enqueue(new Callback<Trailers>() {
            @Override
            public void onResponse(Call<Trailers> call, Response<Trailers> response) {
                response.body().getResults();
                setVideos(response.body().getResults());
            }

            @Override
            public void onFailure(Call<Trailers> call, Throwable t) {

            }
        });
    }

    private void fetchInfo(int id) { //419704
        API retrofit = RetroFitService.getRetrofit().create(API.class);
        Call<DetailInfo> call = retrofit.getMovieDetail(id, "788a71cfbb2953df3cc3b1e7531ef259",
                "en-US");
        call.enqueue(new Callback<DetailInfo>() {
            @Override
            public void onResponse(Call<DetailInfo> call, Response<DetailInfo> response) {
                response.body();
                setData(response.body());
            }

            @Override
            public void onFailure(Call<DetailInfo> call, Throwable t) {
                System.out.println("hata" + t);

            }
        });
    }

    private void fetchCrews(int id) {
        API retrofit = RetroFitService.getRetrofit().create(API.class);
        Call<MovieCrew> call = retrofit.getMovieCrew(id, "788a71cfbb2953df3cc3b1e7531ef259");
        call.enqueue(new Callback<MovieCrew>() {
            @Override
            public void onResponse(Call<MovieCrew> call, Response<MovieCrew> response) {
                setDataCrews(response.body());
            }

            @Override
            public void onFailure(Call<MovieCrew> call, Throwable t) {
                System.out.println("hata ne iimiş" + t);

            }
        });

    }

    private void setVideos(ArrayList<Trailer> list) {
        trailer = new ArrayList<>();
        trailer.addAll(list);
        setRecyclerView();


    }

    private void setRecyclerView() {
        layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new InfoAdapter(getContext(), trailer);
        recyclerView.setAdapter(adapter);
    }

    private void setDataCrews(MovieCrew list) {
        movieCrews = new ArrayList<>();
        movieCrews.add(list);
        setTexts(list.getCrew());
    }

    private void setTexts(final ArrayList<Crew> list) {
        crews = new ArrayList<>();
        crews.addAll(list);
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
        textCrewOne.setText(crew[0]);
        textCrewOnes.setText(crew2[0]);
        textCrewTwo.setText(crew[1]);
        textCrewTwos.setText(crew2[1]);
        textCrewThree.setText(crew[2]);
        textCrewThrees.setText(crew2[2]);
        textCrewFour.setText(crew[3]);
        textCrewFours.setText(crew2[3]);

        textShowAll.setOnClickListener(new View.OnClickListener() {
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

    private void setTag(final DetailInfo list, ArrayList<ProductionCompanies> productionCompaniesArrayList,
                        ArrayList<ProductionCountries> productionCountriesArrayList) {
        productionCompanies = new ArrayList<>();
        productionCompanies.addAll(productionCompaniesArrayList);
        productionCountries = new ArrayList<>();
        productionCountries.addAll(productionCountriesArrayList);
        textOriginalName.setText(list.getOriginal_title());
        String date = list.getRelease_date();
        SimpleDateFormat spf = new SimpleDateFormat(DATE_FORMAT);
        try {
            Date newDate = spf.parse(date);
            spf = new SimpleDateFormat("dd MMM yyyy");
            date = spf.format(newDate);
            textReleaseDate.setText(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }


        textSitutation.setText(list.getStatus());
        String companies = " ";
        for (ProductionCompanies s : productionCompanies) {
            String.valueOf(s);
            companies += s.getName() + ",";
        }
        textCompanies.setText(companies);
        NumberFormat formatter = new DecimalFormat("#,###");
        double myNumber = list.getBudget();
        String formattedNumber = formatter.format(myNumber);
        textBudget.setText("$" + String.valueOf(formattedNumber));
        textRuntime.setText(String.valueOf(list.getRuntime() + " dk"));

        String lng = list.getOriginal_language();
        Locale loc = new Locale(lng);
        String name = loc.getDisplayLanguage(loc);
        textOriginalLanguage.setText(name);
        // textCertificate.setText(list.get);
        double mynumber2 = list.getRevenue();
        String number = formatter.format(mynumber2);
        textRevenues.setText(String.valueOf("$" + number));
        String countries = " ";
        for (ProductionCountries s : productionCountries) {
            countries += s.getName() + ",";
        }
        textProductionCountries.setText(countries);

        textHomepage.setText(list.getHomepage());
        textHomepage.setOnClickListener(new View.OnClickListener() {
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
