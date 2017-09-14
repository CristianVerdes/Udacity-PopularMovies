package com.example.cristianv.popularmovies.display.movie;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.cristianv.popularmovies.R;
import com.example.cristianv.popularmovies.display.movies.MoviesModel;
import com.squareup.picasso.Picasso;

/**
 * Created by cristianv on 9/5/17.
 */

public class MovieActivity extends AppCompatActivity implements MovieContract.View{
    private ImageView poster;
    private TextView title;
    private TextView releaseDate;
    private TextView voteAverage;
    private TextView overview;
    private View movieDetails;
    private ProgressBar progressBar;
    private View noConnection;
    private MoviePresenter presenter;

    public static void start(Context context, MoviesModel moviesModel) {
        Intent starter = new Intent(context, MovieActivity.class);
        starter.putExtra("movieId", moviesModel.getMovieId());
        starter.putExtra("moviePoster", moviesModel.getPhotoPath());
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);

        getSupportActionBar().setTitle("-");

        poster = (ImageView) findViewById(R.id.iv_movie_poster);
        title = (TextView) findViewById(R.id.tv_title);
        releaseDate = (TextView) findViewById(R.id.tv_release_date);
        voteAverage = (TextView) findViewById(R.id.tv_vote_average);
        overview = (TextView) findViewById(R.id.tv_overview);

        movieDetails = (View) findViewById(R.id.movie_details);
        progressBar = (ProgressBar) findViewById(R.id.pb_loading_data);
        noConnection = (View) findViewById(R.id.no_connection);

        String moviePoster = getIntent().getStringExtra("moviePoster");
        int movieId = getIntent().getIntExtra("movieId", 0);

        presenter = new MoviePresenter(this, new MovieInteractor(moviePoster, movieId));
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }

    // View

    @Override
    public void showProgress() {
        movieDetails.setVisibility(View.GONE);
        overview.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        movieDetails.setVisibility(View.VISIBLE);
        overview.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void displayData(MovieModel movieModel) {
        hideProgress();
        getSupportActionBar().setTitle(movieModel.getTitle());

        Picasso.with(this).load(movieModel.getPoster()).into(poster);
        title.setText(movieModel.getTitle());
        releaseDate.setText(movieModel.getReleaseDate());
        voteAverage.setText(movieModel.getVoteAverage());
        overview.setText(movieModel.getOverview());
    }

    @Override
    public void showNoConnection() {
        movieDetails.setVisibility(View.GONE);
        overview.setVisibility(View.GONE);
        progressBar.setVisibility(View.GONE);
        noConnection.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideNoConnection() {
        movieDetails.setVisibility(View.VISIBLE);
        overview.setVisibility(View.VISIBLE);
        noConnection.setVisibility(View.GONE);
    }
}
