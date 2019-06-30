package com.example.talha.moviedemo.ui.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.example.talha.moviedemo.R;
import com.example.talha.moviedemo.helper.ImageLoadHelper;
import com.example.talha.moviedemo.model.MovieResult;
import com.example.talha.moviedemo.ui.activity.BaseActivity;
import com.example.talha.moviedemo.ui.interfaces.ItemClickListener;
import com.example.talha.moviedemo.ui.interfaces.OnLoadMoreListener;

import java.util.ArrayList;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ArrayList<MovieResult> movieResults;
    private ItemClickListener itemClickListener;
    private Context context;
    private final int VIEW_ITEM = 1;
    private final int VIEW_PROG = 0;
    private int visibleThreshold = 5;
    private int lastVisibleItem, totalItemCount;
    private boolean isLoading;
    private OnLoadMoreListener onLoadMoreListener;
    private String posterPath;
    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.movie_photo)
        ImageView movie_photo;
        @BindView(R.id.movie_name)
        TextView  movie_name;
        @BindView(R.id.movie_age)
        TextView movie_popularity;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this,view);
        }
    }
    public class ProgressHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.itemProgressbar)
        ProgressBar progressBar;

        public ProgressHolder(View view) {
            super(view);
            ButterKnife.bind(this,view);
        }
    }

    public MovieListAdapter(ArrayList<MovieResult> movieResults, Context context,RecyclerView recyclerView, ItemClickListener itemClickListener) {
        this.movieResults = movieResults;
        this.itemClickListener = itemClickListener;
        this.context = context;

        //Load More
        final LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                totalItemCount = linearLayoutManager.getItemCount();
                lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
                if (!isLoading && totalItemCount <= (lastVisibleItem + visibleThreshold)) {
                    if (onLoadMoreListener != null) {
                        onLoadMoreListener.onLoadMore();
                    }
                    isLoading = true;
                }
            }
        });
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType == VIEW_ITEM) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item_list, parent, false);
            return new ViewHolder(view);
        } else if (viewType == VIEW_PROG) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.progress_bar, parent, false);
            return new ProgressHolder(view);
        }
        return null;
    }
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof ViewHolder) {
            ViewHolder viewHolder = (ViewHolder) holder;
            viewHolder.movie_name.setText(movieResults.get(position).getTitle());
            viewHolder.movie_popularity.setText(String.valueOf(movieResults.get(position).getPopularity()));
            posterPath = movieResults.get(position).getPosterPath();
            ImageLoadHelper.getInstance().loadImage(posterPath,viewHolder.movie_photo);

        } else if (holder instanceof ProgressHolder) {
            ProgressHolder progressHolder = (ProgressHolder) holder;
            progressHolder.progressBar.setIndeterminate(true);
        }
    }

    @Override
    public int getItemCount() {
        return movieResults == null ? 0 : movieResults.size();
    }

    @Override
    public int getItemViewType(int position) {
        return movieResults.get(position) == null ? VIEW_PROG : VIEW_ITEM;
    }
    public void setOnLoadMoreListener(OnLoadMoreListener mOnLoadMoreListener) {
        this.onLoadMoreListener = mOnLoadMoreListener;
    }
    public void setLoaded() {
        isLoading = false;
    }

}

