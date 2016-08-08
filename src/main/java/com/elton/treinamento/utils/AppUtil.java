package com.elton.treinamento.utils;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.transaction.TransactionSystemException;

public class AppUtil {

	public static final String extractMessage(final TransactionSystemException e) {
		final Set<ConstraintViolation<?>> violations = ((ConstraintViolationException) e.getRootCause())
				.getConstraintViolations();

		final ConstraintViolation violation = violations.iterator().next();
		return violation.getMessage();
	}

	private AppUtil() {
	}

}
