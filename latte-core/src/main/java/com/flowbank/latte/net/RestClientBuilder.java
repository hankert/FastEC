package com.flowbank.latte.net;

import android.content.Context;

import com.flowbank.latte.net.callback.IError;
import com.flowbank.latte.net.callback.IFailure;
import com.flowbank.latte.net.callback.IRequest;
import com.flowbank.latte.net.callback.ISuccess;
import com.flowbank.latte.ui.LoaderStyle;

import java.util.Map;
import java.util.WeakHashMap;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * @auther jh
 * @des ${TODO}
 * Created by J.H on 2017/9/26.
 */

public class RestClientBuilder {

    private String mUrl = null;
    private static final Map<String, Object> PARAMS = RestCreator.getParams();

    private IRequest mRequest = null;
    private IFailure mFailure = null;
    private IError mError = null;
    private ISuccess mSuccess = null;
    private RequestBody mBody = null;
    private Context mContext = null;
    private LoaderStyle mLoaderStyle = null;

    RestClientBuilder() {

    }

    public final RestClientBuilder url(String url) {

        this.mUrl = url;
        return this;

    }


    public final RestClientBuilder params(WeakHashMap<String, Object> params) {

        PARAMS.putAll(params);
        return this;

    }

    public final RestClientBuilder params(String key, Object value) {

        PARAMS.put(key, value);
        return this;
    }

    public final RestClientBuilder raw(String raw) {

        this.mBody = RequestBody.create(MediaType.parse("application/json;charset=UTF-8"), raw);
        return this;

    }

    public final RestClientBuilder success(ISuccess iSuccess) {

        this.mSuccess = iSuccess;
        return this;

    }

    public final RestClientBuilder failure(IFailure iFailure) {

        this.mFailure = iFailure;
        return this;

    }

    public final RestClientBuilder error(IError iError) {

        this.mError = iError;
        return this;

    }

    public final RestClientBuilder request(IRequest iRequest) {

        this.mRequest = iRequest;
        return this;

    }

    public final RestClientBuilder loader(Context context, LoaderStyle style) {

        this.mContext = context;
        this.mLoaderStyle = style;
        return this;

    }

    public final RestClientBuilder loader(Context context) {

        this.mContext = context;
        this.mLoaderStyle = LoaderStyle.BallSpinFadeLoaderIndicator;
        return this;

    }


    public final RestClient build() {
        return new RestClient(mUrl, PARAMS, mRequest, mFailure, mError, mSuccess, mBody, mLoaderStyle, mContext);
    }


}
