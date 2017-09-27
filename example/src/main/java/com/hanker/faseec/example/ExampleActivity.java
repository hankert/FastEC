package com.hanker.faseec.example;

import com.flowbank.latte.activities.ProxyActivity;
import com.flowbank.latte.delegates.LatteDelegagte;
import com.flowbank.latte.ec.launcher.LauncherScrollDelegate;

public class ExampleActivity extends ProxyActivity{


    @SuppressWarnings("SpellCheckingInspection")
    @Override
    public LatteDelegagte setRootDelegte() {
        return new LauncherScrollDelegate();
    }
}
