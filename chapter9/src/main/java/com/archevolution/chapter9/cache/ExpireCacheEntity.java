package com.archevolution.chapter9.cache;

public class ExpireCacheEntity {
	private Object value;//缓存的值
	private long expireTime;//缓存过期时间
	
	public ExpireCacheEntity(Object value, long expireTime) {
		super();
		this.value = value;
		this.expireTime = expireTime;
	}
	
	public Object getValue() {
		return value;
	}
	public void setValue(Object value) {
		this.value = value;
	}
	public long getExpireTime() {
		return expireTime;
	}
	public void setExpireTime(long expireTime) {
		this.expireTime = expireTime;
	}
}
