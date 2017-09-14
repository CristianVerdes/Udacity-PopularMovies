package com.example.cristianv.popularmovies.utilities;

import android.net.Uri;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by cristianv on 9/13/17.
 */

public class NetworkUtils {
    final static String TMDB_BASE_URL = "http://api.themoviedb.org/3/movie";
    final static String API_KEY = "";

    final static String PARAM_POPULAR = "popular";
    final static String PARAM_TOP_RATED = "top_rated";
    final static String PARAM_API_KEY = "api_key";

    public static URL createMovieURL(String id){
        Uri buildUri = Uri.parse(TMDB_BASE_URL).buildUpon()
                .appendPath(id)
                .appendQueryParameter(PARAM_API_KEY, API_KEY)
                .build();
        URL url = null;
        try {
            url = new URL(buildUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return url;
    }

    public static URL createPopularURL(){
        Uri buildUri = Uri.parse(TMDB_BASE_URL).buildUpon()
                .appendPath(PARAM_POPULAR)
                .appendQueryParameter(PARAM_API_KEY, API_KEY)
                .build();

        URL url = null;
        try {
            url = new URL(buildUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return url;
    }

    public static URL createTopRatedURL(){
        Uri buildUri = Uri.parse(TMDB_BASE_URL).buildUpon()
                .appendPath(PARAM_TOP_RATED)
                .appendQueryParameter(PARAM_API_KEY, API_KEY)
                .build();

        URL url = null;
        try {
            url = new URL(buildUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return url;
    }

    public static String getResponseFromHttpUrl(URL url) throws IOException{
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setUseCaches(false);
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }

    public static String createPosterLink(String posterPath){
        String BASE_POSTER_LINK = "http://image.tmdb.org/t/p/w185";
        return BASE_POSTER_LINK + posterPath;
    }
}
