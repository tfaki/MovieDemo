package com.example.talha.moviedemo.network;

import android.widget.Toast;

import com.example.talha.moviedemo.ui.activity.BaseActivity;

import java.lang.annotation.Annotation;

import retrofit2.Converter;
import retrofit2.Response;

public class ApiErrorUtils {

    public static ApiError parseError(Response<?> response){
        Converter<okhttp3.ResponseBody,ApiError> converter =
                ApiService.retrofit.responseBodyConverter(ApiError.class,new Annotation[0]);
        ApiError error = new ApiError();

        try {
            error = converter.convert(response.errorBody());
            error.setStatus_code(response.code());

            if (error.getStatus_message() != null && !error.getStatus_message().isEmpty()){
                Toast.makeText(BaseActivity.currentActivity, "Error :" + error.getStatus_message(), Toast.LENGTH_SHORT).show();
            }
        }
        catch (Exception e){
            e.printStackTrace();
            return error;
        }
        return error;
    }
}
