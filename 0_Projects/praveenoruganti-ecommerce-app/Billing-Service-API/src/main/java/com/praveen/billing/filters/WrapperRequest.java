package com.praveen.billing.filters;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class WrapperRequest extends HttpServletRequestWrapper {

	private StringBuilder body;

	public WrapperRequest(HttpServletRequest request) throws IOException {
		super(request);
		body = new StringBuilder();
		try (BufferedReader in = request.getReader()) {
			String line;
			while ((line = in.readLine()) != null) {
				body.append(line);
			}
		}
	}
	
	@Override
	public ServletInputStream getInputStream() throws IOException{
		final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(body.toString().getBytes());
		return new ServletInputStream() {
			@Override
			public int read() throws IOException{
				return byteArrayInputStream.read();
			}
			@Override
			public void setReadListener(ReadListener listener) {
				
			}
			@Override
			public boolean isFinished() {
				return false;
			}
			@Override
			public boolean isReady() {
				return false;
			}
		};
	}
	
	@Override
	public BufferedReader getReader() throws IOException{
		return new BufferedReader(new InputStreamReader(this.getInputStream()));
	}

}
