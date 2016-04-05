package com.yin.graduation.wather.plugin.bean;

public class IndexDetail {
	private String desc;
	private String detail;
	private String title;
	private int type;

	public String getDesc() {
		return this.desc;
	}

	public String getDetail() {
		return this.detail;
	}

	public String getTitle() {
		return this.title;
	}

	public int getType() {
		return this.type;
	}

	public void setDesc(String paramString) {
		this.desc = paramString;
	}

	public void setDetail(String paramString) {
		this.detail = paramString;
	}

	public void setTitle(String paramString) {
		this.title = paramString;
	}

	public void setType(int paramInt) {
		this.type = paramInt;
	}

	@Override
	public String toString() {
		return "IndexDetail [desc=" + desc + ", detail=" + detail + ", title="
				+ title + ", type=" + type + "]";
	}
}
