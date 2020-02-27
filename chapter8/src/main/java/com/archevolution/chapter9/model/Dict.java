package com.archevolution.chapter9.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Dict {
	@Id
	private int id;//主键 id
	private String type;//字典数据分类
	private String code;//字典编码
	private String desc;//中文描述
	private String short_desc;//描述缩写
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getShort_desc() {
		return short_desc;
	}
	public void setShort_desc(String short_desc) {
		this.short_desc = short_desc;
	}
}
