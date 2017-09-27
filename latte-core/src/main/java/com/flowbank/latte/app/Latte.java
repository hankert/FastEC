package com.flowbank.latte.app;

import android.content.Context;

import java.util.HashMap;

/**
 * @auther jh
 * @des ${TODO}
 * Created by J.H on 2017/9/25.
 */

public final class Latte {

    public static Configurator init(Context context){
        getConfigurations().put(ConfigKeys.APPLACATION_CONTEXT.name(), context .getApplicationContext());
        return Configurator.getInstance();

    }
    public static HashMap<Object, Object> getConfigurations(){
        return Configurator.getInstance().getLatteConfigs();
    }

    public static Context getApplication(){
        return (Context) getConfigurations().get(ConfigKeys.APPLACATION_CONTEXT.name());
    }



}
