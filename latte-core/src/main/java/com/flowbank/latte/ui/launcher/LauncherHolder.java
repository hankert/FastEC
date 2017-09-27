package com.flowbank.latte.ui.launcher;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.bigkoo.convenientbanner.holder.Holder;

/**
 * Created by 傅令杰 on 2017/4/22
 */

public class LauncherHolder implements Holder<Integer> {

    private ImageView mImageView = null;

    @Override
    public View createView(Context context) {
        mImageView = new ImageView(context);
        return mImageView;
    }

    @Override
    public void UpdateUI(Context context, int position, Integer data) {
        mImageView.setBackgroundResource(data);
    }
}
