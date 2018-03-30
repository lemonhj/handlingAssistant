package com.septinary.common.web.general.converter.springmvc;

import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;

public class HttpParameter2Enum implements ConverterFactory<String, Enum<?>> {

	@Override
	public <T extends Enum<?>> Converter<String, T> getConverter(Class<T> targetType) {
		// TODO Auto-generated method stub
		return null;
	}

}
