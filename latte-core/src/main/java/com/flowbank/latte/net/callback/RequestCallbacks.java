package com.flowbank.latte.net.callback;

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

    public RequestCallbacks(IRequest request, IFailure failure, IError error, ISuccess success) {
        this.REQUEST = request;
        this.FAILURE = failure;
        this.ERROR = error;
        this.SUCCESS = success;
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
    }

    @Override
    public void onFailure(Call<String> call, Throwable t) {
        if (FAILURE != null) {
            FAILURE.onFailure();
        }
        if (REQUEST != null) {
            REQUEST.onRequestEnd();
        }
    }
}
