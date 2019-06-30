package com.example.talha.moviedemo.helper;

import com.afollestad.materialdialogs.MaterialDialog;
import com.example.talha.moviedemo.ui.activity.BaseActivity;

public class DialogHelper {

    private static DialogHelper dialogHelper;
    private MaterialDialog loadingDialog;

    public static DialogHelper getInstance(){
        if (dialogHelper == null){
            dialogHelper = new DialogHelper();
        }
        return dialogHelper;
    }
    public void showLoadingDialog(){
        if (loadingDialog != null && loadingDialog.isShowing())
            return;
        loadingDialog = new MaterialDialog.Builder(BaseActivity.currentActivity)
                .content("Loading...")
                .cancelable(false)
                .progress(true,0)
                .progressIndeterminateStyle(true)
                .show();
    }
    public void hideLoadingDialog(){
        if (loadingDialog != null && loadingDialog.isShowing()){
            loadingDialog.dismiss();
            loadingDialog = null;
        }
    }
}
