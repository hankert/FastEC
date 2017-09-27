package com.flowbank.latte.util.timer;

import java.util.TimerTask;

/**
 * @auther jh
 * @des ${TODO}
 * Created by J.H on 2017/9/27.
 */

public class BaseTimerTask extends TimerTask{

    private ITimerListener mITimerListener = null;


    public BaseTimerTask(ITimerListener timerListener) {
        this.mITimerListener = timerListener;
    }

    @Override
    public void run() {
        if (mITimerListener != null){
            mITimerListener.onTimer();
        }
    }
}
