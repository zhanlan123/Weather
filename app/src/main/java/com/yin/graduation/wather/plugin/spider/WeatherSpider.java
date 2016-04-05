package com.yin.graduation.wather.plugin.spider;

import android.content.Context;

import com.yin.graduation.wather.plugin.util.Tools;
import com.yin.graduation.wather.plugin.WeatherSpiderFactory;
import com.yin.graduation.wather.plugin.bean.AQI;
import com.yin.graduation.wather.plugin.bean.Alerts;
import com.yin.graduation.wather.plugin.bean.Forecast;
import com.yin.graduation.wather.plugin.bean.Index;
import com.yin.graduation.wather.plugin.bean.RealTime;
import com.yin.graduation.wather.plugin.util.HttpUtils;


public class WeatherSpider implements WeatherSpiderFactory.ISpider {

	private static final int FORCAST_FROM_ALL = 0;
	private static final int REALTIME_FROM_ALL = 1;
	private static final int AQI_FROM_ALL = 2;
	private static final int ALERT_FROM_ALL = 3;

	private static final String WEATHER_ALL = "http://weatherapi.market.xiaomi.com/wtr-v2/weather?cityId=%s";

	private Alerts mAlerts = null;
	private AQI mAqi = null;
	private Context mContext = null;
	private Forecast mForecast = null;
	private Index mIndex = null;
	private String mPid = null;
	private RealTime mRealtime = null;
	private String mWeatherAllUrl = null;

	public WeatherSpider(Context context, String pid) {
		this.mContext = context;
		this.mPid = pid;
	}

	private String generatePartWeatherInfo(String weatherResult, int type) {
		switch (type) {
		case FORCAST_FROM_ALL:
			return weatherResult.replace("forecast", "weatherinfo");
		case REALTIME_FROM_ALL:
			return weatherResult.replace("realtime", "weatherinfo");
		case AQI_FROM_ALL:
			return weatherResult;
		case ALERT_FROM_ALL:
			return weatherResult.replace("alert", "weatherinfo");
		default:
			return null;
		}

	}

	private void generateWeatherStruct(String forecastInfo,
			String realTimeInfo, String aqiInfo, String alertInfo) {

		String language = mContext.getResources().getConfiguration().locale
				.toString();
		try {
			mAqi = WeatherController.convertToNewAQI(aqiInfo, language, mPid);
			mForecast = WeatherController.convertToNewForecast(forecastInfo,
					language, mPid);
			mRealtime = WeatherController.convertToNewRealTime(realTimeInfo,
					language, mPid);
			mAlerts = WeatherController.convertToNewAlert(alertInfo, mPid);
			mIndex = WeatherController.convertToNewIndex(forecastInfo,
					language, mPid);
		} catch (Exception e) {
		}
	}

	private void getWeatherFrom1Url() {
		String weatherResult = HttpUtils.getText(this.mWeatherAllUrl);
		generateWeatherStruct(
				generatePartWeatherInfo(weatherResult, FORCAST_FROM_ALL),
				generatePartWeatherInfo(weatherResult, REALTIME_FROM_ALL),
				generatePartWeatherInfo(weatherResult, AQI_FROM_ALL),
				generatePartWeatherInfo(weatherResult, ALERT_FROM_ALL));
	}

	public void generateUrl() {
		mWeatherAllUrl = String.format(WEATHER_ALL, new Object[] { mPid });
		mWeatherAllUrl += Tools.getDeviceInfo();
	}

	public void generateWeatherInfos() {
		getWeatherFrom1Url();
	}

	public Alerts getAlerts() {
		return this.mAlerts;
	}

	public AQI getAqi() {
		return this.mAqi;
	}

	public Forecast getForecast() {
		return this.mForecast;
	}

	public Index getIndex() {
		return this.mIndex;
	}

	public RealTime getRealtime() {
		return this.mRealtime;
	}

}