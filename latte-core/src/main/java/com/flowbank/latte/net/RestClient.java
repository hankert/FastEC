package com.flowbank.latte.net;

import com.flowbank.latte.net.callback.IError;
import com.flowbank.latte.net.callback.IFailure;
import com.flowbank.latte.net.callback.IRequest;
import com.flowbank.latte.net.callback.ISuccess;
import com.flowbank.latte.net.callback.RequestCallbacks;

import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;

/**
 * @auther jh
 * @des ${TODO}
 * Created by J.H on 2017/9/26.
 */

public class RestClient {

    private final String URL;
    private static final Map<String, Object> PARAMS = RestCreator.getParams();

    private final IRequest REQUEST;
    private final IFailure FAILURE;
    private final IError ERROR;
    private final ISuccess SUCCESS;
    private final RequestBody BODY;

    public RestClient(String url, Map<String, Object> params,
                      IRequest request, IFailure failure, IError error,
                      ISuccess success, RequestBody body) {
        this.URL = url;
        PARAMS.putAll(params);
        this.REQUEST = request;
        this.FAILURE = failure;
        this.ERROR = error;
        this.SUCCESS = success;
        this.BODY = body;
    }

    public static RestClientBuilder builder() {
        return new RestClientBuilder();
    }


    private void request(HttpMethod method) {
        final RestService service = RestCreator.getRestService();
        Call<String> call = null;

        if (REQUEST != null) {
            REQUEST.onRequestStart();
        }
        switch (method) {
            case GET:
                call = service.get(URL, PARAMS);
                break;
            case POST:
                call = service.post(URL, PARAMS);
                break;
            case PUT:
                call = service.put(URL, PARAMS);
                break;
            case DELETE:
                call = service.delete(URL, PARAMS);
                break;
            default:
                break;
        }
        if (call != null) {
            call.enqueue(getRequestCallback());
        }

    }

    private Callback<String> getRequestCallback() {
        return new RequestCallbacks(
                REQUEST,
                FAILURE,
                ERROR,
                SUCCESS
        );
    }

    public final void get() {
        request(HttpMethod.GET);
    }

    public final void post() {
        request(HttpMethod.POST);
    }

    public final void delete() {
        request(HttpMethod.DELETE);
    }

    public final void put() {
        request(HttpMethod.PUT);
    }

}
