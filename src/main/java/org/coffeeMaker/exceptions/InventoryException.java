/*
 * Copyright (c) 2024,
 * Adapted from Sarah Heckman, Laurie Williams and Dright Ho
 * assignment.
 */

package org.coffeeMaker.exceptions;

import java.io.Serial;

public class InventoryException extends Exception {

	@Serial
	private static final long serialVersionUID = 1L;
	
	public InventoryException(String msg) {
		super(msg);
	}

}
