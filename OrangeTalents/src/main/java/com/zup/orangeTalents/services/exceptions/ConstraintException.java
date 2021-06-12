package com.zup.orangeTalents.services.exceptions;

import org.springframework.dao.DataIntegrityViolationException;

public class ConstraintException extends DataIntegrityViolationException{

	private static final long serialVersionUID = 1L;

	public ConstraintException(String msg) {
		super(msg);
	}

}
