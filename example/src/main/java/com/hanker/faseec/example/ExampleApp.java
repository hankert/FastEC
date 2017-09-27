package com.hanker.faseec.example;

import android.app.Application;

import com.flowbank.latte.app.Latte;
import com.flowbank.latte.ec.icon.FontEcModule;
import com.flowbank.latte.net.interceptors.DebugInterceptor;
import com.joanzapata.iconify.fonts.FontAwesomeModule;

/**
 * @auther jh
 * @des ${TODO}
 * Created by J.H on 2017/9/25.
 */

public class ExampleApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Latte.init(this)
                .withIcon(new FontAwesomeModule())
                .withIcon(new FontEcModule())
                .withInterceptor(new DebugInterceptor("index", R.raw.test))
                .withApiHost("http://127.0.0.1")
                .configure();
    }
}
