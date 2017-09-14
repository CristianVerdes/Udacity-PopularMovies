package com.example.cristianv.popularmovies.display.movies;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cristianv.popularmovies.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cristianv on 8/29/17.
 */

public class MoviesAdapter extends RecyclerView.Adapter<MovieViewHolder> {
    private List<MoviesModel> items;
    private int numberOfMovies;
    final private ListItemClickListener onClickListener;

    public MoviesAdapter(ListItemClickListener onClickListener){
        this.onClickListener = onClickListener;
        this.items = new ArrayList<>();
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View view = LayoutInflater.from(context)
                .inflate(R.layout.movie_list_item, parent, false);

        MovieViewHolder movieViewHolder = new MovieViewHolder(view, onClickListener);

        return movieViewHolder;
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, final int position) {
        holder.bind(items.get(position).getPhotoPath());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickListener.onListItemClick(items.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return numberOfMovies;
    }

    public void clearList(){
        this.items.clear();
        numberOfMovies = 0;
        this.notifyDataSetChanged();
    }


    // Setters

    public void setItems(List<MoviesModel> items) {
        this.items = items;
    }

    public void setNumberOfMovies(int numberOfMovies) {
        this.numberOfMovies = numberOfMovies;
    }


}
