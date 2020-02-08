package com.example.movies.ui.tv.view;

import com.example.movies.core.base.BasePageableFragment;

/**
 * Created by Tarik on 16.11.2019.
 */

public class TvFragment extends BasePageableFragment {

    @Override
    public void fetchRequest() {
        service.fetchTv(pagination, this);
    }
}


