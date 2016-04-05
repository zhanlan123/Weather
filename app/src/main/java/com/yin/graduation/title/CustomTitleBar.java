package com.yin.graduation.title;

/**
 * Created by yin on 2016/3/30.
 */

import android.app.Activity;
import android.view.Window;

import com.yin.graduation.R;

/**
 * @author aaron
 *
 */
public class CustomTitleBar {

    private static Activity mActivity;

    /**
     * @see [自定义标题栏]
     * @param activity
     * @param title
     */
    public static void getTitleBar(Activity activity,String title) {
        mActivity = activity;

        activity.requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        activity.setContentView(R.layout.titlebar);
        activity.getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,
                R.layout.titlebar);


    }
}
