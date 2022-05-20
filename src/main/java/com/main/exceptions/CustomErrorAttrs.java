package com.main.exceptions;

import java.util.Map;

import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.web.context.request.WebRequest;


public class CustomErrorAttrs extends DefaultErrorAttributes {

	@Override
	public Map<String, Object> getErrorAttributes(WebRequest webRequest, ErrorAttributeOptions options) {
		Map<String, Object> values = super.getErrorAttributes(webRequest, options);
		values.put("success", Boolean.FALSE);
		values.put("status", values.get("error"));
		values.put("errorCode", 421);
		values.put("error", values.get("message"));
		return values;
	}

}
