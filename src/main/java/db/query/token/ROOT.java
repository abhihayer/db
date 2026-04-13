package db.query.token;

import db.query.token.tokenInterface.Token;

public record ROOT() implements Token{
	
	@Override
	public String getToken() {
		return ROOT.class.getSimpleName();
	}
}
