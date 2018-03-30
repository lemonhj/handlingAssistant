package com.septinary.common.web.general.filter.springmvc;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.HttpPutFormContentFilter;

public class PutFormFilterSpringMVC extends HttpPutFormContentFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doFilterInternal(request, response, filterChain);
	}

}
