package com.flowbank.latte.activities;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.ContentFrameLayout;

import com.flowbank.latte.R;
import com.flowbank.latte.delegates.LatteDelegagte;

import me.yokeyword.fragmentation.SupportActivity;

/**
 * @auther jh
 * @des ${TODO}
 * Created by J.H on 2017/9/25.
 */

public abstract class ProxyActivity extends SupportActivity{

    @SuppressWarnings("SpellCheckingInspection")
    public abstract LatteDelegagte setRootDelegte();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
    }

    private void initContainer(@Nullable Bundle savedInstanceState){
        final ContentFrameLayout container = new ContentFrameLayout(this);
        container.setId(R.id.delegate_container);

        setContentView(container);
        if (savedInstanceState == null)
        {
            loadRootFragment(R.id.delegate_container, setRootDelegte());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.gc();
        System.runFinalization();
    }
}
