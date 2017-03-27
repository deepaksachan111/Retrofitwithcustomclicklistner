package info.androidhive.retrofit.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;


import info.androidhive.retrofit.OnItemClickListener;
import info.androidhive.retrofit.R;
import info.androidhive.retrofit.model.Movie;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MovieViewHolder> {

    private List<Movie> movies;
    private int rowLayout;
    private Context context;
    private  int lastPosition;
    private final OnItemClickListener listener;

    public static class MovieViewHolder extends RecyclerView.ViewHolder {
        LinearLayout moviesLayout;
        TextView movieTitle;
        TextView data;
        TextView movieDescription;
        TextView rating;


        public MovieViewHolder(View v) {
            super(v);
            moviesLayout = (LinearLayout) v.findViewById(R.id.movies_layout);
            movieTitle = (TextView) v.findViewById(R.id.title);
            data = (TextView) v.findViewById(R.id.subtitle);
            movieDescription = (TextView) v.findViewById(R.id.description);
            rating = (TextView) v.findViewById(R.id.rating);
        }

        public void bind(final Movie movies, final OnItemClickListener listener, final int pos) {
            movieTitle.setText(movies.getTitle());
            data.setText(movies.getReleaseDate());
            movieDescription.setText(movies.getOverview());
            rating.setText(movies.getVoteAverage().toString());

            itemView.setOnClickListener(new View.OnClickListener() {

                @Override public void onClick(View v) {

                    listener.onItemClick(movies,pos);

                }

            });

        }


    }

    public MoviesAdapter(List<Movie> movies, int rowLayout, Context context,  OnItemClickListener listener) {
        this.movies = movies;
        this.rowLayout = rowLayout;
        this.context = context;
        this.listener = listener;
    }

    @Override
    public MoviesAdapter.MovieViewHolder onCreateViewHolder(ViewGroup parent,
                                                            int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
       //Animation scaleUp = AnimationUtils.loadAnimation(context, android.R.anim.fade_in);
       // view.startAnimation(scaleUp);
        return new MovieViewHolder(view);
    }


    @Override
    public void onBindViewHolder(MovieViewHolder holder, final int position) {
    holder.bind(movies.get(position),listener,position);

        if(position > lastPosition) {

            Animation animation = AnimationUtils.loadAnimation(context,
                    R.anim.up_from_bottom);
            holder.itemView.startAnimation(animation);
            lastPosition = position;
        }
        if(position == 0 || position==1) {

            Animation animation = AnimationUtils.loadAnimation(context,
                    R.anim.up_from_bottom);
            holder.itemView.startAnimation(animation);
            lastPosition = position;
        }


    }



    @Override
    public int getItemCount() {
        return movies.size();
    }
}