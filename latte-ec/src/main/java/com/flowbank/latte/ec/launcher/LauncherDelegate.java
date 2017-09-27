package com.flowbank.latte.ec.launcher;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.flowbank.latte.delegates.LatteDelegagte;
import com.flowbank.latte.ec.R;
import com.flowbank.latte.ec.R2;
import com.flowbank.latte.util.timer.BaseTimerTask;
import com.flowbank.latte.util.timer.ITimerListener;

import java.text.MessageFormat;
import java.util.Timer;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * @auther jh
 * @des ${TODO}
 * Created by J.H on 2017/9/27.
 */

public class LauncherDelegate extends LatteDelegagte implements ITimerListener{

    private Timer mtimer;
    private int mCount = 5;

    @BindView(R2.id.tv_luncher_timer)
    AppCompatTextView mTvTimer = null;

    @OnClick(R2.id.tv_luncher_timer)
    void onClickTimerView(){


    }

    private void initTimer(){
        mtimer = new Timer();
        final BaseTimerTask task = new BaseTimerTask(this);
        mtimer.schedule(task, 0, 1000);
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_luncher;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        initTimer();
    }

    @Override
    public void onTimer() {
        getProxyActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (mTvTimer != null){
                    mTvTimer.setText(MessageFormat.format("跳过\n{0}s", mCount));
                    mCount--;
                    if (mCount < 0){
                        if (mTvTimer != null){
                            mtimer.cancel();
                            mtimer = null;
                        }
                    }
                }
            }
        });
    }
}
