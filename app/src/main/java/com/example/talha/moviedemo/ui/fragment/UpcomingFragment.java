package com.example.talha.moviedemo.ui.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.talha.moviedemo.R;
import com.example.talha.moviedemo.helper.DialogHelper;
import com.example.talha.moviedemo.model.Movie;
import com.example.talha.moviedemo.model.MovieResult;
import com.example.talha.moviedemo.network.ApiErrorUtils;
import com.example.talha.moviedemo.network.ApiInterface;
import com.example.talha.moviedemo.network.ApiService;
import com.example.talha.moviedemo.ui.activity.BaseActivity;
import com.example.talha.moviedemo.ui.adapter.MovieListAdapter;
import com.example.talha.moviedemo.utils.Constant;
import com.example.talha.moviedemo.ui.interfaces.ItemClickListener;
import com.example.talha.moviedemo.ui.interfaces.OnLoadMoreListener;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class UpcomingFragment extends Fragment {
    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;
    private Call<Movie> call;
    private ApiInterface apiInterface;
    private ArrayList<MovieResult> movieResults;
    private ArrayList<MovieResult> upcomingList = new ArrayList<>();



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_upcoming,container,false);
        ButterKnife.bind(this,view);

        DialogHelper.getInstance().showLoadingDialog();
        apiInterface = ApiService.getRetrofitInstance().create(ApiInterface.class);
        call = apiInterface.getUpcomingMovies(Constant.API_KEY);
        call.enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                DialogHelper.getInstance().hideLoadingDialog();
                if (response.isSuccessful()){
                    getUpcomingMovieList(response.body());
                }
                else {
                    ApiErrorUtils.parseError(response);
                }
            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {
                DialogHelper.getInstance().hideLoadingDialog();
                Toast.makeText(BaseActivity.currentActivity, "Error", Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }

    public void getUpcomingMovieList(Movie response){
        movieResults = response.getMovieResult();
        for (int i = 0; i < 5 ;i++) {
            upcomingList.add(movieResults.get(i));
        }
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        layoutManager.scrollToPosition(0);
        recyclerView.setLayoutManager(layoutManager);

        final MovieListAdapter adapter_items = new MovieListAdapter(upcomingList, getActivity(),recyclerView,new ItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                //itemClick
            }
        });
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter_items);
        adapter_items.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                if (upcomingList.size() < movieResults.size()) {
                    upcomingList.add(null);
                    adapter_items.notifyItemInserted(upcomingList.size() - 1);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            upcomingList.remove(upcomingList.size() - 1);
                            adapter_items.notifyItemRemoved(upcomingList.size());

                            //Generating more data
                            int index = upcomingList.size();
                            int end = index + 5;
                            for (int i = index; i < end; i++) {

                                upcomingList.add(movieResults.get(i));
                            }
                            adapter_items.notifyDataSetChanged();
                            adapter_items.setLoaded();
                        }
                    }, 2000);
                } else {
                    // else
                }
            }
        });
    }

}
