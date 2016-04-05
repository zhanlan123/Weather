package com.yin.graduation.wather.plugin.spider;

import com.yin.graduation.wather.plugin.bean.AQI;
import com.yin.graduation.wather.plugin.bean.Alerts;
import com.yin.graduation.wather.plugin.bean.Alerts.Alert;
import com.yin.graduation.wather.plugin.bean.Forecast;
import com.yin.graduation.wather.plugin.bean.Index;
import com.yin.graduation.wather.plugin.bean.IndexDetail;
import com.yin.graduation.wather.plugin.bean.RealTime;
import com.yin.graduation.wather.plugin.util.Tools;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

public class WeatherController {
	public static AQI convertToNewAQI(String aqiInfo, String language,
			String pid) throws Exception {
		AQI aqi = new AQI();
		JSONObject aqiJSONObject = new JSONObject(aqiInfo).getJSONObject("aqi");
		aqi.setCity_code(pid);
		aqi.setPub_time(getAQITime(aqiJSONObject.getString("pub_time")));
		int aqiValue = WeatherUtilities.getAqi(aqiJSONObject.getString("aqi"));
		aqi.setAqi(aqiValue);
		aqi.setPm25(WeatherUtilities.getAqi(aqiJSONObject.getString("pm25")));
		aqi.setPm10(WeatherUtilities.getAqi(aqiJSONObject.getString("pm10")));
		aqi.setNo2(WeatherUtilities.getAqi(aqiJSONObject.getString("no2")));
		aqi.setSo2(WeatherUtilities.getAqi(aqiJSONObject.getString("so2")));
		aqi.setCo(WeatherConstants.NO_VALUE_FLAG.intValue());
		aqi.setO3(WeatherConstants.NO_VALUE_FLAG.intValue());
		aqi.setAqi_level(WeatherUtilities.getAqiLevel(aqiValue, language));
		aqi.setAqi_desc(WeatherUtilities.getAqiDesc(aqiValue, language));
		aqi.setSource(WeatherUtilities.getAQISource(language));
		aqi.setSpot(aqiJSONObject.getString("spot"));
		return aqi;
	}

	public static Alerts convertToNewAlert(String alertInfo, String language) {
		ArrayList<Alert> alertLists = new ArrayList<Alert>();
		Alerts alerts = new Alerts();
		try {
			JSONArray alertJSONArray = new JSONObject(alertInfo)
					.getJSONArray("weatherinfo");
			for (int i = 0; i < alertJSONArray.length(); ++i) {
				JSONObject jsonObject = alertJSONArray.getJSONObject(i);
				// alerts.getClass();
				Alerts.Alert alert = new Alerts.Alert();
				alert.setAbnormal(jsonObject.getString("abnormal"));
				alert.setDetail(jsonObject.getString("detail"));
				alert.setHoliday(jsonObject.getString("holiday"));
				alert.setLevel(jsonObject.getString("level"));
				alert.setPubTime(Long.valueOf(jsonObject.getLong("pub_time")));
				alert.setTitle(jsonObject.getString("title"));
				alertLists.add(alert);
			}
			alerts.setPid(language);
			alerts.setArryAlert(alertLists);

		} catch (Exception e) {
		}
		return alerts;
	}

	public static Forecast convertToNewForecast(String forecastInfo,
			String language, String pid) throws Exception {
		Forecast forecast = new Forecast();

		JSONObject forecastJSONObject = new JSONObject(forecastInfo);
		JSONObject forcastJSONObject = forecastJSONObject
				.getJSONObject("weatherinfo");

		JSONObject todayJSONObject = forecastJSONObject.getJSONObject("today");
		JSONObject yestodayJSONObject = forecastJSONObject
				.getJSONObject("yestoday");

		forecast.setPid(pid);
		forecast.setWinds(0, "");
		forecast.setWinds(1, WeatherUtilities.getWind(
				forcastJSONObject.getString("fx1"),
				forcastJSONObject.getString("fl1"), language));
		forecast.setWinds(2, WeatherUtilities.getWind(
				forcastJSONObject.getString("fx2"),
				forcastJSONObject.getString("fl2"), language));
		forecast.setWinds(3, WeatherUtilities.getWind(
				forcastJSONObject.getString("fx2"),
				forcastJSONObject.getString("fl3"), language));
		forecast.setWinds(4, WeatherUtilities.getWind(
				forcastJSONObject.getString("fx2"),
				forcastJSONObject.getString("fl4"), language));
		forecast.setWinds(5, WeatherUtilities.getWind(
				forcastJSONObject.getString("fx2"),
				forcastJSONObject.getString("fl5"), language));
		for (int i = 0; i < 6; ++i)
			forecast.setHumiditys(i, WeatherConstants.NO_VALUE_FLAG.intValue());
		WeatherUtilities.WeatherName[] weatherName = new WeatherUtilities.WeatherName[5];
		for (int i = 0; i < 5; ++i)
			weatherName[i] = WeatherUtilities.getWeatherName(
					forcastJSONObject.getString("weather" + (i + 1)), language);
		forecast.setWeatherNames(0, "");
		for (int i = 0; i < 5; ++i)
			forecast.setWeatherNames(i + 1, weatherName[i].getName());
		forecast.setWeatherNamesFrom(0,
				yestodayJSONObject.getString("weatherStart"));
		for (int i = 0; i < 5; ++i)
			forecast.setWeatherNamesFrom(i + 1, weatherName[i].getFrom());
		forecast.setWeatherNamesTo(0,
				yestodayJSONObject.getString("weatherEnd"));
		for (int i = 0; i < 5; ++i)
			forecast.setWeatherNamesTo(i + 1, weatherName[i].getTo());
		long forcastTime = getForcastTime(forcastJSONObject.getString("date_y")
				+ " " + forcastJSONObject.getString("fchh"));
		forecast.setPubtime(forcastTime);
		long yestodayTime = Tools.getStartMillsInOneDay(Calendar.getInstance());
		//Log.i("liweiping", "yestodayTime = "+getData(yestodayTime));
		//Log.i("liweiping", "forcastTime = "+getData(forcastTime));
		int[] forecastTemps = new int[12];
		for (int i = 0; i < 5; ++i) {
			String[] temps = forcastJSONObject.getString("temp" + (i + 1))
					.split("~");
			String[] minTemp = temps[0].split("℃");
			String[] maxTemp = temps[1].split("℃");
			forecastTemps[(i * 2)] = Integer.parseInt(minTemp[0]);
			forecastTemps[(1 + i * 2)] = Integer.parseInt(maxTemp[0]);
		}
		forecast.setTmpHighs(0, yestodayJSONObject.getInt("tempMax"));
		forecast.setTmpLows(0, yestodayJSONObject.getInt("tempMin"));
		// if (forecastTemps[0] < forecastTemps[1])
		// ;
		for (int i = 0; i < 5; ++i) {
			forecast.setTmpHighs(i + 1, forecastTemps[(i * 2)]);
			forecast.setTmpLows(i + 1, forecastTemps[(1 + i * 2)]);
		}
		if (forcastTime > yestodayTime)
			forecast.setTmpHighs(1, todayJSONObject.getInt("tempMax"));
		for (int i = 0; i < 6; ++i)
			forecast.setSunrise(i, Long.valueOf(Long.parseLong(String
					.valueOf(WeatherConstants.NO_VALUE_FLAG))));
		for (int i = 0; i < 6; ++i)
			forecast.setSunset(i, Long.valueOf(Long.parseLong(String
					.valueOf(WeatherConstants.NO_VALUE_FLAG))));
		for (int i = 0; i < 6; ++i)
			forecast.setPressures(i, WeatherConstants.NO_VALUE_FLAG.intValue());
		// Log.i("way", forecast.toString());
		return forecast;
	}

	public static Index convertToNewIndex(String indexInfo, String language,
			String pid) throws Exception {
		Index index = new Index();
		index.setCity_code(pid);

		JSONObject indexJSONObject = new JSONObject(indexInfo)
				.getJSONObject("weatherinfo");
		// Log.i("way", indexJSONObject.toString());
		ArrayList<IndexDetail> indexDetailLists = new ArrayList<IndexDetail>();
		IndexDetail windIndexDetail = new IndexDetail();
		windIndexDetail.setDesc(WeatherUtilities.getWind(
				indexJSONObject.getString("fx1"),
				indexJSONObject.getString("fl1"), language));
		windIndexDetail.setTitle("风力指数");
		windIndexDetail.setDetail(WeatherUtilities.getWindIndexDetail(
				indexJSONObject.getString("fl1"), language));
		windIndexDetail.setType(WeatherUtilities.getIndexType("风力指数").intValue());
		indexDetailLists.add(windIndexDetail);

		for (Map.Entry<String, String> entry : WeatherConstants.INDEX_OLD.entrySet()) {
			String key = entry.getKey();
			IndexDetail indexDetail = new IndexDetail();
			indexDetail.setType(WeatherUtilities.getIndexType(key).intValue());
			indexDetail.setTitle(WeatherUtilities.getIndexTitle(key, language));
			String indexValue = indexJSONObject.getString(WeatherConstants.INDEX_OLD
					.get(key));
			indexDetail.setDesc(WeatherUtilities.getIndexDesc(key, indexValue,
					language));

			indexDetail.setDetail(WeatherUtilities.getIndexDetail(key, indexValue,
					language));

			indexDetailLists.add(indexDetail);
		}
		index.setIndex(indexDetailLists);
		return index;
	}

	public static RealTime convertToNewRealTime(String realTimeInfo,
			String language, String pid) throws Exception {
		RealTime realTime = new RealTime();
		JSONObject realTimeJSONObject = new JSONObject(realTimeInfo)
				.getJSONObject("weatherinfo");
		realTime.setCity_code(pid);
		realTime.setPub_time(parseTime(realTimeJSONObject.getString("time")));
		realTime.setTemp(WeatherUtilities.getIntFromJSON(realTimeJSONObject, "temp"));
		realTime.setWind(WeatherUtilities.getWind(realTimeJSONObject.getString("WD"),
				realTimeJSONObject.getString("WS"), language));
		realTime.setAnimation_type(WeatherUtilities
				.getAnimationType(realTimeJSONObject.getString("weather")));
		realTime.setHumidity(WeatherUtilities.getHumidity(realTimeJSONObject
				.getString("SD")));
		realTime.setRising_tide(WeatherConstants.NO_VALUE_FLAG.intValue());
		realTime.setFalling_tide(WeatherConstants.NO_VALUE_FLAG.intValue());
		realTime.setPressure(WeatherConstants.NO_VALUE_FLAG.intValue());
		realTime.setWater(WeatherConstants.NO_VALUE_FLAG.intValue());
		realTime.setWeather_name(WeatherUtilities.getWeatherName(
				realTimeJSONObject.getString("weather"), language).getName());
		return realTime;
	}

	private static long getAQITime(String timeStr) {
		long aqiTime = -7754570281926000640L;
		try {
			aqiTime = new SimpleDateFormat("yyyy-MM-dd HH:00").parse(timeStr)
					.getTime();
		} catch (Exception e) {
		}
		return aqiTime;
	}

	private static long getForcastTime(String timeStr) {
		long forcastTime = -7754570281926000640L;
		try {
			forcastTime = new SimpleDateFormat("yyyy年M月d日 HH").parse(timeStr)
					.getTime();

		} catch (Exception e) {
		}
		return forcastTime;
	}

	private static long parseTime(String timeStr) {
		Calendar calendar1 = Calendar.getInstance();
		String[] arrayOfString = timeStr.split(":");
		// (60 * Integer.parseInt(arrayOfString[0]) +
		// Integer.parseInt(arrayOfString[1]));
		int i = Integer.parseInt(arrayOfString[0]);
		int j = Integer.parseInt(arrayOfString[1]);
		Long localLong = Long.valueOf(calendar1.getTimeInMillis());
		if (i - calendar1.get(11) > 2)
			localLong = Long.valueOf(localLong.longValue() - 24 * 60 * 60
					* 1000L);
		Calendar calendar2 = Calendar.getInstance();
		calendar2.setTimeInMillis(localLong.longValue());
		calendar2.set(11, i);
		calendar2.set(12, j);
		return calendar2.getTimeInMillis();
	}
	private static String getData(long time){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String date = sdf.format(new Date(time));
		return date;
	}
}