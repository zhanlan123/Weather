package com.yin.graduation.activites;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

import com.yin.graduation.R;
import com.yin.graduation.json.analysis.CityCodeAnalysis;
import com.yin.graduation.sqlite.MyOperator;
import com.yin.graduation.sqlite.SQLiteHelper;
import com.yin.graduation.wather.plugin.WeatherSpiderFactory;
import com.yin.graduation.wather.plugin.bean.RealTime;

public class StartActivity extends Activity {

    private static final int FAILURE = 0; // 失败
    private static final int SUCCESS = 1; // 成功
    private static final int OFFLINE = 2; // 如果支持离线阅读，进入离线模式
    private static final int SHOW_TIME_MIN = 800;

    private WeatherSpiderFactory.ISpider spider;
    private SQLiteHelper myHelper;
    private MyOperator myOperator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.start_activity);

        Thread thread = new Thread(new Runnable() {
            public void run() {
                String cityCode = CityCodeAnalysis.getCityCode("岳阳");

                WeatherSpiderFactory factory = WeatherSpiderFactory.getInstance(StartActivity.this,cityCode);

                spider = factory.getAvailableSpiders();

                spider.generateWeatherInfos();
                if(!("".equals(spider.getRealtime().getCity_code()))){

                    Message message = new Message();
                    message.what = 1;
                    handler.sendMessage(message);
                }else{
                    handler.sendEmptyMessage(2);
                }
            }
        });
        thread.start();

    }

    private Handler handler = new Handler(){
        public void handleMessage(Message msg) {
            if(msg.what == 1){

                RealTime realTime = spider.getRealtime();

                myHelper = new SQLiteHelper(StartActivity.this);

                myOperator = new MyOperator(myHelper.getWritableDatabase());

                if(!myOperator.check_same(realTime.getCity_code()))
                {
                    myOperator.insert(realTime);
                    myOperator.locationInsert(realTime.getCity_code(),"岳阳");
                }else{
                    myOperator.update(realTime);
                }

                Intent intent = new Intent();
                intent.setClass(StartActivity.this, MainActivity.class);
                startActivity(intent);
                StartActivity.this.finish();

            }else if(msg.what == 2){
                Toast.makeText(StartActivity.this, "加载失败", Toast.LENGTH_SHORT).show();
            }
        }
    };

}
