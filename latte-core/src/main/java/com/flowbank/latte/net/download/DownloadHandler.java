package com.flowbank.latte.net.download;

import android.os.AsyncTask;

import com.flowbank.latte.net.RestCreator;
import com.flowbank.latte.net.callback.IError;
import com.flowbank.latte.net.callback.IFailure;
import com.flowbank.latte.net.callback.IRequest;
import com.flowbank.latte.net.callback.ISuccess;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @auther jh
 * @des ${TODO}
 * Created by J.H on 2017/9/27.
 */

public class DownloadHandler {

    private final String URL;
    private static final Map<String, Object> PARAMS = RestCreator.getParams();

    private final String DOWNLOAD_DIR;
    private final String EXTENSION;
    private final String NAME;

    private final IRequest REQUEST;
    private final IFailure FAILURE;
    private final IError ERROR;
    private final ISuccess SUCCESS;

    public DownloadHandler(String url, String download_dir, String extension, String name, IRequest request,
                           IFailure failure, IError error, ISuccess success) {
        this.URL = url;
        this.DOWNLOAD_DIR = download_dir;
        this.EXTENSION = extension;
        this.NAME = name;
        this.REQUEST = request;
        this.FAILURE = failure;
        this.ERROR = error;
        this.SUCCESS = success;
    }

    public final void handleDownload() {
        if (REQUEST != null) {
            REQUEST.onRequestStart();

        }
        RestCreator.getRestService().download(URL, PARAMS)
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()) {

                            final ResponseBody responseBody = response.body();
                            final SaveFileTask task = new SaveFileTask(REQUEST, SUCCESS);

                            task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, DOWNLOAD_DIR, EXTENSION, responseBody, NAME);

                            //注意判断  否则文件下载不全
                            if (task.isCancelled()) {
                                if (REQUEST != null) {
                                    REQUEST.onRequestEnd();

                                }
                            }
                        }else {
                            if (ERROR != null){
                                ERROR.onError(response.code(), response.message());
                            }
                        }

                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        if (FAILURE != null){
                            FAILURE.onFailure();
                        }
                    }
                });


    }


}
