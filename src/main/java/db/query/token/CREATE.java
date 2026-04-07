package db.query.token;

import db.query.token.tokenInterface.Token;

public record CREATE() implements Token {
	
	@Override
	public String getToken() {
		return CREATE.class.getSimpleName();
	}

	@Override
	public Boolean hasTokenState() {
		return false;
	}
	
}
