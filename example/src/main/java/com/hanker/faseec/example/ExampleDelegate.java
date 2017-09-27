package com.hanker.faseec.example;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;

import com.flowbank.latte.delegates.LatteDelegagte;
import com.flowbank.latte.net.RestClient;
import com.flowbank.latte.net.callback.IError;
import com.flowbank.latte.net.callback.IFailure;
import com.flowbank.latte.net.callback.ISuccess;

/**
 * @auther jh
 * @des ${TODO}
 * Created by J.H on 2017/9/26.
 */

public class ExampleDelegate extends LatteDelegagte {
    @Override
    public Object setLayout() {
        return R.layout.delegate_example;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        testRestClient();
    }

    private void testRestClient(){
        RestClient.builder()
                .url("http://127.0.0.1/index")
                .loader(getContext())
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        Toast.makeText(getContext(), response, Toast.LENGTH_LONG).show();
                    }
                })
                .failure(new IFailure() {
                    @Override
                    public void onFailure() {

                    }
                })
                .error(new IError() {
                    @Override
                    public void onError(int code, String msg) {

                    }
                })
                .build()
                .get();
    }
}
