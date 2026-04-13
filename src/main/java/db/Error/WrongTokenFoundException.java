package db.Error;

import java.util.Set;

import db.query.token.tokenInterface.Token;

public class WrongTokenFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4008145011407759409L;
	
	public WrongTokenFoundException(Token currenttoken, Set<String> possibleTokens) {
		
		super("Wrong token found: "+ 
				(currenttoken==null? null : currenttoken.getToken()) + 
				", possible tokens: " + possibleTokens
				);
	}

}
