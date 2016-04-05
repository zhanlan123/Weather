package com.yin.graduation.wather.plugin.bean;

public class RealTime {
	private int animation_type;
	private String city_code;
	private long falling_tide;
	private int humidity;
	private double pressure;
	private long pub_time;
	private long rising_tide;
	private int temp;
	private int water;
	private String weather_name;
	private String wind;

	public int getAnimation_type() {
		return this.animation_type;
	}

	public String getCity_code() {
		return this.city_code;
	}

	public long getFalling_tide() {
		return this.falling_tide;
	}

	public int getHumidity() {
		return this.humidity;
	}

	public double getPressure() {
		return this.pressure;
	}

	public long getPub_time() {
		return this.pub_time;
	}

	public long getRising_tide() {
		return this.rising_tide;
	}

	public int getTemp() {
		return this.temp;
	}

	public int getWater() {
		return this.water;
	}

	public String getWeather_name() {
		return this.weather_name;
	}

	public String getWind() {
		return this.wind;
	}

	public void setAnimation_type(int paramInt) {
		this.animation_type = paramInt;
	}

	public void setCity_code(String paramString) {
		this.city_code = paramString;
	}

	public void setFalling_tide(long paramLong) {
		this.falling_tide = paramLong;
	}

	public void setHumidity(int paramInt) {
		this.humidity = paramInt;
	}

	public void setPressure(double paramDouble) {
		this.pressure = paramDouble;
	}

	public void setPub_time(long paramLong) {
		this.pub_time = paramLong;
	}

	public void setRising_tide(long paramLong) {
		this.rising_tide = paramLong;
	}

	public void setTemp(int paramInt) {
		this.temp = paramInt;
	}

	public void setWater(int paramInt) {
		this.water = paramInt;
	}

	public void setWeather_name(String paramString) {
		this.weather_name = paramString;
	}

	public void setWind(String paramString) {
		this.wind = paramString;
	}

	@Override
	public String toString() {
		return "RealTime [animation_type=" + animation_type + ", city_code="
				+ city_code + ", falling_tide=" + falling_tide + ", humidity="
				+ humidity + ", pressure=" + pressure + ", pub_time="
				+ pub_time + ", rising_tide=" + rising_tide + ", temp=" + temp
				+ ", water=" + water + ", weather_name=" + weather_name
				+ ", wind=" + wind + "]";
	}
	
}