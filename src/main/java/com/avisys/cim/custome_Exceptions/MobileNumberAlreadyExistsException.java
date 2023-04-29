package com.avisys.cim.custome_Exceptions;

public class MobileNumberAlreadyExistsException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public MobileNumberAlreadyExistsException(String msg) {
		super(msg);
	}

}
