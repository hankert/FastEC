package com.flowbank.latte.app;

import java.util.WeakHashMap;

/**
 * @auther jh
 * @des ${TODO}
 * Created by J.H on 2017/9/25.
 */

public class Configurator {

    public static final WeakHashMap<String, Object> LATTE_CONFIGS = new WeakHashMap<>();
    private Configurator(){
        LATTE_CONFIGS.put(ConfigType.CONFIT_READY.name(), false);
    }

    public static Configurator getInstance() {
        return Holder.INSTANCE;
    }

    final WeakHashMap<String, Object> getLatteConfigs(){
        return LATTE_CONFIGS;
    }

    private static class Holder{
        private static final Configurator INSTANCE = new Configurator();
    }

    public final void configure(){
        LATTE_CONFIGS.put(ConfigType.CONFIT_READY.name(),  true);
    }
    public final Configurator withApiHost(String host){
        LATTE_CONFIGS.put(ConfigType.API_HOST.name(), host);
        return  this;
    }

    private void checkConfiguration(){
        final boolean isReady = (boolean) LATTE_CONFIGS.get(ConfigType.CONFIT_READY.name());
        if(!isReady){
            throw new RuntimeException("configuration is not ready, call configure");
        }

    }
    @SuppressWarnings("unchecked")
    final <T> T getConfigration(Enum<ConfigType> key){
        checkConfiguration();
        return (T) LATTE_CONFIGS.get(key.name());
    }


}
