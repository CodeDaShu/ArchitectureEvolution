package com.archevolution.chapter8.dto;

public class ResponseCode {
	public static final String SUCCESS = "200";//查询成功
	public static final String SUCCESS_NULL = "204";//查询成功，但是没有数据
	public static final String PARAMETER_ERROR = "400";//参数错误
	public static final String FAIL = "500";//服务器异常
}
