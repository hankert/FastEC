package com.flowbank.latte.util.dimen;

import android.content.res.Resources;
import android.util.DisplayMetrics;

import com.flowbank.latte.app.Latte;

/**
 * @auther jh
 * @des ${TODO}
 * Created by J.H on 2017/9/26.
 */

public class DimenUtil {

    public static int getScreenWidth(){
        final Resources resources = Latte.getApplication().getResources();
        final DisplayMetrics dm = resources.getDisplayMetrics();
        return dm.widthPixels;
    }

    public static int getScreenHeight(){
        final Resources resources = Latte.getApplication().getResources();
        final DisplayMetrics dm = resources.getDisplayMetrics();
        return dm.heightPixels;
    }
}
