package com.hanker.faseec.example;

import android.app.Application;

import com.flowbank.latte.app.Latte;
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
                .withApiHost("http://127.0.0.1")
                .withIcon(new FontAwesomeModule())
                .withIcon(new FontAwesomeModule())
                .configure();
    }
}
