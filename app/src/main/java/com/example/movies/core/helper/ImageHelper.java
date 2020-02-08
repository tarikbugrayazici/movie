package com.example.movies.core.helper;

import com.example.movies.core.util.Constants;

public class ImageHelper {

    public static String getImageUrl(String path){
        return Constants.IMAGE_BASE_PATH + path;
    }
}
