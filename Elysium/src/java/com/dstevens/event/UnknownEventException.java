package com.dstevens.event;

public class UnknownEventException extends RuntimeException {

	private static final long serialVersionUID = -927445461553296592L;

	public UnknownEventException(String message) {
		super(message);
	}

}
