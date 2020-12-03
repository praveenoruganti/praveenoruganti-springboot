package com.praveen.ordermanagement.filters;

public class InputRequestContextHolder {

	private InputRequestContextHolder() {

	}

	private static final ThreadLocal<InputRequestContext> INPUT_REQUEST_CONTEXT = new ThreadLocal<>();

	public static void setInputRequestContext(InputRequestContext inputRequestContext) {
		INPUT_REQUEST_CONTEXT.set(inputRequestContext);
	}

	public static InputRequestContext getInputRequestContext() {
		return INPUT_REQUEST_CONTEXT.get();
	}

	public static void clear() {
		INPUT_REQUEST_CONTEXT.remove();
	}

	public static String getOrderId() {
		return INPUT_REQUEST_CONTEXT.get() != null ? INPUT_REQUEST_CONTEXT.get().getOrderId() : null;
	}

	public static String getTransactionId() {
		return INPUT_REQUEST_CONTEXT.get() != null ? INPUT_REQUEST_CONTEXT.get().getTransactionId() : null;
	}

	public static String getAppId() {
		return INPUT_REQUEST_CONTEXT.get() != null ? INPUT_REQUEST_CONTEXT.get().getAppId() : null;
	}

	public static String getHttpMethodWithUriPath() {
		return INPUT_REQUEST_CONTEXT.get() != null ? INPUT_REQUEST_CONTEXT.get().getHttpMethodWithUriPath() : null;
	}

}
