package com.hanker.faseec.example;

import com.flowbank.latte.activities.ProxyActivity;
import com.flowbank.latte.delegates.LatteDelegagte;

public class ExampleActivity extends ProxyActivity{


    @SuppressWarnings("SpellCheckingInspection")
    @Override
    public LatteDelegagte setRootDelegte() {
        return new ExampleDelegate();
    }
}
