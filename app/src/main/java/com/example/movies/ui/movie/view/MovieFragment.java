package com.example.movies.ui.movie.view;

import com.example.movies.core.base.BasePageableFragment;

/**
 * Created by Tarik on 16.11.2019.
 */

public class MovieFragment extends BasePageableFragment {

    @Override
    public void fetchRequest() {
        service.fetchMovies(pagination, this);
    }
}
