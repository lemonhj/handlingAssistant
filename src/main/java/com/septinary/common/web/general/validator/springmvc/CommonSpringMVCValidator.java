package com.septinary.common.web.general.validator.springmvc;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.septinary.common.web.basic.validator.BaseValidator;

/**
 * 
 * @Filename: com.septinary.common.web.general.validator.springmvc.CommonSpringMVCValidator.java of the project [com.septinary.common.web]
 *     @Type: CommonSpringMVCValidator
 *     @Desc: TODO
 *   @Author: macbook[weide<weide001@gmail.com>]
 *  @Created: 2016年4月8日上午11:23:05
 *
 */
public class CommonSpringMVCValidator extends BaseValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		
	}

}
