package com.septinary.common.web.general.converter.springmvc;

import java.io.IOException;
import java.lang.reflect.Type;

import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.converter.AbstractGenericHttpMessageConverter;
import org.springframework.http.converter.GenericHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

public class MappingXml2HttpMessageConverter extends AbstractGenericHttpMessageConverter<Object> implements GenericHttpMessageConverter<Object> {

	@Override
	public Object read(Type type, Class<?> contextClass, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void writeInternal(Object t, Type type, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected Object readInternal(Class<? extends Object> clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
		// TODO Auto-generated method stub
		return null;
	}

}
