package com.example.talha.moviedemo.network;

import com.example.talha.moviedemo.utils.Constant;
import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ApiService {
    public static ApiService apiService = new ApiService();
    public static Retrofit retrofit;
    public ApiInterface apiInterface;

    public static ApiService getInstance(){
        return apiService;
    }

    public static Retrofit getRetrofitInstance(){
        OkHttpClient.Builder builder = new OkHttpClient().newBuilder();
        builder.readTimeout(30,TimeUnit.SECONDS);
        builder.connectTimeout(10,TimeUnit.SECONDS);
        OkHttpClient client = builder.build();


        if(retrofit == null){
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(Constant.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
