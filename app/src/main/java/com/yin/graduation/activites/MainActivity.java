package com.yin.graduation.activites;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.TextView;

import com.yin.graduation.R;
import com.yin.graduation.sqlite.MyOperator;
import com.yin.graduation.sqlite.SQLiteHelper;
import com.yin.graduation.title.CustomTitleBar;
import com.yin.graduation.utils.SystemBarTintManager;
import com.yin.graduation.wather.plugin.bean.RealTime;

import at.markushi.ui.ActionView;
import at.markushi.ui.action.BackAction;
import at.markushi.ui.action.DrawerAction;


public class MainActivity extends Activity {


    public DrawerLayout mDrawerLayout = null;
    private MyOperator myOperator;
    private RealTime realTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setTheme(R.style.MyCustomTheme);

        super.onCreate(savedInstanceState);

        //自定义标题
        CustomTitleBar.getTitleBar(this,"我的自定义标题栏");

        //系统状态栏
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(true);
            SystemBarTintManager tintManager = new SystemBarTintManager( this );
            tintManager.setStatusBarTintEnabled(true);
            tintManager.setStatusBarTintResource( R.color.colorAccent );//通知栏所需颜色
        }

        setContentView(R.layout.activity_main);


        //左边抽屉
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        final ActionView actionView = (ActionView) findViewById(R.id.action);

        //点击按钮，改变形状，并弹出抽屉
        actionView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(actionView.getRotation() == ActionView.ROTATE_CLOCKWISE)
                {
                    actionView.setAction(new BackAction(), ActionView.ROTATE_COUNTER_CLOCKWISE);
                    actionView.setRotation(ActionView.ROTATE_COUNTER_CLOCKWISE);
                    mDrawerLayout.openDrawer(GravityCompat.START);
                }else{
                    actionView.setAction(new DrawerAction(), ActionView.ROTATE_CLOCKWISE);
                    actionView.setRotation(ActionView.ROTATE_CLOCKWISE);
                    mDrawerLayout.closeDrawer(GravityCompat.START);
                }
            }
        });
        //抽屉状态变化，改变按钮形状
        mDrawerLayout.setDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {

            }

            @Override
            public void onDrawerOpened(View drawerView) {

            }

            @Override
            public void onDrawerClosed(View drawerView) {

            }

            @Override
            public void onDrawerStateChanged(int newState) {
                if (newState == DrawerLayout.STATE_SETTLING){
                    if(mDrawerLayout.isDrawerOpen(GravityCompat.START))
                    {
                        actionView.setAction(new DrawerAction(), ActionView.ROTATE_CLOCKWISE);
                        actionView.setRotation(ActionView.ROTATE_CLOCKWISE);
                    }
                }

            }
        });
        //右边
        ImageButton addBtn = (ImageButton) findViewById(R.id.add_btn);


        TextView textTemp = (TextView) findViewById(R.id.location_temperature);

        myOperator = new MyOperator(new SQLiteHelper(MainActivity.this).getReadableDatabase());

        realTime = myOperator.getstatebyId(myOperator.findLocation());

        textTemp.setText(String.valueOf(realTime.getTemp()));

    }

    public void btn_test(View view){
        Intent in = new Intent(MainActivity.this, TestActivity.class);
        startActivity(in);
    }


    @TargetApi(19)
    private void setTranslucentStatus(boolean on) {
        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }


}
