package com.flowbank.latte.net;

import com.flowbank.latte.net.callback.IError;
import com.flowbank.latte.net.callback.IFailure;
import com.flowbank.latte.net.callback.IRequest;
import com.flowbank.latte.net.callback.ISuccess;

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

    private String mUrl;
    private static final Map<String, Object> PARAMS = RestCreator.getParams();

    private IRequest mRequest;
    private IFailure mFailure;
    private IError mError;
    private ISuccess mSuccess;
    private RequestBody mBody;

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


    public final RestClient build() {
        return new RestClient(mUrl, PARAMS, mRequest, mFailure, mError, mSuccess, mBody);
    }


}
