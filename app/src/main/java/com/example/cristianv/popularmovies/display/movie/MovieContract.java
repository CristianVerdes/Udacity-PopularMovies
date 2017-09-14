package com.example.cristianv.popularmovies.display.movie;

/**
 * Created by cristianv on 9/14/17.
 */

public interface MovieContract {

     interface View{
        void showProgress();

        void hideProgress();

        void displayData(MovieModel movieModel);

        void showNoConnection();

        void hideNoConnection();
    }

    interface Presenter{
         void onResume();

         void onDestroy();
    }

    interface OnInteractorFinishedListener{
         void onFinished(MovieModel movieModel);
    }

    interface Interactor{
         void getMovieData(OnInteractorFinishedListener listener);
    }


}
