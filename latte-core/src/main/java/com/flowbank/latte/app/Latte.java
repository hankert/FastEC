package com.flowbank.latte.app;

import android.content.Context;

import java.util.WeakHashMap;

/**
 * @auther jh
 * @des ${TODO}
 * Created by J.H on 2017/9/25.
 */

public final class Latte {

    public static Configurator init(Context context){
        getConfigurations().put(ConfigType.APPLACATION_CONTEXT.name(), context .getApplicationContext());
        return Configurator.getInstance();

    }
    public static WeakHashMap<String, Object> getConfigurations(){
        return Configurator.getInstance().getLatteConfigs();
    }

    public static Context getApplication(){
        return (Context) getConfigurations().get(ConfigType.APPLACATION_CONTEXT.name());
    }



}
