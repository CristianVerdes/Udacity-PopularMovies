package com.example.cristianv.popularmovies.display.movie;

import android.os.AsyncTask;

import com.example.cristianv.popularmovies.utilities.NetworkUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.net.URL;

/**
 * Created by cristianv on 9/14/17.
 */

public class MovieInteractor implements MovieContract.Interactor {
    private static String moviePoster;
    private static int movieId;

    public MovieInteractor(String moviePoster, int movieId){
        this.moviePoster = moviePoster;
        this.movieId = movieId;
    }

    @Override
    public void getMovieData(MovieContract.OnInteractorFinishedListener listener) {
        new MyMovieData(listener).execute(
                NetworkUtils.createMovieURL(String.valueOf(movieId)));
    }

    static class MyMovieData extends AsyncTask<URL, Void, MovieModel>{
        private WeakReference<MovieContract.OnInteractorFinishedListener> listener;

        public MyMovieData (MovieContract.OnInteractorFinishedListener listener){
            this.listener = new WeakReference<>(listener);
        }

        @Override
        protected MovieModel doInBackground(URL... urls) {
            URL url = urls[0];

            String queryResult = null;
            MovieModel movieModel = new MovieModel();

            try {
                queryResult = NetworkUtils.getResponseFromHttpUrl(url);
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }

            try {
                JSONObject jsonObject = new JSONObject(queryResult);

                String title = jsonObject.getString("original_title");
                String overview = jsonObject.getString("overview");
                int voteAverage = jsonObject.getInt("vote_average");
                String releaseDate = jsonObject.getString("release_date");

                movieModel.setPoster(moviePoster);
                movieModel.setTitle(title);
                movieModel.setOverview(overview);
                movieModel.setVoteAverage(String.valueOf(voteAverage));
                movieModel.setReleaseDate(releaseDate);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return movieModel;
        }

        @Override
        protected void onPostExecute(MovieModel movieModel) {
            super.onPostExecute(movieModel);
            if(listener.get() != null){
                listener.get().onFinished(movieModel);
            }
        }
    }
}
