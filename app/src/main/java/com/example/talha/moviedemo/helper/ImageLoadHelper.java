package com.example.talha.moviedemo.helper;


import android.widget.ImageView;

import com.example.talha.moviedemo.ui.activity.BaseActivity;
import com.example.talha.moviedemo.utils.Constant;
import com.squareup.picasso.Picasso;

public class ImageLoadHelper {

    private static ImageLoadHelper imageLoadHelper;

    public static ImageLoadHelper getInstance(){
        if (imageLoadHelper == null){
            imageLoadHelper = new ImageLoadHelper();
        }
        return imageLoadHelper;
    }

    public void loadImage(String posterPath, ImageView imgTarget){
        Picasso.with(BaseActivity.currentActivity)
                .load(Constant.IMAGE_PATH + posterPath)
                .into(imgTarget);
    }

}
