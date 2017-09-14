package com.example.cristianv.popularmovies.display.movie;

import com.example.cristianv.popularmovies.display.movies.MoviesModel;

/**
 * Created by cristianv on 9/14/17.
 */

public class MoviePresenter implements MovieContract.Presenter,
                                       MovieContract.OnInteractorFinishedListener{
    private MovieContract.View movieView;
    private MovieContract.Interactor interactor;

    public MoviePresenter(MovieContract.View movieView,
                          MovieContract.Interactor interactor){
        this.movieView = movieView;
        this.interactor = interactor;
    }

    @Override
    public void onResume() {
        if(movieView != null){
            movieView.showProgress();
        }
        interactor.getMovieData(this);
    }

    @Override
    public void onDestroy() {
        movieView = null;
    }

    // On Interactor finished listener
    @Override
    public void onFinished(MovieModel movieModel) {
        if (movieView != null && movieModel != null){
            movieView.hideProgress();
            movieView.hideNoConnection();
            movieView.displayData(movieModel);
        } else {
            movieView.hideProgress();
            movieView.showNoConnection();
        }
    }
}
