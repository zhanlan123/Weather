package com.yin.graduation.wather.plugin.bean;

import com.yin.graduation.wather.plugin.util.Constants;

import java.util.Arrays;

public class Forecast {
	public static final int DayNum = 6;
	private Integer[] humiditys = new Integer[6];
	private String pid;
	private Integer[] pressures = new Integer[6];
	private Long pub_time;
	private Long[] sunrise = new Long[6];
	private Long[] sunset = new Long[6];
	private Integer[] tmpHighs = new Integer[6];
	private Integer[] tmpLows = new Integer[6];
	private String[] weatherNames = new String[6];
	private String[] weatherNamesFrom = new String[6];
	private String[] weatherNamesTo = new String[6];
	private String[] winds = new String[6];

	public int getHumidity(int paramInt) {
		if ((paramInt > 5) || (paramInt < 0)
				|| (this.humiditys[paramInt] == null))
			return Constants.NO_VALUE_FLAG;
		return this.humiditys[paramInt].intValue();
	}

	public String getPid() {
		return this.pid;
	}

	public int getPressure(int paramInt) {
		if ((paramInt > 5) || (paramInt < 0)
				|| (this.pressures[paramInt] == null))
			return Constants.NO_VALUE_FLAG;
		return this.pressures[paramInt].intValue();
	}

	public Long getPubtime() {
		return this.pub_time;
	}

	public String getSunrise(int paramInt) {
		if ((paramInt > 5) || (paramInt < 0))
			return "";
		return this.sunrise[paramInt].toString();
	}

	public String getSunset(int paramInt) {
		if ((paramInt > 5) || (paramInt < 0))
			return "";
		return this.sunset[paramInt].toString();
	}

	public int getTmpHigh(int paramInt) {
		if ((paramInt > 5) || (paramInt < 0)
				|| (this.tmpHighs[paramInt] == null))
			return Constants.NO_VALUE_FLAG;
		return this.tmpHighs[paramInt].intValue();
	}

	public int getTmpLow(int paramInt) {
		if ((paramInt > 5) || (paramInt < 0)
				|| (this.tmpLows[paramInt] == null))
			return Constants.NO_VALUE_FLAG;
		return this.tmpLows[paramInt].intValue();
	}

	public String getWeatherNames(int paramInt) {
		if ((paramInt > 5) || (paramInt < 0))
			return "";
		return this.weatherNames[paramInt];
	}

	public String getWeatherNamesFrom(int paramInt) {
		if ((paramInt > 5) || (paramInt < 0))
			return "";
		return this.weatherNamesFrom[paramInt];
	}

	public String getWeatherNamesTo(int paramInt) {
		if ((paramInt > 5) || (paramInt < 0))
			return "";
		return this.weatherNamesTo[paramInt];
	}

	public String getWinds(int paramInt) {
		if ((paramInt > 5) || (paramInt < 0))
			return "";
		return this.winds[paramInt];
	}

	public void setHumiditys(int paramInt1, int paramInt2) {
		if ((paramInt1 > 5) || (paramInt1 < 0))
			return;
		this.humiditys[paramInt1] = Integer.valueOf(paramInt2);
	}

	public void setPid(String paramString) {
		this.pid = paramString;
	}

	public void setPressures(int paramInt1, int paramInt2) {
		if ((paramInt1 > 5) || (paramInt1 < 0))
			return;
		this.pressures[paramInt1] = Integer.valueOf(paramInt2);
	}

	public void setPubtime(Long paramLong) {
		this.pub_time = paramLong;
	}

	public void setSunrise(int paramInt, Long paramLong) {
		if ((paramInt > 5) || (paramInt < 0))
			return;
		this.sunrise[paramInt] = paramLong;
	}

	public void setSunset(int paramInt, Long paramLong) {
		if ((paramInt > 5) || (paramInt < 0))
			return;
		this.sunset[paramInt] = paramLong;
	}

	public void setTmpHighs(int paramInt1, int paramInt2) {
		if ((paramInt1 > 5) || (paramInt1 < 0))
			return;
		this.tmpHighs[paramInt1] = Integer.valueOf(paramInt2);
	}

	public void setTmpLows(int paramInt1, int paramInt2) {
		if ((paramInt1 > 5) || (paramInt1 < 0))
			return;
		this.tmpLows[paramInt1] = Integer.valueOf(paramInt2);
	}

	public void setWeatherNames(int paramInt, String paramString) {
		if ((paramInt > 5) || (paramInt < 0))
			return;
		this.weatherNames[paramInt] = paramString;
	}

	public void setWeatherNamesFrom(int paramInt, String paramString) {
		if ((paramInt > 5) || (paramInt < 0))
			return;
		this.weatherNamesFrom[paramInt] = paramString;
	}

	public void setWeatherNamesTo(int paramInt, String paramString) {
		if ((paramInt > 5) || (paramInt < 0))
			return;
		this.weatherNamesTo[paramInt] = paramString;
	}

	public void setWinds(int paramInt, String paramString) {
		if ((paramInt > 5) || (paramInt < 0))
			return;
		this.winds[paramInt] = paramString;
	}

	@Override
	public String toString() {
		return "Forecast [humiditys=" + Arrays.toString(humiditys) + ", pid="
				+ pid + ", pressures=" + Arrays.toString(pressures)
				+ ", pub_time=" + pub_time + ", sunrise="
				+ Arrays.toString(sunrise) + ", sunset="
				+ Arrays.toString(sunset) + ", tmpHighs="
				+ Arrays.toString(tmpHighs) + ", tmpLows="
				+ Arrays.toString(tmpLows) + ", weatherNames="
				+ Arrays.toString(weatherNames) + ", weatherNamesFrom="
				+ Arrays.toString(weatherNamesFrom) + ", weatherNamesTo="
				+ Arrays.toString(weatherNamesTo) + ", winds="
				+ Arrays.toString(winds) + "]";
	}

}
