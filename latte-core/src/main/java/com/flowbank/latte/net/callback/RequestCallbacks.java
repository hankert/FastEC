package com.flowbank.latte.net.callback;

import android.os.Handler;

import com.flowbank.latte.ui.LatteLoader;
import com.flowbank.latte.ui.LoaderStyle;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @auther jh
 * @des ${TODO}
 * Created by J.H on 2017/9/26.
 */

public class RequestCallbacks implements Callback<String> {

    private final IRequest REQUEST;
    private final IFailure FAILURE;
    private final IError ERROR;
    private final ISuccess SUCCESS;
    private final LoaderStyle LOADER_STYLE;

    private static final Handler HANDLER = new Handler();

    public RequestCallbacks(IRequest request, IFailure failure, IError error, ISuccess success, LoaderStyle style) {
        this.REQUEST = request;
        this.FAILURE = failure;
        this.ERROR = error;
        this.SUCCESS = success;
        this.LOADER_STYLE = style;
    }

    @Override
    public void onResponse(Call<String> call, Response<String> response) {
        if (response.isSuccessful()) {
            if (call.isExecuted()) {
                if (SUCCESS != null) {
                    SUCCESS.onSuccess(response.body());
                }
            }
        } else {
            if (ERROR != null) {
                ERROR.onError(response.code(), response.message());
            }
        }
        stopLoading();
    }

    @Override
    public void onFailure(Call<String> call, Throwable t) {
        if (FAILURE != null) {
            FAILURE.onFailure();
        }
        if (REQUEST != null) {
            REQUEST.onRequestEnd();
        }
        stopLoading();
    }

    private void stopLoading() {
        if (LOADER_STYLE != null){
            HANDLER.postDelayed(new Runnable() {
                @Override
                public void run() {
                    LatteLoader.stopLoading();
                }
            }, 2000);
        }
    }


}
