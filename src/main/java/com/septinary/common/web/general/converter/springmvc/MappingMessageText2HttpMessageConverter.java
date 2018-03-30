package com.septinary.common.web.general.converter.springmvc;

import java.io.IOException;
import java.nio.charset.Charset;

import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import com.septinary.common.core.util.ResourceMessagerable;
import com.septinary.common.util.IMessageText;
import com.septinary.common.util.MessageText;
import com.septinary.common.util.StreamUtil;

public class MappingMessageText2HttpMessageConverter extends AbstractHttpMessageConverter<IMessageText> {

	@Override
	protected boolean supports(Class<?> clazz) {
		return IMessageText.class.isAssignableFrom(clazz);
	}

	@Override
	protected IMessageText readInternal(Class<? extends IMessageText> clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
		String message = StreamUtil.CopyToString(inputMessage.getBody(),Charset.forName("UTF-8"));
		return new MessageText(message);
	}

	@Override
	protected void writeInternal(IMessageText text, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
		String code = text.getCode();
		Object[] args = text.getArgs();
		//RequestContext requestContext = new RequestContext(request);
        //outputMessage.getBody().write(requestContext.getMessage(code,args).getBytes());
		outputMessage.getBody().write(ResourceMessagerable.GetMessager().getMessage(code, args, null).getBytes());
	}
}
