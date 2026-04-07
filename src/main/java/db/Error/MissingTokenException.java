package db.Error;

import db.query.token.tokenInterface.Token;

public class MissingTokenException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public MissingTokenException(Class<? extends Token> missedToken, Token currentToken) {
		super("Token Missing : "+ missedToken.getSimpleName() + " before adding: "+ currentToken.getToken());
	}
	
	public MissingTokenException(Class<? extends Token> missedToken) {
		super("Token Missing : "+ missedToken.getSimpleName() + " before building query");
	}
}
