package com.flowbank.latte.net.rx;

import android.content.Context;

import com.flowbank.latte.net.HttpMethod;
import com.flowbank.latte.net.RestCreator;
import com.flowbank.latte.ui.LatteLoader;
import com.flowbank.latte.ui.LoaderStyle;

import java.io.File;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;


/**
 * @auther jh
 * @des rxjava+retrofit
 * Created by J.H on 2017/9/26.
 */

public class RxRestClient {

    private final String URL;
    private static final Map<String, Object> PARAMS = RestCreator.getParams();

    private final RequestBody BODY;
    private final LoaderStyle LOADER_STYLE;
    private final Context CONTEXT;
    private final File FILE;

    public RxRestClient(String url, Map<String, Object> params,
                       RequestBody body, LoaderStyle loader_style, Context context, File file) {
        this.URL = url;
        PARAMS.putAll(params);

        this.BODY = body;
        this.CONTEXT = context;
        this.LOADER_STYLE = loader_style;
        this.FILE = file;

    }

    public static RxRestClientBuilder builder() {
        return new RxRestClientBuilder();
    }


    private Observable<String> request(HttpMethod method) {
        final RxRestService service = RestCreator.getRxRestService();
        Observable<String> observable = null;

        if (LOADER_STYLE != null){
            LatteLoader.showLoading(CONTEXT);
        }

        switch (method) {
            case GET:
                observable = service.get(URL, PARAMS);
                break;
            case POST:
                observable = service.post(URL, PARAMS);
                break;
            case PUT:
                observable = service.put(URL, PARAMS);
                break;
            case PUT_RAW:
                observable = service.putRaw(URL, BODY);
                break;
            case DELETE:
                observable = service.delete(URL, PARAMS);
                break;
            case POST_RAW:// post 原始数据
                observable = service.postRaw(URL, BODY);
                break;
            case UPLOAD:
                final RequestBody requestBody = RequestBody.create(MediaType.parse(MultipartBody.FORM.toString()), FILE);
                final MultipartBody.Part body = MultipartBody.Part.createFormData("file", FILE.getName(), requestBody);
                observable = RestCreator.getRxRestService().upload(URL, body);
                break;
            default:
                break;
        }
       return  observable;

    }



    public final Observable<String> get() {
        return request(HttpMethod.GET);
    }

    public final Observable<String> post() {
        if (BODY == null){
            return  request(HttpMethod.POST);

        }else {
            if (!PARAMS.isEmpty()){
                throw new RuntimeException("params must be null!");
            }
            return  request(HttpMethod.POST_RAW);
        }

    }

    public final Observable<String> delete() {
        return  request(HttpMethod.DELETE);
    }

    public final Observable<String> put() {
        if (BODY == null){
            return   request(HttpMethod.PUT);

        }else {
            if (!PARAMS.isEmpty()){
                throw new RuntimeException("params must be null!");
            }
            return  request(HttpMethod.PUT_RAW);
        }
    }

    public final Observable<ResponseBody> download(){
        final Observable<ResponseBody> responseBodyObservable = RestCreator.getRxRestService().download(URL, PARAMS);

        return  responseBodyObservable;
    }



}