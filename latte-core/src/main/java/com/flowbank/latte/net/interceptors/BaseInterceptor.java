package com.flowbank.latte.net.interceptors;

import java.util.LinkedHashMap;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;

/**
 * @auther jh
 * @des ${TODO}
 * Created by J.H on 2017/9/27.
 */

public abstract class BaseInterceptor implements Interceptor {



    protected LinkedHashMap<String, String> getUrlParameters(Chain chain){
        final HttpUrl url = chain.request().url();
        int size = url.querySize();
        final LinkedHashMap<String, String> params = new LinkedHashMap<>();
        for (int i = 0 ; i < size ; i++){
            params.put(url.queryParameterName(i), url.queryParameterValue(i));
        }
        return params;

    }
    protected String getUrlParameters(Chain chain, String key){

        final Request request = chain.request();
        return request.url().queryParameter(key);

    }

    /**
     * 获取post中的body
     * @param chain
     * @return
     */
    protected LinkedHashMap<String, String> getBodyParameters(Chain chain){
        final FormBody formBody = (FormBody) chain.request().body();
        int size = formBody.size();
        final LinkedHashMap<String, String> params = new LinkedHashMap<>();
        for (int i = 0 ; i < size ; i++){
            params.put(formBody.name(i), formBody.value(i));
        }
        return params;

    }

    protected String getBodyParameters(Chain chain, String key){
        return getBodyParameters(chain).get(key);
    }

}
