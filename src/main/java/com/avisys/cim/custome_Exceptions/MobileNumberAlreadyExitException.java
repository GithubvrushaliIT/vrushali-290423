package com.avisys.cim.custome_Exceptions;

public class MobileNumberAlreadyExitException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public MobileNumberAlreadyExitException(String msg) {
		super(msg);
	}

}
