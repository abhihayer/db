package db.Error;

import db.query.token.tokenInterface.Token;

public class DuplicateTokenException extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DuplicateTokenException(Token token) {
		
		super("ERROR! Found duplicate token: " + token.getToken());
	}
}