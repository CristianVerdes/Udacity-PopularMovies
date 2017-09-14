package com.example.cristianv.popularmovies.display.movies;

import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cristianv.popularmovies.R;
import com.example.cristianv.popularmovies.display.movie.MovieActivity;

import java.util.List;

public class MoviesActivity extends AppCompatActivity implements ListItemClickListener, MoviesContract.View {
    private RecyclerView moviesList;
    private ProgressBar progressBar;
    private Toast toast;
    private MoviesContract.Presenter presenter;
    private MoviesAdapter moviesAdapter;
    private View noConection;


    private final int SPAN_COUNT = 3;
    private static final String KEY_FILTER = "key_filter";

    private MoviesPresenter.Filter filter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Views
        moviesList = (RecyclerView) findViewById(R.id.rv_movies);
        progressBar = (ProgressBar) findViewById(R.id.pb_loading_data);
        noConection = (View) findViewById(R.id.no_connection);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, SPAN_COUNT);
        moviesList.setLayoutManager(gridLayoutManager);
        moviesList.setHasFixedSize(true);
        moviesAdapter = new MoviesAdapter(this);
        moviesList.setAdapter(moviesAdapter);

        toast = new Toast(this);

        // Presenter
        presenter = new MoviesPresenter(this, new MoviesInteractor());

        if(savedInstanceState != null){
            filter = (MoviesPresenter.Filter) savedInstanceState.getSerializable(KEY_FILTER);
        } else {
            filter = MoviesPresenter.Filter.POPULAR;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.onResume(filter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(KEY_FILTER, filter);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if(savedInstanceState != null){
            filter = (MoviesPresenter.Filter) savedInstanceState.getSerializable(KEY_FILTER);
        } else {
            filter = MoviesPresenter.Filter.POPULAR;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_sort, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.sort_popular:
                getSupportActionBar().setTitle(R.string.popular_movies);
                presenter.onPopularSelected();
                filter = MoviesPresenter.Filter.POPULAR;
                return true;
            case R.id.sort_top_rated:
                getSupportActionBar().setTitle(R.string.top_rated_movies);
                presenter.onTopRatedSelected();
                filter = MoviesPresenter.Filter.TOP_RATED;
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    // ListItemClickListener function
    @Override
    public void onListItemClick(MoviesModel moviesModel) {
        MovieActivity.start(this, moviesModel);
    }

    // Movie View
    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
        moviesList.setVisibility(View.INVISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.INVISIBLE);
        moviesList.setVisibility(View.VISIBLE);
    }

    @Override
    public void setItems(List<MoviesModel> items) {
        moviesAdapter.setItems(items);
        moviesAdapter.setNumberOfMovies(items.size());
        moviesAdapter.notifyDataSetChanged();
    }

    @Override
    public void showNoConnection() {
        moviesAdapter.clearList();
        noConection.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideNoConnection() {
        noConection.setVisibility(View.INVISIBLE);
    }


}
