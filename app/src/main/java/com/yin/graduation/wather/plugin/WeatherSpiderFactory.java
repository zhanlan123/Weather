package com.yin.graduation.wather.plugin;

import android.content.Context;

import com.yin.graduation.wather.plugin.bean.AQI;
import com.yin.graduation.wather.plugin.bean.Alerts;
import com.yin.graduation.wather.plugin.bean.Forecast;
import com.yin.graduation.wather.plugin.bean.Index;
import com.yin.graduation.wather.plugin.bean.RealTime;
import com.yin.graduation.wather.plugin.spider.WeatherSpider;

public class WeatherSpiderFactory {
	private static WeatherSpiderFactory mInstance;
	private Context mContext = null;
	private String mPid = null;

	public synchronized static WeatherSpiderFactory getInstance(
			Context context, String cityCode) {
		if (mInstance == null)
			mInstance = new WeatherSpiderFactory(context, cityCode);
		return mInstance;
	}

	private WeatherSpiderFactory(Context context, String cityCode) {
		mContext = context;
		mPid = cityCode;
	}

	public ISpider getAvailableSpiders() {
		WeatherSpider weatherSpider = new WeatherSpider(mContext, mPid);
		weatherSpider.generateUrl();
		return weatherSpider;
	}

	public static abstract interface ISpider {
		public abstract void generateUrl();

		public abstract void generateWeatherInfos();

		public abstract Alerts getAlerts();

		public abstract AQI getAqi();

		public abstract Forecast getForecast();

		public abstract Index getIndex();

		public abstract RealTime getRealtime();

	}
}
