package com.yin.graduation.wather.plugin.util;

import java.util.Calendar;

public class Tools {

	public static String getDeviceInfo() {
		return String
				.format("&imei=%s&device=%s&miuiVersion=%s&modDevice=%s&source=miuiWeatherApp",
						new Object[] { "529e2dd3d767bdd3595eec30dd481050",
								"pisces", "JXCCNBD20.0", "" });
	}

	public static long getStartMillsInOneDay(Calendar calendar) {
		int i = calendar.get(1);
		int j = calendar.get(2);
		int k = calendar.get(5);
		calendar.clear();
		calendar.set(1, i);
		calendar.set(2, j);
		calendar.set(5, k);
		return calendar.getTimeInMillis();
	}
}
