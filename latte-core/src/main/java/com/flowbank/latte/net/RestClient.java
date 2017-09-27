package com.flowbank.latte.net;

import android.content.Context;

import com.flowbank.latte.net.callback.IError;
import com.flowbank.latte.net.callback.IFailure;
import com.flowbank.latte.net.callback.IRequest;
import com.flowbank.latte.net.callback.ISuccess;
import com.flowbank.latte.net.callback.RequestCallbacks;
import com.flowbank.latte.ui.LatteLoader;
import com.flowbank.latte.ui.LoaderStyle;

import java.io.File;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
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
    private final LoaderStyle LOADER_STYLE;
    private final Context CONTEXT;
    private final File FILE;

    public RestClient(String url, Map<String, Object> params,
                      IRequest request, IFailure failure, IError error,
                      ISuccess success, RequestBody body, LoaderStyle loader_style, Context context, File file) {
        this.URL = url;
        PARAMS.putAll(params);
        this.REQUEST = request;
        this.FAILURE = failure;
        this.ERROR = error;
        this.SUCCESS = success;
        this.BODY = body;
        this.CONTEXT = context;
        this.LOADER_STYLE = loader_style;
        this.FILE = file;
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
        if (LOADER_STYLE != null){
            LatteLoader.showLoading(CONTEXT);
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
            case PUT_RAW:
                call = service.putRaw(URL, BODY);
                break;
            case DELETE:
                call = service.delete(URL, PARAMS);
                break;
            case POST_RAW:// post 原始数据
                call = service.postRaw(URL, BODY);
                break;
            case UPLOAD:
                final RequestBody requestBody = RequestBody.create(MediaType.parse(MultipartBody.FORM.toString()), FILE);
                final MultipartBody.Part body = MultipartBody.Part.createFormData("file", FILE.getName(), requestBody);
                call = RestCreator.getRestService().upload(URL, body);
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
                SUCCESS,
                LOADER_STYLE
        );
    }

    public final void get() {
        request(HttpMethod.GET);
    }

    public final void post() {
        if (BODY == null){
        request(HttpMethod.POST);

        }else {
            if (!PARAMS.isEmpty()){
                throw new RuntimeException("params must be null!");
            }
            request(HttpMethod.POST_RAW);
        }

    }


    public final void delete() {
        request(HttpMethod.DELETE);
    }

    public final void put() {
        if (BODY == null){
            request(HttpMethod.PUT);

        }else {
            if (!PARAMS.isEmpty()){
                throw new RuntimeException("params must be null!");
            }
            request(HttpMethod.PUT_RAW);
        }
    }

}
